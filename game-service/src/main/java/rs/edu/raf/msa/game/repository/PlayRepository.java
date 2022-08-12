package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Play;

public interface PlayRepository extends PagingAndSortingRepository<Play, Long> {
//    List<Play> findByGameId(Long id, Sort sort);

}
