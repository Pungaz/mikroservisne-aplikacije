package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.PlayPlayer;

@Repository
public interface PlayPlayerRepository extends PagingAndSortingRepository<PlayPlayer, Long> {


}
