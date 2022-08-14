package rs.edu.raf.msa.game.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.edu.raf.msa.game.entity.Play;

import java.util.List;

public interface PlayRepository extends PagingAndSortingRepository<Play, Long> {

    Play findPlayByExternalIdAndGameId(long externalId, long gameId);

}
