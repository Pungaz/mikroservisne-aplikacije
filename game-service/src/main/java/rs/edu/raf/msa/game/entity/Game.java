package rs.edu.raf.msa.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    Long id;

    ArrayList<Player> players;

    ArrayList<Play> plays;

    boolean startedParsing;

    boolean finishedParsing;

    double endedParsingTime;

}
