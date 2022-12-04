package cliente.data;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import cliente.data.modelo.Alumno;
import cliente.data.network.ConfigurationSingleton_OkHttpClient;
import cliente.data.servicios.EstupidoAPI;

public class DaoEstupido extends DaoGenerics{


    private CacheAuthorization cache;

    public DaoEstupido(CacheAuthorization cache) {
        this.cache = cache;
    }

    public Single<Either<String,Alumno>> getAlumno(){
        EstupidoAPI estu = ConfigurationSingleton_OkHttpClient.getInstance(cache).create(EstupidoAPI.class);



        return safeSingleApicall(estu.getAlumno())
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
