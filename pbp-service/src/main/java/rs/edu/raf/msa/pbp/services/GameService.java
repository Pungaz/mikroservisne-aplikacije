package rs.edu.raf.msa.pbp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rs.edu.raf.msa.pbp.model.PlayByPlay;

import java.io.IOException;
import java.io.InputStream;


@Service
public class GameService {

    private final ObjectMapper objectMapper;

    public GameService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PlayByPlay game(String gameId) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("games/" + gameId + ".json");
        try {
            return objectMapper.readValue(input, PlayByPlay.class);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error opening game id=" + gameId);
        }
    }


}
