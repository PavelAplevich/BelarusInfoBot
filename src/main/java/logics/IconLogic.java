package logics;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class IconLogic {
    final private static int time = GregorianCalendar.getInstance().get(Calendar.HOUR);
    final private static int time24 = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);

    public static String getCityIcon(){
        if(time24 >= 6 && time24 < 11){
            return "\uD83C\uDF07";
        } else if(time24 >= 11 && time24 < 18){
            return "\uD83C\uDF06";
        } else if(time24 >= 18 && time24 < 22){
            return "\uD83C\uDFD9";
        } else {
            return "\uD83C\uDF03";
        }
    }

    public static String getClockIcon(){
        switch (time){
            case 1:
                return "\uD83D\uDD50";
            case 2:
                return "\uD83D\uDD51";
            case 3:
                return "\uD83D\uDD52";
            case 4:
                return "\uD83D\uDD53";
            case 5:
                return "\uD83D\uDD54";
            case 6:
                return "\uD83D\uDD55";
            case 7:
                return "\uD83D\uDD56";
            case 8:
                return "\uD83D\uDD57";
            case 9:
                return "\uD83D\uDD58";
            case 10:
                return "\uD83D\uDD59";
            case 11:
                return "\uD83D\uDD5A";
            default:
                return "\uD83D\uDD5B";
        }
    }

    public static String getWeatherIcon(String main){
        switch (main){
            case "Clouds":
                return "\u2601 Облачно";
            case "Mist":
                return "\uD83C\uDF01 Туман";
            case "Snow":
                return "\u2744 Снег";
            default:
                return main;
        }
    }
}
