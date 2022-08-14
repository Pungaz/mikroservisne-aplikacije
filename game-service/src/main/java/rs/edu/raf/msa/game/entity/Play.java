package rs.edu.raf.msa.game.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Play {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    long externalId;

    String playName;

    Long gameId;

    String atin;

//    @ManyToMany
//    Set<Course> likedCourses;
}
