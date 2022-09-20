package ui;

import com.google.gson.*;

import com.squareup.moshi.Moshi;
import model.RespuestaAPI;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit.FootballApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {

        //llamada con retrofit
        //

        OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("https://api-football-standings.azharimm.site/leagues")
                    .build();
   RespuestaAPI r ;
   String respJson;
        try (okhttp3.Response response = client.newCall(request).execute()) {
            respJson = response.body().string();
        }



        OkHttpClient clientOK = new OkHttpClient.Builder()
                .readTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .callTimeout(Duration.of(10, ChronoUnit.MINUTES))
                .connectTimeout(Duration.of(10, ChronoUnit.MINUTES))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_1_1,Protocol.HTTP_2))
                .build();

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString())).registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString())
        ).create();

        //r= gson.fromJson(respJson, RespustaAPI.class);

        Moshi moshi = new Moshi.Builder().build();

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api-football-standings.azharimm.site/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
               // .client(client)
                .build();


        FootballApi f =  retro.create(FootballApi.class);


        Response<RespuestaAPI> response = f.getTeams().execute();

        if (response.isSuccessful()) {
            System.out.println(response.headers());
            System.out.println(response.body().data().stream().map(dataItem -> dataItem.name()).collect(Collectors.joining(",")));
//            System.out.println(r);
            System.out.println(respJson);
        }
        else
            System.out.println(response.errorBody().string());

    }
}
