package rs.edu.raf.msa.game.entity;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

	@Id
	Long id;

	Long externalId;
	
	String shortName;
	String firstName;
	String lastName;
	
	
}
