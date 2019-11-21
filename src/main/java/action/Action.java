package action;

import main.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Action {
    private static String city = "Минск";

    public void doAction(Bot bot, Message message) {
        switch (message.getText().toLowerCase()){
            case "минск":
                city = "Минск";
                ActionLogic.chooseCity(bot, message);
                break;
            case "гродно":
                city = "Гродно";
                ActionLogic.chooseCity(bot, message);
                break;
            case "гомель":
                city = "Гомель";
                ActionLogic.chooseCity(bot, message);
                break;
            case "брест":
                city = "Брест";
                ActionLogic.chooseCity(bot, message);
                break;
            case "могилёв":
                city = "Могилёв";
                ActionLogic.chooseCity(bot, message);
                break;
            case "витебск":
                city = "Витебск";
                ActionLogic.chooseCity(bot, message);
                break;
            case "перейти к выбору города":
                ActionLogic.getCity(bot, message);
                break;
            case "меню":
                ActionLogic.chooseCity(bot, message);
            case "/settings":
            case "перейти в настройки":
                ActionLogic.settings();
                break;
            case "/help":
                ActionLogic.showHelp(bot, message);
                break;
            case "/start":
                ActionLogic.sayHello(bot, message);
                break;
            case "новости":
                NewsLogic.makeNews(bot, message.getChatId(), city);
                break;
            case "погода":
                WeatherLogic.makeWeather(bot, message.getChatId(), city);
                break;
            case "афиша":
                AfficheLogic.makeAffiche(bot, message.getChatId(), city);
                break;
            case "курс":
            case "курсы валют":
                CourseLogic.makeCourse(bot, message.getChatId(), city);
                break;
            default:
                ActionLogic.doNotUnderstandYou(bot, message);

        }
    }

    public void doCallBack(Bot bot, CallbackQuery callbackQuery){
        Long id = callbackQuery.getMessage().getChatId();
        String message = callbackQuery.getData();
        switch (message){
            case "news":
                NewsLogic.makeNews(bot, id, city);
                break;
            case "weather":
                WeatherLogic.makeWeather(bot, id, city);
            case "affiche":
                AfficheLogic.makeAffiche(bot, id, city);
                break;
            case "course":
                CourseLogic.makeCourse(bot, id, city);
                break;
            default:
                System.out.printf(message);
        }
    }
}
