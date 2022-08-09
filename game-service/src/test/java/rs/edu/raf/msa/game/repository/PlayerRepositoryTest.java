package rs.edu.raf.msa.game.repository;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.game.entity.Player;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class PlayerRepositoryTest {

	@Autowired
	PlayerRepository playerRepository;

	@Test
	void savePlayer() {
		Player p1 = Player.builder().externalId(203999L).shortName("nikola_jokic").firstName("Nikola").lastName("Jokic")
				.build();
		Player p2 = Player.builder().externalId(2544L).shortName("lebron_james").firstName("Lebron").lastName("James")
				.build();

		playerRepository.saveAll(asList(p1, p2));

		Iterable<Player> found = playerRepository.findAll();
		log.debug("{}", found);
		assertThat(found).contains(p1, p2);
	}

	@Test
	void findByExternalId() {
		Player p1 = Player.builder().externalId(203999L).shortName("nikola_jokic").firstName("Nikola").lastName("Jokic")
				.build();
		Player p2 = Player.builder().externalId(2544L).shortName("lebron_james").firstName("Lebron").lastName("James")
				.build();

		playerRepository.saveAll(asList(p1, p2));

		assertThat(playerRepository.findByExternalId(2544)).extracting(p -> p.getShortName()).isEqualTo("lebron_james");
	}
}
