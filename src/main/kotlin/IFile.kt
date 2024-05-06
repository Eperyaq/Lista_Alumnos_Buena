import java.io.File
import java.sql.SQLException

interface IFile {

    fun readFile(fichero: File): MutableList<String>

    fun writeFile(text:String, fichero: File)
}