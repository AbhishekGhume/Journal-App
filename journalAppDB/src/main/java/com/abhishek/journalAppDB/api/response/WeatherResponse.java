package com.abhishek.journalAppDB.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_dir")
        private String windDir;
        private int humidity;
        private int feelslike;

    }
}


//Below is how JSON file looks like

//{
//        "request": {
//        "type": "City",
//        "query": "Karad, India",
//        "language": "en",
//        "unit": "m"
//        },
//        "location": {
//        "name": "Karad",
//        "country": "India",
//        "region": "Maharashtra",
//        "lat": "17.283",
//        "lon": "74.200",
//        "timezone_id": "Asia/Kolkata",
//        "localtime": "2024-12-31 23:29",
//        "localtime_epoch": 1735687740,
//        "utc_offset": "5.50"
//        },
//        "current": {
//        "observation_time": "05:59 PM",
//        "temperature": 20,
//        "weather_code": 113,
//        "weather_icons": [
//        "https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0008_clear_sky_night.png"
//        ],
//        "weather_descriptions": [
//        "Clear "
//        ],
//        "wind_speed": 6,
//        "wind_degree": 60,
//        "wind_dir": "ENE",
//        "pressure": 1016,
//        "precip": 0,
//        "humidity": 48,
//        "cloudcover": 3,
//        "feelslike": 20,
//        "uv_index": 0,
//        "visibility": 10,
//        "is_day": "no"
//        }
//        }




