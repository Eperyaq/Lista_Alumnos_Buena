import java.io.File

class StudentVMFile:IFile {
    override fun writeFile(text: String, fichero:File) {
        fichero.writeText(text)
    }
    override fun readFile(fichero:File): MutableList<String> {
        return fichero.readLines().toMutableList()
    }

}