package logics;

import main.Bot;
import main.BotButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class CourseLogic {

    public static void makeCourse(Bot bot, Long id, String city) {
        try {
            URL url = new URL("http://www.nbrb.by/api/exrates/rates?periodicity=0");
            Scanner scanner = new Scanner((InputStream)(url.getContent()));
            String message = "Курс валют по данным НБРБ \uD83C\uDDE7\uD83C\uDDFE:\n" + getMessage(scanner);
            SendMessage sendMessage = new SendMessage().setText("<b>" + message + "</b>").setChatId(id).
                    setParseMode("HTML").setReplyMarkup(BotButtons.getMenuButton());
            bot.sendInfo(sendMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMessage(Scanner scanner) {
        String result = "";
        String[] array = scanner.nextLine().split(",\\{");
        for(String line:array){
            String scale = line.substring(line.indexOf("\"Cur_Scale\":") + 12, line.indexOf(",\"Cur_Name\""));
            String name = line.substring(line.indexOf("\"Cur_Name\":\"") + 12, line.indexOf("\",\"Cur_OfficialRate\""));
            if(name.contains("СДР")) {
                name = "СДР";
            }
            String rate = line.substring(line.indexOf("\"Cur_OfficialRate\":") + 19, line.indexOf("}"));
            rate = rate.substring(0, 4);
            result = result.concat("" + scale + " " + name + " = " + rate + " Br\n");
        }
        return result;
    }

}
