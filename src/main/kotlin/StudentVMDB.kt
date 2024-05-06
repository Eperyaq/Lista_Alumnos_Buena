import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import java.io.File

class StudentVMDB(
    private val fileManagement: IFile,
    private val studentsFile: File
    ) {
        private var _newStudent = mutableStateOf("")
        var _students = mutableListOf<String>()
        val newStudent: State<String> = _newStudent
        fun addStudent() {
            if (_newStudent.value.isNotBlank()) {
                _students.add(_newStudent.value.trim())
                _newStudent.value = ""
            }
        }
        fun newStudentChange(name: String) {
            if (name.length <= 10) {
                _newStudent.value = name
            }
        }
}