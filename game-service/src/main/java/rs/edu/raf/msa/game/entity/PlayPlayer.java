package rs.edu.raf.msa.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    // TODO Reference to player
}
