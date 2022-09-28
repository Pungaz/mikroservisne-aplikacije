package rs.edu.raf.msa.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<PlayPlayer> playPlayerSet;

    String fullName;

    long externalId;
}
