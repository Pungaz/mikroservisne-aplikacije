package rs.edu.raf.msa.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.edu.raf.msa.game.client.GameClient;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;
import rs.edu.raf.msa.game.entity.Game;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.PlayPlayer;
import rs.edu.raf.msa.game.entity.Player;
import rs.edu.raf.msa.game.repository.GameRepository;
import rs.edu.raf.msa.game.repository.PlayPlayerRepository;
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
    final PlayPlayerRepository playPlayerRepository;

    int finishedGameCounter = 0;

    @Value("${jobs.enabled:true}")
    private boolean enabled = true;

    @Scheduled(fixedDelay = 5_000)
    public void scanGames() {
        if (enabled) {

            List<String> allGames = gameClient.games();
            log.info("Loaded games from pbp-service: {}", allGames);

            for (String fileName : allGames) {

                if (!gameRepository.existsGameByFileName(fileName)) {
                    log.info("Game with filename: {} doesn't exist in db", fileName);

                    ArrayList<PlayerDto> playerDtos = gameClient.players(fileName);

                    for (PlayerDto playerDto : playerDtos) {

                        Player player = Player.builder()
                                .externalId(playerDto.getExternalId())
                                .fullName(PlayerUtil.splitName(playerDto.getC()))
                                .build();

                        if (!playerRepository.existsByExternalId(playerDto.getExternalId())) {
                            playerRepository.save(player);
                            log.info("Saved player: {}", player);
                        }

                    }

                    Game game = Game.builder()
                            .lastParsedPlayTime("0")
                            .fileName(fileName)
                            .build();

                    gameRepository.save(game);
                    log.info("Saved game: {}", game);

                } else {
                    log.info("Game with filename: {} exists in db", fileName);

                    String start = "0:00";
                    String end = "48:00";

                    ArrayList<PlayDto> playDtos = gameClient.plays(fileName, start, end);
                    log.info("Loaded {} plays from file: {}, time interval is from {} to {}", playDtos.size(),
                            fileName, start, end);

                    Game currentGame = gameRepository.findGameByFileName(fileName);

                    for (PlayDto playDto : playDtos) {

                        if (playDto.getAtin() == null) {
                            playDto.setAtin(currentGame.getLastParsedPlayTime());
                        }

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

                            if (playDto.getPlayers() != null) {
                                for (String playerExternalId : playDto.getPlayers()) {

                                    if (playerRepository.existsByExternalId(Long.parseLong(playerExternalId))) {

                                        PlayPlayer playPlayer = PlayPlayer.builder()
                                                .player(playerRepository.findPlayerByExternalId(Long.parseLong(playerExternalId)))
                                                .play(playRepository.findPlayByExternalIdAndGameId(play.getExternalId(), currentGame.getId()))
                                                .gameId(currentGame.getId())
                                                .build();

                                        playPlayerRepository.save(playPlayer);
                                    }
                                }
                            }

                        }
                    }
                    currentGame.setFinishedParsing(true);
                    gameRepository.save(currentGame);
                    finishedGameCounter++;

                    if (finishedGameCounter == allGames.size()) {
                        enabled = false;
                        log.info("All games are finished parsing");
                    }

                }
            }
        }
    }
}
