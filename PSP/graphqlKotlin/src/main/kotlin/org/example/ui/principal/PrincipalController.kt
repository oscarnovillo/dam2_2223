package org.example.ui.principal

import com.apollographql.apollo3.ApolloClient
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.ListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.rickMorty.FirstQuery
import java.net.URL
import java.util.*

class PrincipalController : Initializable{

    @FXML
    lateinit var cargando: Label

    @FXML
    lateinit var list: ListView<String>


    @FXML
    fun cargar() {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()

        GlobalScope.launch(Dispatchers.JavaFx) {
            cargando.isVisible = true
            list.items.clear()
            apolloClient.query(FirstQuery()).execute().data?.let {data ->
                data.characters?.results?.map { list.items.add(it?.name) }
            }
            cargando.isVisible = false
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        cargando.isVisible = false
    }


}
