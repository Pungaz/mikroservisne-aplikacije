package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import lombok.Builder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Play {

	@Id
	Long id;

	Long externalId;

	Long gameId;
}
