package rs.edu.raf.msa.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.edu.raf.msa.game.client.GameClient;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;
import rs.edu.raf.msa.game.entity.Game;
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

        for (String fileName : allGames) {

            int i = 0;

            if (!gameRepository.existsGameByFileName(fileName)) {

                ArrayList<PlayerDto> playerDtos = gameClient.players(fileName);

                for (PlayerDto playerDto : playerDtos) {

                    Player player = Player.builder()
                            .externalId(playerDto.getExternalId())
                            .fullName(PlayerUtil.splitName(playerDto.getC()))
                            .build();

                    if (!playerRepository.existsByExternalId(player.getExternalId())) {
                        playerRepository.save(player);
                    }

                }

                Game game = Game.builder()
                        .fileName(fileName)
                        .startedParsing(true)
                        .build();
                gameRepository.save(game);

            } else {

                String gameFileName = allGames.get(i);
                String start = "9:00";
                String end = "17:00";
//                LocalTime start = LocalTime.of(0, 0, 0);
//                LocalTime end = start.plusMinutes(1);

                ArrayList<PlayDto> ps = gameClient.plays(gameFileName, start, end);
                log.info("Loaded {} plays from file: {}, time interval is from {} to {}", ps.size(), gameFileName, start, end);


            }
        }


    }

}
