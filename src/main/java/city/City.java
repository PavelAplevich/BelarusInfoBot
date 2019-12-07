package city;

import logics.CourseLogic;
import logics.NewsLogic;
import logics.WeatherLogic;
import main.Bot;
import main.BotButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class City implements CityLogic {

    protected City() {
    }

    public static City getCity(String name) {
        switch (name.toLowerCase()) {
            case "minsk":
                return new Minsk();
            case "grodno":
                return new Grodno();
            case "gomel":
                return new Gomel();
            case "brest":
                return new Brest();
            case "mogilev":
                return new Mogilev();
            case "vitebsk":
                return new Vitebsk();
            default:
                return new Minsk();
        }
    }

    public void chooseChoice(Bot bot, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите интересующий вас раздел\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\u2b07\t\t\t\t\t\t\t\t\t\t\u2b07");
        sendMessage.setReplyMarkup(BotButtons.getInlineKeyboard());
        sendMessage.setChatId(message.getChatId());
        bot.sendInfo(sendMessage);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void getWeather(Bot bot, long id) {
        WeatherLogic.makeWeather(bot, id, this.getName());
    }

    @Override
    public void getNews(Bot bot, long id) {
        NewsLogic.makeNews(bot, id, this.getName(), 5);
    }

    @Override
    public void getCourse(Bot bot, long id) {
        CourseLogic.makeCourse(bot, id, this.getName());
    }
}
