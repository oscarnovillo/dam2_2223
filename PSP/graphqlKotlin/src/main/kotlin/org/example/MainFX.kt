package org.example

import javafx.application.Application

import javafx.stage.Stage
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

import javafx.scene.layout.AnchorPane



class MainFX  : Application() {



    override fun start(primaryStage: Stage?) throws Exception {
        try{
        var  loaderMenu: FXMLLoader? = FXMLLoader(
            javaClass.getResource("/fxml/principal.fxml"))

        var  root: AnchorPane? = loaderMenu?.load()
        var  scene: Scene? = Scene(root)
        primaryStage?.setTitle("Rick Morty")
        primaryStage?.setScene(scene)
        primaryStage?.show()
        primaryStage?.setResizable(false)
            }catch (e: Exception){
                e.printStackTrace()
            }
    }

}

fun main(args: Array<String>) {
    Application.launch(MainFX::class.java, *args)
}
