package ui.pantallas.listado;

import domain.modelo.Cromo;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.Comparator;

public class ListadoController extends BasePantallaController {


    private final ListadoViewModel viewModel;
    public MFXButton ver;
    @FXML
    private TableColumn<Cromo, String> colCol;
    @FXML
    private TableColumn<Cromo, String> colDes;
    @FXML
    private TableColumn<Cromo, Integer> colNum;

    @FXML
    private TableView<Cromo> tablaNormal;
    @FXML
    private MFXTextField txtNombre;
    @FXML
    private MFXTableView<Cromo> tabla;

    @Inject
    public ListadoController(ListadoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void ver(ActionEvent actionEvent) {
//        getPrincipalController().cargarPantalla(Pantallas.DETALLE);
        tabla.getSelectionModel().getSelection().values().stream().findFirst()
                .ifPresent(cromo -> getPrincipalController().onSeleccionCromo(cromo));


    }

    public void initialize() {
// tabla materialFX
        MFXTableColumn<Cromo> collecionColumn = new MFXTableColumn<>("collecion", true, Comparator.comparing(Cromo::getCollecion));
        MFXTableColumn<Cromo> descriptionColumn = new MFXTableColumn<>("descripcion", true, Comparator.comparing(Cromo::getDescripcion));
        MFXTableColumn<Cromo> numberColumn = new MFXTableColumn<>("numero", true, Comparator.comparing(Cromo::getNumero));
        collecionColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getCollecion));
        descriptionColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getDescripcion));
        numberColumn.setRowCellFactory(persona -> new MFXTableRowCell<>(Cromo::getNumero));

        colCol.setCellValueFactory(new PropertyValueFactory<>("collecion"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));


        tablaNormal.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Cromo>) change -> {
            txtNombre.setText("" + change.getList().get(0).getNumero());
        });
        tabla.getSelectionModel().selectionProperty().addListener((observableValue, cromo, cromoNew) -> {
            if (cromoNew != null) {
                txtNombre.setText(cromoNew.values().stream().findFirst().get().getCollecion());
            }
        });

        tabla.getTableColumns().addAll(collecionColumn, descriptionColumn, numberColumn);

        cambiosEstado();
        viewModel.loadCromos();
        // tabla.setItems(viewModel.getState().get().getPersonas());
    }

    private void cambiosEstado() {
        viewModel.getState().addListener((observableValue, listadoState, listadoStateNew) -> {
            if (listadoStateNew.getError() != null) {
                getPrincipalController().sacarAlertError(listadoStateNew.getError());
            }
            if (listadoStateNew.getCromos() != null) {
                tabla.getItems().clear();
                tabla.getItems().addAll(listadoStateNew.getCromos());
                tablaNormal.getItems().clear();
                tablaNormal.getItems().addAll(listadoStateNew.getCromos());


            }


        });
    }

    public void verNormal(ActionEvent actionEvent) {

        if (tablaNormal.getSelectionModel().getSelectedItem() != null)
            getPrincipalController().onSeleccionCromo(tablaNormal.getSelectionModel().getSelectedItem());


    }
}
