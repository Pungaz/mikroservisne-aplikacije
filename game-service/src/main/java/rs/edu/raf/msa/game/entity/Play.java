package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Play {

    @Id
    @Generated
    Long id;

    long externalId;

    String playName;

    Long gameId;

    String atin;

//    @ManyToMany
//    Set<Course> likedCourses;
}
