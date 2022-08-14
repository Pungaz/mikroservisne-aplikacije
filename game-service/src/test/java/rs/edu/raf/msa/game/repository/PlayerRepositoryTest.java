package rs.edu.raf.msa.game.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.raf.msa.game.entity.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void savePlayer() {
        Player player = new Player();
        playerRepository.save(player);

        ArrayList<Player> playerTestList = (ArrayList<Player>) playerRepository.findAll();

        assertNotNull(playerTestList);
    }

    @Test
    void findByExternalId() {
        Player player = new Player();
        player.setExternalId(123);
        playerRepository.save(player);

        Player playerTest = playerRepository.findPlayerByExternalId(123);

        assertNotNull(playerTest);
    }
}
