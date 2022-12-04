package cliente.data.servicios;

import io.reactivex.rxjava3.core.Single;
import cliente.data.modelo.Alumno;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface EstupidoAPI {


    @GET("alumno")
    Single<Alumno> getAlumno();


    @GET("alumno/filtro")
    Single<String> getJWT();

    @GET("alumno/verify")
    Single<String> getVerify();
}
