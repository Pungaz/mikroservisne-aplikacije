package rs.edu.raf.msa.game.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Play {

	@Id
	Long id;

	Long externalId;

	Long gameId;

	// TODO Consider using @Builder
	
}
