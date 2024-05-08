import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import java.io.File
import java.sql.SQLException

class StudentVMDB(studentRepository: IStudentRepo){
    val repo = studentRepository

    fun selectStudentDB(): MutableList<DataBase.Estudiante>{
        return repo.selectAllStudents()
    }

    fun updateStudentsDB(id:Int, name:String){
        repo.updateStudent(id, name)
    }
}
