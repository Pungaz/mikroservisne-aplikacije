package rs.edu.raf.msa.game.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

import java.util.ArrayList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @Generated
    Long id;

    String fullName;

    long externalId;

    public Player(PlayerDto playerDto) {
        this.fullName = splitName(playerDto.getC());
        this.externalId = playerDto.getExternalId();
    }

    String splitName(String string){
        String[] array = string.split("_");
        StringBuilder nameBuilder = new StringBuilder();

        for (String word: array){
            nameBuilder.append(word);
        }
        return nameBuilder.toString();
    }

}
