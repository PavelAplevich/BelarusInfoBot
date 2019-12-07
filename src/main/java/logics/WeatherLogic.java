package logics;

import main.Bot;
import main.BotButtons;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import static logics.IconLogic.*;

public class WeatherLogic {

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
                        new Date().toLocaleString().replaceAll(",", "") + "\n"
                        + getCityIcon().toUpperCase() + " " +
                        name + "\n" +
                        "\uD83C\uDF21 Температура: " + temp + " \u2103" + "\n" +
                        "\uD83D\uDCA7 Влажность: " + humidity + "%" + "\n" +
                        getWeatherIcon(innerMain) + "\n").setChatId(id).setReplyMarkup(BotButtons.getMenuButton());
        bot.sendInfo(sendMessage);
    }
}
