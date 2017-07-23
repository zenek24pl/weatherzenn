package com.example.zenek.weatherzen;


import com.example.zenek.weatherzen.models.town.Town;

public class Cfg {
    public static final Server SERVER = Server.TEST;
    public static final Server WEATHER=Server.WEATHER;
    public static final Server TOWNS=Server.TOWNS;



    public enum Server {
        TEST("http://graph.facebook.com"),
        WEATHER("http://api.openweathermap.org/data/2.5/"),
        TOWNS("https://api.myjson.com/bins/");
        String url;

        Server(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }


    public static class RestApi {
        public static final String EVENTS = "https://graph.facebook.com/v2.9/search?type=event&access_token=EAAbCpGRKWngBANzj8cszvB8lsfhYSDaDVSZCPpdm36zmyt7njZCIghqTFmHLqsqqZBYvmTkpBlyLg8H5o6XbH5XYlwmHiBsvpkxvvq2FydD5QVAGdhltSDGEWwX38kPiPtMeLENZCmsvCvywZCPX7dt2vjlUmYzAZD\n";

    }
    public static class WeatherApi{
        public static final String WEATHER="http://api.openweathermap.org/data/2.5/weather?APPID=35e48136bcf88bc2ec0b338b80303f04";
    }
    public static class TownApi{
        public static final String TOWNS="https://api.myjson.com/bins/8xdk7";
    }

}
