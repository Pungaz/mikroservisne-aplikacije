package rs.edu.raf.msa.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.edu.raf.msa.game.client.GameClient;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;
import rs.edu.raf.msa.game.entity.Game;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.Player;
import rs.edu.raf.msa.game.repository.GameRepository;
import rs.edu.raf.msa.game.repository.PlayRepository;
import rs.edu.raf.msa.game.repository.PlayerRepository;
import rs.edu.raf.msa.game.utils.PlayerUtil;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class GamePlayByPlayJob {

    final GameClient gameClient;
    final PlayerRepository playerRepository;
    final GameRepository gameRepository;
    final PlayRepository playRepository;

    @Scheduled(fixedDelay = 5_000)
    public void scanGames() {
        List<String> allGames = gameClient.games();
        log.info("Loaded games from pbp-service: {}", allGames);

        int i = 0;

        for (String fileName : allGames) {

            if (!gameRepository.existsGameByFileName(fileName)) {
                log.info("Game with filename: {} doesn't exist in db", fileName);

                ArrayList<PlayerDto> playerDtos = gameClient.players(fileName);

                for (PlayerDto playerDto : playerDtos) {

                    Player player = Player.builder()
                            .externalId(playerDto.getExternalId())
                            .fullName(PlayerUtil.splitName(playerDto.getC()))
                            .build();

                    if (!playerRepository.existsByExternalId(player.getExternalId())) {
                        playerRepository.save(player);
                        log.info("Saved player: {}", player);
                    }

                }

                Game game = Game.builder()
                        .lastParsedPlayTime("0")
                        .fileName(fileName)
                        .startedParsing(true)
                        .build();

                gameRepository.save(game);
                log.info("Saved game: {}", game);

            } else {
                log.info("Game with filename: {} exists in db", fileName);

                String gameFileName = allGames.get(i);
                i++;
                String start = "0:00";
                String end = "2:00";

                ArrayList<PlayDto> playDtos = gameClient.plays(gameFileName, start, end);
                log.info("Loaded {} plays from file: {}, time interval is from {} to {}", playDtos.size(), gameFileName, start, end);

                Game currentGame = gameRepository.findGameByFileName(gameFileName);

                for (PlayDto playDto : playDtos) {

                    if (playDto.getAtin().compareTo(currentGame.getLastParsedPlayTime()) > 0) {

                        Play play = Play.builder()
                                .gameId(currentGame.getId())
                                .playName(playDto.getD())
                                .externalId(playDto.getId())
                                .atin(playDto.getAtin())
                                .build();

                        currentGame.setLastParsedPlayExternalId(play.getExternalId());
                        currentGame.setLastParsedPlayTime(playDto.getAtin());

                        playRepository.save(play);
                        gameRepository.save(currentGame);

                    }
                }
            }
        }
    }
}
