package config;

import java.util.HashMap;
import java.util.Map;

public class ColorText {
    public static String color(String s, String color){
        String ANSI_RESET = "\u001B[0m";
        Map<String, String> colors = new HashMap<>();
        colors.put("RED", "\u001B[31m");
        colors.put("GREEN", "\u001B[32m");
        colors.put("BLUE", "\u001B[34m");
        return colors.get(color)+s+ANSI_RESET;
    }
}
