package rs.edu.raf.msa.pbp.model;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@Data
public class PlayByPlay {
    Map<String, Player> players = new LinkedHashMap<>();
    List<Quarter> quarters = new ArrayList<>(4);

    public List<Play> play(String fromMinString, String toMinString) {
        double fromMin = fromStringToDouble(fromMinString);
        double toMin = fromStringToDouble(toMinString);

        List<Play> result = new ArrayList<>();
        for (Quarter quarter : quarters) {
            List<Play> plays = quarter.getPlays();
            for (Play play : plays) {
                if (currentTime(quarter.q, play.c) >= fromMin && currentTime(quarter.q, play.c) <= toMin) {
                    result.add(play);
                }
            }
        }
        return result;
    }

    public double fromStringToDouble(String string) {
        try {
            if (!string.equals("12:00")) {
                String[] stringArray = string.split(":");
                return Double.parseDouble(stringArray[0]) + Double.parseDouble(stringArray[1]) / 100;
            } else {
                return 11.6;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with entered time period", e);
        }
    }

    public double currentTime(int quarter, String currentTime) {
        return ((quarter - 1) * 12) + abs(fromStringToDouble(currentTime) - 11.60);

    }

    public List<Player> listPlayersByGameId() {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, Player> entry : getPlayers().entrySet()) {
            Player player = entry.getValue();
            player.setExternalId(Long.parseLong(entry.getKey()));
            players.add(player);
        }
        return players;
    }

}