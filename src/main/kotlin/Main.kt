
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import kotlin.system.exitProcess

fun main() = application {

    val icon = BitmapPainter(useResource("IconoPredeterminado.png", ::loadImageBitmap))
    val gestorFichero = FileManagement()
    val studentsFile = File("studentList.txt")
    var viewmodelElegido by remember { mutableStateOf<IStudentViewModel?>(null) }

    val studentRepository = StudentRepository()
    val viewmodelFile= StudentVMFile(gestorFichero, studentsFile)
    val viewmodelDB = StudentVMDB(studentRepository)
    val windowState = GetWindowState(
        windowWidth = 800.dp,
        windowHeight = 800.dp
    )

    if (viewmodelElegido == null){
        Window(
            onCloseRequest = {exitApplication()} ,
            title = "Ventana escoger",
            state = windowState,
            icon = icon
        )
        {
                Column (modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ){
                    Button(modifier = Modifier
                        .padding(10.dp),
                        onClick = { viewmodelElegido = viewmodelDB },
                    ){
                        Text("Base de Datos")
                    }

                    Button(modifier = Modifier,
                        onClick = { viewmodelElegido = viewmodelFile }
                    ){
                        Text("Fichero")
                    }

                }

        }
    }

    viewmodelElegido?.let {
        StudentWindow(
        title = "Añadir Estudiante",
        windowState = windowState,
        onCloseMainWindow = { exitApplication() },
        icon = icon,
        resizable = false,
        studentViewModel = it
    )
    }

}