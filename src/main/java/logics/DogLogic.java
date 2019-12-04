package logics;

import main.Bot;
import main.BotButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class DogLogic {

   private static File file = new File("/Users/dariaapril/IDEA projects/BelarusInfoBot/src/main/resources/BufferFile");

    public static void makeDog(Bot bot, Long chatId) {
        try {
            URL url = new URL("https://dog.ceo/api/breeds/image/random");
            Scanner scanner = new Scanner((InputStream) url.getContent());
            String result = "";
            while(scanner.hasNext()){
                result = result.concat(scanner.nextLine());
            }
            result = result.substring(result.indexOf("{\"message\":\"") + 12, result.indexOf("\",\"status\":"));
            URL url1 = new URL(result.replaceAll("\\\\", ""));
            BufferedImage image = ImageIO.read(url1);
            ImageIO.write(image, "jpg", file);
            bot.sendInfo(new SendPhoto().setChatId(chatId).setReplyMarkup(BotButtons.getMenuButton()).setPhoto(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
