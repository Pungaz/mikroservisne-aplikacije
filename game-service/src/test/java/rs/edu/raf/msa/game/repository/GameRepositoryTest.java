package rs.edu.raf.msa.game.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.raf.msa.game.entity.Game;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Test
    void saveGame() {
        Game g = new Game();
        g.setFileName("123");
        gameRepository.save(g);

        Game gameTest = gameRepository.findGameByFileName("123");

        assertNotNull(gameTest);
    }
}
