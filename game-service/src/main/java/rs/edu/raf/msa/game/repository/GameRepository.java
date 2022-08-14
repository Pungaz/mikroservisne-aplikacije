package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Game;

import java.util.List;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    boolean existsGameByFileName(String fileName);

    Game findGameByFileName(String fileNmae);

    List<Game> findAll();
}
