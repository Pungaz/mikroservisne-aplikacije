package rs.edu.raf.msa.game.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

import java.util.ArrayList;

@FeignClient(value = "gameClient", url = "http://localhost:8080/")
public interface GameClient {

    @GetMapping("games")
    ArrayList<String> games();

    @GetMapping("/game/{fileName}")
    Object findGameByFileName(@PathVariable("fileName") String fileName);

    @GetMapping("players/{gameId}")
    ArrayList<PlayerDto> players(@PathVariable String gameId);

    @GetMapping("plays/{gameId}/{fromMin}/{toMin}")
    ArrayList<PlayDto> plays(@PathVariable String gameId,
                             @PathVariable String fromMin,
                             @PathVariable String toMin);

}