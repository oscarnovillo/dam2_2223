package retrofit;

import model.RespuestaAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballApi {

    @GET("leagues")
    Call<RespuestaAPI> getTeams();

}

