package rs.edu.raf.msa.game.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.game.client.GameClient;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.repository.PlayerRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class GamePlayByPlayJob {

	final GameClient gameClient;
	final PlayerRepository playerRepository;
	
	@Scheduled(fixedDelay = 5_000)
	public void scanGames() {
		List<String> allGames = gameClient.games();
		log.info("Loaded games from pbp-service: {}", allGames);
		
		String gameId = allGames.get(0);
		LocalTime start = LocalTime.of(0, 0, 0);
		
		LocalTime end = start.plusMinutes(1);
		List<PlayDto> ps = gameClient.plays(gameId, start.toString(), end.toString());
		log.info("Loaded {} plays from {}: {}", ps.size(), gameId, start);
	}
	
}
