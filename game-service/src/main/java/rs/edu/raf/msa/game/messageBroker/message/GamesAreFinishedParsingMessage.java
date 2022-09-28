package rs.edu.raf.msa.game.messageBroker.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class GamesAreFinishedParsingMessage {
	String message;
}
