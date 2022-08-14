package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    String fileName;

    boolean startedParsing;

    boolean finishedParsing;

    double lastParsedPlayExternalId;

    String lastParsedPlayTime;
}
