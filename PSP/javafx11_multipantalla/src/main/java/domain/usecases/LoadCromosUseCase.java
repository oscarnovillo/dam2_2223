package domain.usecases;

import dao.DaoCromos;
import domain.modelo.Cromo;
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
}
