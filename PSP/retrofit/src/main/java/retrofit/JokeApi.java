package retrofit;

import model.RespuestaAPI;
import model.jokes.ResponseJoke;

import model.jokes.ResponseJokeSimple;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public interface JokeApi {


    @GET("Any")
    Call<ResponseJoke> getAnyJoke(@Query("lang") String lang);

    @GET("Any")
    Call<ResponseJokeSimple> getAnyJokeSimple(@Query("lang") String lang, @Query("type") String type);

    @GET("Programming")
    Call<ResponseJoke> getProgrammingJoke(@Query("lang") String lang);

    @DELETE
    Call<Response<Void>> deleteJoke(@Query("id") int id);



}
