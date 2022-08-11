package rs.edu.raf.msa.game.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Game;

public interface GameRepository extends PagingAndSortingRepository<Game, Id> {
    Game findGameById(long id);

}
