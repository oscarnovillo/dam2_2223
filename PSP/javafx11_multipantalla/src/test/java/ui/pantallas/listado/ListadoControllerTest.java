package ui.pantallas.listado;

import domain.modelo.Cromo;
import domain.usecases.LoadCromosUseCase;
import domain.usecases.LoginUseCase;
import domain.usecases.LoginUseCaseImpl;
import io.github.palexdev.materialfx.controls.MFXTableView;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import ui.pantallas.login.LoginController;
import ui.pantallas.login.LoginViewModel;
import ui.pantallas.principal.PrincipalController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class ListadoControllerTest {


    //class under test
    private ListadoController listado;


    //@Mock
    private PrincipalController principalController; // = mock(PrincipalController.class);;

    //@Mock
    private LoadCromosUseCase useCase; // = mock(LoginUseCase.class);


    private ListadoViewModel viewModel;

    Cromo c = new Cromo("Cromo 1",1,"des");
    Cromo c1 = new Cromo("Cromo2",2,"des");

    @Captor
    ArgumentCaptor<Cromo> captor;

    @BeforeEach
    void setUp() {
        //principalController = mock(PrincipalController.class);
    }

    @Start
    public void start(Stage stage) throws IOException {

        principalController = mock(PrincipalController.class);
        useCase = mock(LoadCromosUseCase.class);
        viewModel = new ListadoViewModel(useCase);
        when(useCase.loadCromos()).thenReturn(List.of(c,c1));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(param -> new ListadoController(viewModel));
        InputStream s = getClass().getResourceAsStream("/fxml/listado.fxml");
        Parent fxmlParent = fxmlLoader.load(s);
        listado = fxmlLoader.getController();
        listado.setPrincipalController(principalController);
        ;

        stage.setScene(new Scene(fxmlParent));
        stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        stage.show();

    }

    @Test
    void testListadoController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga

        //then
        MFXTableView<Cromo> tabla = robot.lookup("#tabla").queryAs(MFXTableView.class);
        assertThat(tabla.getItems().size()).isEqualTo(2);
        assertThat(tabla.getItems()).containsExactly(c,c1);



    }


    @Test
    void testClickTablaController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga

        //then
        MFXTableView<Cromo> tabla = robot.lookup("#tabla").queryAs(MFXTableView.class);

        tabla.getCell(0).setId("cell0");
        tabla.getCell(1).setId("cell1");
//        Node n = robot.lookup(".table-row-cell").query();
        robot.clickOn("#cell0");
        robot.clickOn("#cell1");

//        robot.interact(() -> tabla.getSelectionModel().selectItem(c));
        //Platform.runLater(() -> tabla.getSelectionModel().selectItem(c));
        //tabla.getSelectionModel().selectItem(c);
        //robot.clickOn("#tabla");

        robot.sleep(1000);

        robot.clickOn("#ver");

        verify(principalController).onSeleccionCromo(captor.capture());

        assertThat(captor.getValue()).isEqualTo(c1);




    }

    @Test
    void testClickTablaNormalController(FxRobot robot) {
        //given
//        Cromo c = new Cromo("Cromo 1",1,"des");
//        when(useCase.loadCromos()).thenReturn(List.of(c));

        //when
        //se carga

        //then
        TableView<Cromo> tabla = robot.lookup("#tablaNormal").queryAs(TableView.class);

        robot.interact(() -> tabla.getSelectionModel().select(1));

//        Node n = robot.lookup(".table-row-cell").query();
        robot.clickOn("#verNormal");
//        robot.clickOn("#cell1");
//        robot.interact(() -> tabla.getSelectionModel().selectItem(c));
        //Platform.runLater(() -> tabla.getSelectionModel().selectItem(c));
        //tabla.getSelectionModel().selectItem(c);
        //robot.clickOn("#tabla");

        //robot.sleep(1000);
        verify(principalController).onSeleccionCromo(captor.capture());

        assertThat(captor.getValue()).isEqualTo(c1);




    }

}
