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

import java.time.LocalTime;
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

            if (!gameRepository.existsGameByFileName(fileName)) {

                ArrayList<PlayerDto> playerDtos = gameClient.players(fileName);

                for (PlayerDto playerDto : playerDtos) {

                    Player player = new Player(playerDto);

                    if (!playerRepository.existsByExternalId(player.getExternalId())) {
                        playerRepository.save(player);
                    }

                }

                Game game = Game.builder().fileName(fileName).build();
                gameRepository.save(game);

            } else {

            }
        }

        String gameId = allGames.get(0);
        LocalTime start = LocalTime.of(0, 0, 0);

        LocalTime end = start.plusMinutes(1);
        ArrayList<PlayDto> ps = gameClient.plays(gameId, start.toString(), end.toString());
        log.info("Loaded {} plays from {}: {}", ps.size(), gameId, start);
    }

}
