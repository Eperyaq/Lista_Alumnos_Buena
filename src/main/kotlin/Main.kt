
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.dp


fun main() = application {

    val icon = BitmapPainter(useResource("IconoPredeterminado.png", ::loadImageBitmap))
    val windowState = GetWindowState(
        windowWidth = 800.dp,
        windowHeight = 800.dp
    )

    StudentWindow(
        title = "My Students",
        icon = icon,
        windowState = windowState,
        resizable = false,
        onCloseMainWindow = { exitApplication() }
    )
}