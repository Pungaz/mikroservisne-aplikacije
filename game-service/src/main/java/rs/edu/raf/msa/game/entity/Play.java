package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import rs.edu.raf.msa.game.client.dto.PlayDto;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Play {

    @Id
    @Generated
    Long id;

    String name;

    Long gameId;

    public Play(PlayDto playDto) {
        this.name = playDto.getD();
    }

}
