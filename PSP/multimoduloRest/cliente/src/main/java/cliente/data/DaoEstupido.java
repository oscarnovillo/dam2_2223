package cliente.data;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import cliente.data.network.ConfigurationSingleton_OkHttpClient;
import cliente.data.servicios.EstupidoAPI;

import java.util.List;

public class DaoEstupido extends DaoGenerics{


    private CacheAuthorization cache;

    public DaoEstupido() {
        this.cache = new CacheAuthorization();
    }

    public Single<Either<String, List<Usuario>>> getUsuario(){
        EstupidoAPI estu = ConfigurationSingleton_OkHttpClient.getInstance(cache).create(EstupidoAPI.class);

        return safeSingleApicall(estu.getUsers())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());
    }

    public Single<Either<String, Boolean>> getLogin(){
        EstupidoAPI estu = ConfigurationSingleton_OkHttpClient.getInstance(cache).create(EstupidoAPI.class);

        return safeSingleApicall(estu.getLogin())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());
    }


    public Single<Either<String,String>> getJwt(){
        EstupidoAPI estu = ConfigurationSingleton_OkHttpClient.getInstance(cache).create(EstupidoAPI.class);

        return safeSingleApicall(estu.getJWT())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());

    }

    public Single<Either<String,String>> getVerify(){
        EstupidoAPI estu = ConfigurationSingleton_OkHttpClient.getInstance(cache).create(EstupidoAPI.class);

        return safeSingleApicall(estu.getVerify())
                //.map(either -> either.map(alumno -> alumno.setNombre("mapeado")))
                .subscribeOn(Schedulers.io());

    }


}
