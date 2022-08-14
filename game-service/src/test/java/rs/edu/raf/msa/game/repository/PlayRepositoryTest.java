package rs.edu.raf.msa.game.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import rs.edu.raf.msa.game.entity.Game;
import rs.edu.raf.msa.game.entity.Play;
import rs.edu.raf.msa.game.entity.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
public class PlayRepositoryTest {

    @Autowired
    PlayRepository playRepository;

    @Test
    void savePlay() {
        Play play = new Play();
        play.setExternalId(123);
        play.setGameId(1L);
        playRepository.save(play);

        Play playTest = playRepository.findPlayByExternalIdAndGameId(123L, 1L);

        assertNotNull(playTest);
    }
}
