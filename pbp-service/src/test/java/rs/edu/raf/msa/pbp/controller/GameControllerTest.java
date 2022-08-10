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
    void gameLoaded() throws IOException {
        List<String> allGames = gameController.games();
        assertNotNull(allGames);

        log.debug("{}", allGames);
        assertThat(allGames).contains("20200924LALDEN", "20200930MIALAL", "20201002MIALAL");
    }

    @Test
    void gamesLoaded() throws IOException {
        String[] games = {"20200924LALDEN", "20200930MIALAL", "20201002MIALAL"};
        for (String game : games) {
            PlayByPlay pbp = gameController.game(game);
            assertNotNull(pbp);
            assertNotNull(pbp.getQuarters());
            assertNotNull(pbp.getPlayers());
            for (Quarter q : pbp.getQuarters()) {
                assertNotNull(q.getPlays());
            }
            log.debug(game);
        }
    }

    @Test
    void playsLoaded() throws IOException {
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
        System.out.println(ids2);
        log.debug("{}", ids2);
        assertThat(ids2).containsExactly(326, 327, 328, 329, 330, 337, 338);
    }

    @Test
    void listPlayersByGameIdLoaded() throws IOException {
        List<Player> playersList = gameController.listPlayersByGameId("20201002MIALAL");
        assertNotNull(playersList);

        List<String> playerNameList = playersList.stream().map(Player::getC).collect(Collectors.toList());
        log.debug("{}", playerNameList);
        assertThat(playerNameList).contains("markieff_morris",
                "danny_green",
                "lebron_james",
                "kentavious_caldwell-pope",
                "kyle_kuzma",
                "meyers_leonard");

    }

}
