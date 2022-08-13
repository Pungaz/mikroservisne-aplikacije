package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @Generated
    long id;

    String fileName;

    boolean startedParsing;

    boolean finishedParsing;

    float lastParsedPlayId;

    float lastParsedPlayTime;
}
