package stepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Main;
import model.Response;
import model.Weather;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherStepDefs {
    private String cityName;
    private String country;
    private Response response;


    @Given("show test name")
    public void show_test_name() {
        System.out.println("Hello, World!");
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
        response = requester.requestWeather(cityName, country);
        //System.out.println(response.getCoord().getLat());
        //System.out.println(requester.requestWeather(cityName, country));

    }

    @Then("lon is: {float}")
    public void check_lon(float lon) {

        assertEquals(lon, response.getCoord().getLon(), "Wrong lon");
    }

    @Then("lat is: {float}")
    public void check_lat(float lat) {

        assertEquals(lat, response.getCoord().getLat(), "Wrong lat");
    }

    @Then("weather is:")
    public void check_weather(Map<String, String> params) {

        Weather weather = response.getWeathers().get(0);
        assertEquals(Long.parseLong(params.get("id")), weather.getId(), "Wrong weather ID");
        assertEquals(params.get("main"), weather.getMain(), "Wrong Main info");
        assertEquals(params.get("description"), weather.getDescription(), "Wrong weather Description");
        assertEquals(params.get("icon"), weather.getIcon(), "Wrong weather Icon");
    }

    @Then("base is: {string}")
    public void check_base(String base) {

        assertEquals(base, response.getBase(), "Wrong Base");
    }

    @Then("main is:")
    public void check_main(Map<String,Float> params) {

        assertEquals(params.get("temp"),response.getMain().getTemp(), "Wrong Temp");
        assertEquals(params.get("pressure"),response.getMain().getPressure(), "Wrong Pressure");
        assertEquals(params.get("humidity"),response.getMain().getHumidity(), "Wrong Humidity");
        assertEquals(params.get("temp_min"),response.getMain().getTemp_min(), "Wrong Temp min");
        assertEquals(params.get("temp_max"),response.getMain().getTemp_max(), "Wrong Temp max");
    }

    @Then("visibility is: {int}")
    public void check_visibility(int visibility) {

        assertEquals(visibility, response.getVisibility(), "Wrong Visibility");
    }

    @Then("wind is:")
    public void check_wind(Map<String,Float> params) {

        assertEquals(params.get("speed"),response.getWind().getSpeed(), "Wrong Speed");
        assertEquals(params.get("deg"),response.getWind().getDeg(), "Wrong Deg");
    }

    @Then("clouds is:")
    public void check_clouds(Map<String,Float> params) {

        assertEquals(params.get("all"),response.getClouds().getAll(), "Wrong All");
    }

    @Then("dt is {float}")
    public void check_dt(Float dt) {

        assertEquals(dt, response.getDt(), "Wrong Dt");
    }

    @Then("sys is:")
    public void check_sys(Map<String,String> params) {

        assertEquals(Float.parseFloat(params.get("type")),response.getSys().getType(), "Wrong Type");
        assertEquals(Float.parseFloat(params.get("id")),response.getSys().getId(), "Wrong Id");
        assertEquals(Float.parseFloat(params.get("message")),response.getSys().getMessage(), "Wrong Message");
        assertEquals(params.get("country"),response.getSys().getCountry(), "Wrong Country");
        assertEquals(Float.parseFloat(params.get("sunrise")),response.getSys().getSunrise(), "Wrong Sunrise");
        assertEquals(Float.parseFloat(params.get("sunset")),response.getSys().getSunset(), "Wrong Sunset");
    }

    @Then("id is {long}")
    public void check_id(long id) {

        assertEquals(id, response.getId(), "Wrong ID");
    }

    @Then("name is {string}")
    public void check_name(String name) {

        assertEquals(name, response.getName(), "Wrong Name");
    }

    @Then("cod is {long}")
    public void check_cod(long cod) {
        
        assertEquals(cod, response.getCod(), "Wrong Cod");
    }

}
