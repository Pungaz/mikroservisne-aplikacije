package rs.edu.raf.msa.game.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Player;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Id> {

    //    ArrayList<Player> findPlayersByGameId(long gameId);
//    Player findByExternalId(long externalId);

}
