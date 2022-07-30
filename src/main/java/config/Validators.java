package config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNumericValue(String input){
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPositive(double amount) {
        return Double.compare(amount, 0.0) > 0;
    }

}
