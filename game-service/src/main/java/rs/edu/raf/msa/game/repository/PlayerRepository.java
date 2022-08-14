package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.Player;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    boolean existsByExternalId(long externalId);

    Player findPlayerByExternalId(long externalId);

}