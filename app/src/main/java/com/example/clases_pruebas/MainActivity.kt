package com.example.clases_pruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column //importamos columna
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color //libreria de colores
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() { //main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //DosTextosVerticalesEjercicio()
            //EjemploBox()
            ImagenConTexto() //llamada del método
        }
    }
//}

    /*
@Composable
fun DosTextosVerticales(){

    Column (modifier = Modifier.fillMaxSize().padding(top=40.dp)){
        Row (){
            Text(text="Primer texto")
            Spacer(modifier= Modifier.width(width = 20.dp))
            Text(text="Segundo texto")
        }

        Spacer(modifier= Modifier.height(height = 30.dp))
        Text(text="Mi Primer texto")
        Text(text="Mi Segundo texto")
    }
}

@Preview
@Composable
fun DosTextosVerticalesPreview (){
    DosTextosVerticales()
}

*/


//Ejercicio1 - Esto es como crear métodos fuera del main!

    @Composable
    fun DosTextosVerticalesEjercicio() {

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(height = 40.dp)) //en caso de spacer solo se
            // usa height and width
            Row {
                Text(text = "Segundo texto")
                Spacer(modifier = Modifier.width(width = 20.dp))
                Text(text = "Tercer texto")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {//en caso de padding,
                // hay que indicar desde que lado se desea hacer tanto top, bottom, left, right
                Text(text = "Primer texto")
                Text(text = "Segundo texto")
            }
        }
    }

    @Composable
    fun EjemploBox() { // el error esta en que hay que ponerlo entre llaves
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {
            Text("Parte Superior Izquierda", modifier = Modifier.align(Alignment.TopStart))
            Text("Parte Central", modifier = Modifier.align(Alignment.Center))
            Text("Parte Inferior Derecha", modifier = Modifier.align(Alignment.BottomEnd))
            }
    }



    @Composable
    fun ImagenConTexto() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.miamor),
                contentDescription = ("Bachira Meguru my baby<3"),
                modifier = Modifier.align(Alignment.Center)
            )

            Text(
                text = "Bachira Meguru :3",
                fontSize = 22.sp,
                color = Color.Yellow,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )

        }
    }

    //previsualización de métodos

    @Preview
    @Composable
    fun PreviewEjemploBox() {
        EjemploBox()
    }

    @Preview
    @Composable
    fun DosTextosVerticalesEjercicioPreview() {
        DosTextosVerticalesEjercicio()
    }

    @Preview
    @Composable
    fun PreviewImagenConTexto() {
        ImagenConTexto()
    }

}