package dao.impl;

import common.config.Configuracion;
import dao.DaoCromos;
import dao.retrofit.ProducesRetrofit;
import dao.retrofit.llamadas.JokeApi;
import dao.retrofit.modelo.ResponseJoke;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.vavr.control.Either;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoCromosImpl implements DaoCromos {

    private Configuracion configuracion;

    private JokeApi jokeApi;


    @Inject
    public DaoCromosImpl(Configuracion configuracion, @Named("uno") JokeApi jokeApi) {
        this.configuracion = configuracion;
        this.jokeApi = jokeApi;

    }

    @Override
    public List<Cromo> loadCromos() {
        return List.of(
                new Cromo("marvel", 1, "descripcion"),
                new Cromo("marvel", 2, "descripcion"),
                new Cromo("marvel", 3, "descripcion"),
                new Cromo("marvel", 4, "descripcion"));
    }

    public Either<String,MiJokes> llamadaRettrofit() {



        Either<String,MiJokes> respuesta = null;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }

        Response<ResponseJoke> r = null;
        try {
            r = jokeApi.getAnyJoke("aa","es").execute();

            if (r.isSuccessful()) {
                ResponseJoke rj = r.body();
                MiJokes joker = null;
                if (rj.getType().equals("twoparts")) {
                    joker = new MiJokes(rj.getId(), rj.getSetup()+":::"+rj.getDelivery());
                }
                else {
                    joker = new MiJokes(rj.getId(), rj.getJoke());
                }
                respuesta = Either.right(joker);
            } else {
                respuesta = Either.left(r.message());
            }

        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }


        return respuesta;

    }
}
