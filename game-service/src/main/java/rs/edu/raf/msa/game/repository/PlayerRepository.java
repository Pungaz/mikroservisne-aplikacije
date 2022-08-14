package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    boolean existsByExternalId(long externalId);

}