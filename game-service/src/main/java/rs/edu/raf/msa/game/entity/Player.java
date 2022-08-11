package rs.edu.raf.msa.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    Long id;

    String fullName;

    long externalId;

    public Player(PlayerDto playerDto) {
        this.fullName = playerDto.getC();
        this.externalId = playerDto.getExternalId();
    }
}
