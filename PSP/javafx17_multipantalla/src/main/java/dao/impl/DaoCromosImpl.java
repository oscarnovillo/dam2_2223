package dao.impl;

import common.config.Configuracion;
import dao.DaoCromos;
import dao.retrofit.llamadas.JokeApi;
import dao.retrofit.modelo.ResponseJoke;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

public class DaoCromosImpl implements DaoCromos {

    private Configuracion configuracion;

    private JokeApi jokeApi;

    @Inject
    public DaoCromosImpl(Configuracion configuracion, JokeApi jokeApi) {
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

        Response<ResponseJoke> r = null;
        try {
            r = jokeApi.getAnyJoke("AÇ(=(JKSDFL SDLFJ","es").execute();

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
