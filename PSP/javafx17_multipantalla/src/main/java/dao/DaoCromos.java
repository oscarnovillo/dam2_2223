package dao;

import domain.modelo.Cromo;
import domain.modelo.MiJokes;
import io.vavr.control.Either;

import java.util.List;

public interface DaoCromos {

    List<Cromo> loadCromos();

    Either<String, MiJokes> llamadaRettrofit();
}
