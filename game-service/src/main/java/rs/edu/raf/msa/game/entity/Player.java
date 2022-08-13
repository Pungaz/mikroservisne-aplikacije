package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @Generated
    Long id;

    String fullName;

    long externalId;
}
