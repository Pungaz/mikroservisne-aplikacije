package rs.edu.raf.msa.pbp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.edu.raf.msa.pbp.model.Play;
import rs.edu.raf.msa.pbp.model.PlayByPlay;
import rs.edu.raf.msa.pbp.model.Player;
import rs.edu.raf.msa.pbp.model.Quarter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class GameControllerTest {

	@Autowired
	GameController gameController;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void gamesLoaded() throws IOException {
		List<String> games = gameController.games();
		assertNotNull(games);

		log.debug("{}", games);
		assertThat(games).contains("20200924LALDEN", "20200930MIALAL", "20201002MIALAL");
	}

	@Test
	void gameLoaded() throws IOException {
		String[] games = {"20200924LALDEN", "20200930MIALAL", "20201002MIALAL"};
		for(String game : games){
			PlayByPlay pbp = gameController.game(game);
			assertNotNull(pbp);
			assertNotNull(pbp.getQuarters());
			assertNotNull(pbp.getPlayers());
			for(Quarter q : pbp.getQuarters()){
				assertNotNull(q.getPlays());
			}

			log.debug(game);
		}
	}

	@Test
	void plays() throws IOException {
		PlayByPlay pbp1 = gameController.game("20200924LALDEN");
		assertNotNull(pbp1);

		List<Play> ps1 = pbp1.play("11:50", "12:10");
		List<Integer> ids1 = ps1.stream().map(Play::getId).collect(Collectors.toList());
		log.debug("{}", ids1);
		assertThat(ids1).containsExactly(140, 142, 143, 145, 147, 148, 149, 150, 151, 152, 153, 160);

		PlayByPlay pbp2 = gameController.game("20201002MIALAL");
		assertNotNull(pbp2);

		List<Play> ps2 = pbp2.play("23:45", "24:15");
		List<Integer> ids2 = ps2.stream().map(Play::getId).collect(Collectors.toList());
		log.debug("{}", ids2);
		assertThat(ids2).containsExactly(326, 327, 328, 329, 330, 337, 338);
	}

	@Test
	void players() throws IOException {
		PlayByPlay pbp = gameController.game("20201002MIALAL");
		assertNotNull(pbp);

		List<Player> players = pbp.players();
		assertNotNull(players);

		String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(players);
		log.debug(formattedJson);
	}

}
