package city;

import action.CourseLogic;
import action.NewsLogic;
import action.WeatherLogic;
import main.Bot;

public class City implements CityLogic {

     protected City(){
    }

    public static City getCity(String name){
         switch (name.toLowerCase()){
             case "минск":
                 return new Minsk();
             case "гродно":
                 return new Grodno();
             case "гомель":
                 return  new Gomel();
             case "брест":
                 return new Brest();
             case "могилёв":
                 return new Mogilev();
             case "витебск":
                 return new Vitebsk();
             default:
                 return  new Minsk();
         }
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
