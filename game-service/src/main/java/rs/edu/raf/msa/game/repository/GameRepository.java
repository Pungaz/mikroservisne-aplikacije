package rs.edu.raf.msa.game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Game;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    Game findGameById(long id);

    public boolean existsGameByFileName(String fileName);

    public Game findGameByFileName(String fileNmae);

}
