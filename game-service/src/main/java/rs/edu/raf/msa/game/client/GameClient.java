package rs.edu.raf.msa.game.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

import rs.edu.raf.msa.game.client.dto.PlayDto;
import rs.edu.raf.msa.game.client.dto.PlayerDto;

@FeignClient(value = "gameClient", url = "http://localhost:8080/")
public interface GameClient {

	// TODO Fill in the annotations
	
	public List<String> games();

	public List<PlayerDto> players(@PathVariable String gameId);

	public List<PlayDto> plays(String gameId, String fromMin, String toMin);

}
