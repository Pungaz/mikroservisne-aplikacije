package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String fullName;

    long externalId;
}
