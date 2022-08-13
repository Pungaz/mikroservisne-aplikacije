package rs.edu.raf.msa.game.utils;

public class PlayerUtil {

    public static String splitName(String string) {
        String[] array = string.split("_");
        StringBuilder nameBuilder = new StringBuilder();

        for (String word : array) {
            nameBuilder.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return nameBuilder.toString();
    }

}
