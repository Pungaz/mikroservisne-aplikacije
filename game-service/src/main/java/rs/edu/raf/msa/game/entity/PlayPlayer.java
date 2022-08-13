package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayPlayer {

    @Id
    @Generated
    long id;

    // TODO Reference to player
}
