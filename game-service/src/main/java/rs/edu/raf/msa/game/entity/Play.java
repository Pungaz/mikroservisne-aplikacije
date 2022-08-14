package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Play implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "play", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<PlayPlayer> playPlayerSet;

    long externalId;

    String playName;

    Long gameId;

    String atin;


}
