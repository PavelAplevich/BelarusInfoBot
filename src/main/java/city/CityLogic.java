package city;

import main.Bot;

public interface CityLogic {

    String getName();

    void getWeather(Bot bot, long id);

    void getNews(Bot bot, long id);

    void getCourse(Bot bot, long id);

}
