package com.abhishek.journalAppDB.service;

import com.abhishek.journalAppDB.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Service
//public class WeatherService {
////    private static final String apiKey = "7b1ff96f599a3b831505766aec20947a"; // instead of hard coding use @Value annotation and write api key in application.properties file or application.yml file
//    @Value("${weather.api.key}")
//    private String apiKey;
//    private static final String api = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private RedisService redisService;
//
//    public WeatherResponse getWeather(String city) {
//        WeatherResponse weatherResponse =  redisService.get(city, WeatherResponse.class);
//        if(weatherResponse != null) {
//            return weatherResponse;
//        } else{
//            String finalAPI = api.replace("CITY", city).replace("API_KEY", apiKey);
//            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);  //null = we are not sending any header in request
//            //WeatherResponse.class = Deserializing JSON response in pojo class named as WeatherResponse
//
//            if(response != null && response.getBody() != null) {
//                redisService.set(city, response.getBody(), 300L);
//                return response.getBody();
//            }
//
//            //if we want to POST instead of GET and also want to give header we can do following
////        String requestBody = "{\n" +
////                "    \"username\" : \"Abhishek\",\n" +
////                "    \"password\" : \"Abhishek@2004\"\n" +
////                "}";
////        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
//            //instead of string below
////        HttpHeaders httpHeaders = new HttpHeaders();   // api expects some headers so we have to give them
////        httpHeaders.set("key", "value");
////        User user = User.builder().username("Abhishek").password("Abhishek@2004").build();
////        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
////        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
//            return null;
//        }
//    }
//}





@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    private static final String api = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get(city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = api.replace("CITY", city).replace("API_KEY", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

            if(response != null && response.getBody() != null) {
                // Store only the response body, not the entire ResponseEntity
                redisService.set(city, response.getBody(), 300L);
                return response.getBody();
            }
            return null;
        }
    }
}