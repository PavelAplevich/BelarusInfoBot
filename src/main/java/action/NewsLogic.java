package action;

import main.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewsLogic {

    public static void makeNews(Bot bot, Long id, String city, int count) {
        String news = "";
        try {
            URL url = makeUrl(city);
            Scanner scanner = new Scanner((InputStream) url.getContent());
            while (scanner.hasNextLine()){
                news = news.concat(scanner.nextLine());
            }
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(news.getBytes()));
            doc.normalize();

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

            NodeList root = doc.getElementsByTagName("item");
            for(int i = count; i < count + 5 && i < root.getLength(); i++){
                Element element = (Element) root.item(i);
                Node title = element.getElementsByTagName("title").item(0);
                Node link= element.getElementsByTagName("link").item(0);
                InlineKeyboardButton x = new InlineKeyboardButton()
                        .setText("\uD83D\uDC41\u200D\uD83D\uDDE8 " + title.getTextContent())
                        .setUrl(link.getTextContent());
                List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
                inlineKeyboardButtons.add(x);
                rowList.add(inlineKeyboardButtons);
            }
            InlineKeyboardButton button = new InlineKeyboardButton()
                    .setText("⬇ Показать следующие пять новостей ⬇")
                    .setCallbackData("nextFive");
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            buttons.add(button);
            rowList.add(buttons);
            inlineKeyboardMarkup.setKeyboard(rowList);
            bot.sendInfo(new SendMessage().setText("<b>\uD83D\uDCFA Последние новости. " + city + ":</b>\n").setChatId(id)
                    .setReplyMarkup(inlineKeyboardMarkup).setParseMode("HTML"));

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private static URL makeUrl(String city) throws MalformedURLException {
        switch (city.toLowerCase()){
            case "минск":
                return new URL("https://news.tut.by/rss/geonews/minsk.rss");
            case "гродно":
                return new URL("https://news.tut.by/rss/geonews/grodno.rss");
            case "гомель":
                return new URL("https://news.tut.by/rss/geonews/gomel.rss");
            case "брест":
                return  new URL("https://news.tut.by/rss/geonews/brest.rss");
            case "могилёв":
                return new URL("https://news.tut.by/rss/geonews/mogilev.rss");
            case "витебск":
                return new URL("https://news.tut.by/rss/geonews/vitebsk.rss");
            default:
                return new URL("https://news.tut.by/rss/geonews/minsk.rss");
        }
    }
}
