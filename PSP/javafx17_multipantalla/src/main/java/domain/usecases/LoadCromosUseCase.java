package domain.usecases;

import dao.DaoCromos;
import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class LoadCromosUseCase {

    private DaoCromos dao ;

    @Inject
    public LoadCromosUseCase(DaoCromos dao) {
        this.dao = dao;
    }

    public List<Cromo> loadCromos(){
        return dao.loadCromos();
    }

    public Either<String, MiJokes> llamadaRetrofit(){
        return dao.llamadaRettrofit();
    }
}
