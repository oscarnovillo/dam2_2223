package ui.pantallas.listado;

import domain.modelo.Cromo;
import domain.usecases.LoadCromosUseCase;
import domain.usecases.LoginUseCase;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ui.pantallas.login.LoginState;

import java.util.List;

public class ListadoViewModel {


    private LoadCromosUseCase loginUseCase;

    @Inject
    public ListadoViewModel(LoadCromosUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;

        ListadoState ls = null;
        List<Cromo> cromos = loginUseCase.loadCromos();
        if (cromos == null)
            ls = new ListadoState(null, "no se han podido cargar cromos");
        else
            ls = new ListadoState(cromos, null);

        _state = new SimpleObjectProperty<>(new ListadoState(null, null));
    }

    private final ObjectProperty<ListadoState> _state;

    public ReadOnlyObjectProperty<ListadoState> getState() {
        return _state;
    }

    public void loadCromos() {
        ListadoState ls = null;
        List<Cromo> cromos = loginUseCase.loadCromos();
        if (cromos == null)
            ls = new ListadoState(null, "no se han podido cargar cromos");
        else
            ls = new ListadoState(cromos, null);
        _state.setValue(ls);
    }

    public void llamadaRetrofit() {
        ListadoState ls = null;
        var cromos = loginUseCase.llamadaRetrofit();
//        if (cromos.isLeft())
//            ls = new ListadoState(null, cromos.getLeft());
//        else
//            ls = new ListadoState(cromos.get(), null);

//        cromos.peek(mijoke -> {
//            //_state.setValue(new ListadoState(mijoke, null));
//        }).peekLeft(error -> {
//            _state.setValue(new ListadoState(null, error));
//        });

    }

}
