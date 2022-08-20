package ui;

import com.google.gson.*;

import model.RespustaAPI;
import retrofit.FootballApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) throws IOException {

        //llamada con retrofit
        //

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString())
        ).create();


        FootballApi footballApi = new Retrofit.Builder()
                .baseUrl("https://api-football-standings.azharimm.site/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(FootballApi.class);




        Response<RespustaAPI> response = footballApi.getTeams().execute();

        System.out.println(response.headers().toString());
        System.out.println(response.body().toString());

    }
}
