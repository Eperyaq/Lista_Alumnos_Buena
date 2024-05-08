
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import kotlin.system.exitProcess

fun main() = application {

    val icon = BitmapPainter(useResource("IconoPredeterminado.png", ::loadImageBitmap))
    val gestorFichero = FileManagement()
    val studentsFile = File("studentList.txt")
    var mostrar2 by remember { mutableStateOf(false) }


    val viewmodel = StudentVMFile(gestorFichero, studentsFile)
    val windowState = GetWindowState(
        windowWidth = 800.dp,
        windowHeight = 800.dp
    )


    mainWindow(
        estado = windowState,
        onClose = { exitApplication() },
        icono = icon,
        viewmodel = viewmodel,
    )

}