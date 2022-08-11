package rs.edu.raf.msa.game.entity;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {

	@Id
	Long id;

	// TODO Also extend to save currently parsed status
	
}
