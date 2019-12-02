package action;

import main.Bot;
import main.BotButtons;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class WeatherLogic {

    final private static int time = GregorianCalendar.getInstance().get(Calendar.HOUR);
    final private static int time24 = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);

    public static void makeWeather(Bot bot, Long id, String city) {
        try {
            doWeather(bot, id, city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doWeather(Bot bot, Long id, String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + GetToken.getWeatherToken());
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        String name = city;

        JSONObject main = object.getJSONObject("main");
        double temp = main.getDouble("temp");
        double humidity = main.getDouble("humidity");

        JSONArray getArray = object.getJSONArray("weather");
        String innerMain = "";
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            innerMain = (String) obj.get("main");
        }

        SendMessage sendMessage = new SendMessage().setText(
                "\uD83C\uDDE7\uD83C\uDDFE" + " Сегодня\n" + getClockIcon() + " " +
                new Date().toLocaleString().replaceAll(",","") +"\n"
                        + getCityIcon().toUpperCase() + " " +
                name + "\n" +
                "\uD83C\uDF21 Температура: " + temp + " \u2103" + "\n" +
                "\uD83D\uDCA7 Влажность: " + humidity + "%" + "\n" +
                 getWeatherIcon(innerMain) + "\n").setChatId(id);
        BotButtons.setBackButtons(sendMessage);
        bot.sendInfo(sendMessage);
    }

    private static String getCityIcon(){
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

    private static String getClockIcon(){
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

    private static String getWeatherIcon(String main){
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
