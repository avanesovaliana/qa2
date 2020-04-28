package stepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Response;
import model.Weather;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.util.List;

public class WeatherStepDefs {
    private String cityName;
    private String country;
    private Response response;


    @Given("show test name")
    public void show_test_name() {
        System.out.println("Hello, ogurec!");
    }

    @Given("city {string}")
    public void set_city(String cityName) {
       this.cityName = cityName;
    }

    @Given("country {string}")
    public void set_country(String country) {
        this.country = country;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.requestWeather(cityName,country);
        //System.out.println(response.getCoord().getLat());
        //System.out.println(requester.requestWeather(cityName, country));
    }

    @Then("lon is {float}")
    public void check_lon(float lon) {
        Assertions.assertEquals(lon, response.getCoord().getLon(), "Wrong lon");
    }

    @Then("lat is {float}")
    public void check_lat(float lat) {
        Assertions.assertEquals(lat, response.getCoord().getLat(), "Wrong lat");
    }
    @Then("temp is {float}")
    public  void check_temp(float temp){
        Assertions.assertEquals(temp, response.getMain().getTemp(),"Wrong temp");
    }
    @Then("speed of wind is {float}")
    public void check_speed(float speed){
        Assertions.assertEquals(speed,response.getWind().getSpeed(), "Wrong speed");
    }
    @Then("sunrise of sys is {int}")
    public void check_sunrise(int sunrise){
        Assertions.assertEquals(sunrise,response.getSys().getSunrise(), "Wrong sunrise");
    }
    @Then("weather are:")
    public void check_weather(List<Weather> weathers){
        Assertions.assertEquals(weathers, response.getWeather(), "Wrong");
    }
}
