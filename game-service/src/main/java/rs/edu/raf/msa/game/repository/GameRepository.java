package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.edu.raf.msa.game.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    boolean existsGameByFileName(String fileName);

    Game findGameByFileName(String fileNmae);

    List<Game> findAll();
}
