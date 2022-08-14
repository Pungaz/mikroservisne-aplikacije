package rs.edu.raf.msa.game.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.game.entity.Game;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class PlayerRepositoryTest {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayRepository playRepository;

	@Test
	void testAll() {
		Game g = new Game();
		g.setFileName("123");
		g = gameRepository.save(g);

		Game gameTest = gameRepository.findGameByFileName("123");



	}

	@Test
	void savePlayer() {
		// TODO saving player 
	}

	@Test
	void savePlay() {
		// TODO saving play(s) from game
	}

	@Test
	void findByExternalId() {
		// TODO finding player by external id
	}
}
