package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @Generated
    Long id;

    String fileName;

    ArrayList<Player> players;

    ArrayList<Play> plays;

    boolean startedParsing;

    boolean finishedParsing;

    double endedParsingTime;
}
