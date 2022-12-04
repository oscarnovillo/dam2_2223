package cliente.data.servicios;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import java.util.List;

public interface EstupidoAPI {


    @GET("users")
    Single<List<Usuario>> getUsers();

    @GET("login")
    Single<Boolean> getLogin();

    @GET("alumno/filtro")
    Single<String> getJWT();

    @GET("alumno/verify")
    Single<String> getVerify();
}
