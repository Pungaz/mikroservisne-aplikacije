package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "player")
    List<PlayPlayer> playPlayerSet;

    String fullName;

    long externalId;
}
