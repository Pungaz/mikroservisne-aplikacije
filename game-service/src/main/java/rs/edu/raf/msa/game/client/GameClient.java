package rs.edu.raf.msa.game.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

import java.util.List;

@FeignClient(value = "gameClient", url = "http://localhost:8080/")
public interface GameClient {

    @GetMapping("games")
    List<String> games();

    @GetMapping("players/{gameId}")
    List<PlayerDto> players(@PathVariable String gameId);

    @GetMapping("plays/{gameId}/{fromMin}/{toMin}")
    List<PlayDto> plays(@PathVariable String gameId,
                        @PathVariable String fromMin,
                        @PathVariable String toMin);

}