package city;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {

    @Test
    public void getCity(){
        Object object = City.getCity("something strange");
        assertTrue(object instanceof Minsk);
    }

    @Test
    public void getWeather() {
    }

    @Test
    public void getNews() {
    }

    @Test
    public void getCourse() {
    }
}