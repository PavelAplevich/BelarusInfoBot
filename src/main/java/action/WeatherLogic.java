package action;

import main.Bot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

public class WeatherLogic {

    public static void makeWeather(Bot bot, Long id, String city) {
        try {
            doWeather(bot, id, city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doWeather(Bot bot, Long id, String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=b3d530b928f6fd5e3ca6fe9e4c62a193");
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
        String mainN = "";
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            mainN = (String) obj.get("main");
        }
        String icon = "\uD83C\uDF07";
        SendMessage sendMessage = new SendMessage().setText(
                new Date().toLocaleString().replaceAll(",","") +"\n"
                        +icon.toUpperCase()+
                "Город: " + name + "\n" +
                "Температура: " + temp + "C" + "\n" +
                "Влажность:" + humidity + "%" + "\n" +
                "Main: " + mainN + "\n").setChatId(id);
        bot.sendInfo(sendMessage);
    }
}
