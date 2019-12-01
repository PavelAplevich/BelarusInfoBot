package city;

import action.CourseLogic;
import action.NewsLogic;
import action.WeatherLogic;
import main.Bot;

public class Minsk extends City {

    @Override
    public String getName() {
        return "Минск";
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
