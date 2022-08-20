package retrofit;

import model.RespustaAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballApi {

    @GET("leagues")
    Call<RespustaAPI> getTeams();

}

