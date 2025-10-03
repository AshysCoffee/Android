package com.example.clases_pruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column //importamos columna
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color //libreria de colores <--- usar esta de preferencia
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() { //main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //DosTextosVerticalesEjercicio()
            //EjemploBox()
            //ImagenConTexto() //llamada del método
            imagenConZoom()
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

        Column(modifier = Modifier.fillMaxSize()) { //fillMaxSize = pantalla completa
            Spacer(modifier = Modifier.height(height = 40.dp)) //en caso de spacer solo se
            // usa height and width
            Row {
                Text(text = "Segundo texto")
                Spacer(modifier = Modifier.width(width = 20.dp))//separacion entre textos
                Text(text = "Tercer texto")
            }

            Column (modifier = Modifier.fillMaxSize().padding(top = 40.dp))//en caso de padding,
            {
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

    fun randomColor():Color{//<-- : tipo es el return
        val rojo = (0..255).random() /*para un intervalo num1..num2*/
        val azul = (0..255).random()
        val verde = (0..255).random()

        return Color(rojo,azul,verde) //constructor de int int int xd
    } //metodo :3


    @Composable
    fun ImagenConTexto() {
        var backgroundBoxColor by remember { mutableStateOf(Color.White)}//para que cambie el color del fondo
        var textPosition by remember { mutableStateOf(Offset(0f,0f)) } //offset para coordenadas
        var imagePosition by remember  { mutableStateOf(Offset(0f,0f)) }
        var anchoPantalla by remember { mutableStateOf(0f)}
        var altoPantalla by remember { mutableStateOf(0f)}
        var anchoTexto by remember { mutableStateOf(0f)}
        var altoTexto by remember { mutableStateOf(0f)}


        Box(
            modifier = Modifier.fillMaxSize().padding(top = 25.dp).background(backgroundBoxColor)   //para añadir cosas se pone . y la siguiente
                .onGloballyPositioned{coordenadas ->
                    anchoPantalla = coordenadas.size.width.toFloat()
                    altoPantalla=coordenadas.size.height.toFloat()

                    if (textPosition == Offset(0f,0f)) {
                        textPosition = Offset(anchoPantalla/2, altoPantalla/2)
                    }
                }
        )
        {

            Image(
                painter = painterResource(id = R.drawable.miamor), //para identificar la imagen
                //que se pone en proyecto/app/src/main/res/drawable
                contentDescription = ("gato alien :3"),
                modifier = Modifier.align(Alignment.CenterStart).fillMaxSize().offset{IntOffset(imagePosition.x.toInt(), imagePosition.y.toInt())}.pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        textPosition += Offset(dragAmount.x, dragAmount.y)
                    }
                }
            )

            Text(
                text = ("love you :D"),
                fontSize = 22.sp,
                color = Color.Yellow,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset{IntOffset(textPosition.x.toInt(), textPosition.y.toInt())}.pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        textPosition += Offset(dragAmount.x,dragAmount.y)}
                }
                    .onGloballyPositioned{coordenadas ->
                        anchoTexto = coordenadas.size.width.toFloat()
                        altoTexto=coordenadas.size.height.toFloat()

                        if (textPosition == Offset(0f,0f)) {
                            textPosition = Offset((anchoPantalla/anchoTexto)/2, (altoPantalla/altoTexto)/2)
                        }
                    }



            )

            Button (onClick = {backgroundBoxColor = randomColor()},modifier = Modifier.align(Alignment.TopCenter)) {
                Text("Cambiar color lol")
            }
        }
    }


    @Composable
    fun imagenConZoom(){
        var escalaImagen by remember {mutableStateOf(1f)}
        var posicionImagen by remember { mutableStateOf(Offset(0f,0f)) }
        var anguloRotacion by remember {mutableStateOf(0f)}

        Box(
            modifier = Modifier.fillMaxSize().pointerInput(Unit){ //<-- esto es para eventos
                detectTransformGestures { _ ,desplazamiento, zoom, rotacion ->
                    escalaImagen *= zoom
                    posicionImagen += desplazamiento
                    anguloRotacion += rotacion

                }
            }
        )

        Image (
            painter = painterResource(id = R.drawable.miamor),
            contentDescription = "Bachira Meguru",
            modifier = Modifier.graphicsLayer(
                scaleX = escalaImagen.coerceIn(0.5f, 3f),
                scaleY = escalaImagen.coerceIn(0.5f, 3f),
                translationX = posicionImagen.x,
                translationY = posicionImagen.y,
                rotationZ = anguloRotacion
            )
        )
    }


    //todo lo que sea de logica no dejan usar el composable

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