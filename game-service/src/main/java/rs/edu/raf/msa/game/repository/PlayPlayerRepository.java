package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.PlayPlayer;

public interface PlayPlayerRepository extends PagingAndSortingRepository<PlayPlayer, Long> {


}
