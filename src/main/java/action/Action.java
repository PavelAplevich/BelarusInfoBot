package action;

import city.City;
import main.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Action {

    private static City city;

    public static void doAction(Bot bot, Message message) {
        if(message.getText().toLowerCase().equals("могилев")){

        }
        switch (message.getText().toLowerCase()){
            case "минск":
            case "гродно":
            case "гомель":
            case "брест":
            case "могилёв":
            case "витебск":
               city = City.getCity(message.getText());
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
                NewsLogic.makeNews(bot, message.getChatId(), city.getName(), 0);
                break;
            case "погода":
                WeatherLogic.makeWeather(bot, message.getChatId(), city.getName());
                break;
            case "курс":
            case "курсы валют":
                CourseLogic.makeCourse(bot, message.getChatId(), city.getName());
                break;
            default:
                ActionLogic.doNotUnderstandYou(bot, message);

        }
    }

    public static void doCallBack(Bot bot, CallbackQuery callbackQuery){
        Long id = callbackQuery.getMessage().getChatId();
        String message = callbackQuery.getData();
        switch (message){
            case "news":
                city.getNews(bot, id);
                break;
            case "weather":
                city.getWeather(bot, id);
                break;
            case "course":
                city.getCourse(bot, id);
                break;
            case "nextFive":
                NewsLogic.makeNews(bot, id, city.getName(), 5);
                break;
            default:
                System.out.printf(message);
        }
    }
}
