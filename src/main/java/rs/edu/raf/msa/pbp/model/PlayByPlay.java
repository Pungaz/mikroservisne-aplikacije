package rs.edu.raf.msa.pbp.model;

import lombok.Data;

import java.util.*;

@Data
public class PlayByPlay {

    Map<String, Player> players = new LinkedHashMap<>();

    List<Quarter> quarters = new ArrayList<>(4);

    public List<Play> play(String fromMinString, String toMinString) {

        double fromMin = fromStringToDouble(fromMinString);
        double toMin = fromStringToDouble(toMinString);

        int fromQuarter = getQuarter(fromMin);
        int toQuarter = getQuarter(toMin);

        List<Play> result = new ArrayList<>();
        for(Quarter quarter : quarters) {
            List<Play> plays = quarter.getPlays();
            for (Play play : plays) {
                if (currentTime(quarter.q, play.c) >= fromMin && currentTime(quarter.q, play.c) <= toMin){
                    result.add(play);
                }
            }
        }

        return result;
    }

    public int getQuarter(double minute) {
        if (minute >= 0 && minute <= 12) {
            return 1;
        } else if (minute >= 13 && minute <= 24) {
            return 2;
        } else if (minute >= 25 && minute <= 36) {
            return 3;
        } else if (minute >= 37 && minute <= 48) {
            return 4;
        }
        return 0;
    }

    public double fromStringToDouble(String string){
        String[] stringArray = string.split(":");
        return Double.parseDouble(stringArray[0]) + Double.parseDouble(stringArray[1]) / 100;
    }

    public double currentTime(int quarter, String currentTime){
        return ((quarter - 1) * 12) + fromStringToDouble(currentTime) - 12.0;
    }

    public List<Player> players(){

        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, Player> entry : getPlayers().entrySet()) {
            Player player = entry.getValue();
//            player.setExternalId(entry.getKey());
            players.add(player);
        }
        //return new ArrayList<>(getPlayers().values());
        return players;
    }

}
