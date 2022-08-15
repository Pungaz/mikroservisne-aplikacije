package rs.edu.raf.msa.pbp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static rs.edu.raf.msa.pbp.utils.StringToDoubleConverter.fromStringToDouble;

@Data
public class PlayByPlay {
    Map<String, Player> players = new LinkedHashMap<>();
    List<Quarter> quarters = new ArrayList<>(4);

    public List<Play> play(String fromMinString, String toMinString) {
        double fromMin = fromStringToDouble(fromMinString);
        double toMin = fromStringToDouble(toMinString);

        if (fromMin > toMin || fromMin < 0.0 || toMin > 48.00) {
            return null;
        }

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

    public double currentTime(int quarter, String currentTime) {
        return ((quarter - 1) * 12) + abs(fromStringToDouble(currentTime) - 11.60);

    }

    public List<Player> listPlayersByGameId() {
        ArrayList<Player> playerArrayList = new ArrayList<>();

        for (Map.Entry<String, Player> entry : getPlayers().entrySet()) {
            Player player = entry.getValue();
            player.setExternalId(Long.parseLong(entry.getKey()));
            playerArrayList.add(player);
        }
        return playerArrayList;
    }

}