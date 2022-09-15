package retrofit;

import model.RespuestaAPI;
import model.jokes.ResponseJoke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeApi {


    @GET("Any")
    Call<ResponseJoke> getAnyJoke(@Query("lang") String lang);
}
