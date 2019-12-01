package city;

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

    }

    @Override
    public void getNews(Bot bot, long id) {

    }

    @Override
    public void getCourse(Bot bot, long id) {

    }
}
