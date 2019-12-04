package main;

import logics.*;
import city.City;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Action {

    private static City city;

    public static void doAction(Bot bot, Message message) {
        String isCity = InputLogic.getCityName(message.getText().toLowerCase());
        if(isCity.equals("unknown")){
            switch (message.getText().toLowerCase()){
                case "перейти к выбору города":
                    ActionLogic.getCity(bot, message);
                    break;
                case "меню":
                    city.chooseCity(bot, message);
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
        } else {
            switch (isCity){
                case "minsk":
                case "grodno":
                case "gomel":
                case "brest":
                case "mogilev":
                case "vitebsk":
                    city = City.getCity(isCity);
                    city.chooseCity(bot, message);
                    break;
            }
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
            case "dog":
                DogLogic.makeDog(bot, id);
                break;
            default:
                System.out.print(message);
        }
    }
}
