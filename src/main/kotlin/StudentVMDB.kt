import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import java.sql.SQLException

class StudentVMDB(val studentRepository: IStudentRepo): IStudentViewModel{
    companion object {
        private const val MAXCHARACTERS = 10
        private const val MAXNUMSTUDENTSVISIBLE = 7
    }

    private var _newStudent = mutableStateOf("")
    override val newStudent: State<String> = _newStudent

    private val _students = mutableStateListOf<String>()
    override val students: List<String> = _students

    private val _infoMessage = mutableStateOf("")
    override val infoMessage: State<String> = _infoMessage

    private val _showInfoMessage = mutableStateOf(false)
    override val showInfoMessage: State<Boolean> = _showInfoMessage

    private val _selectedIndex = mutableStateOf(-1) // -1 significa que no hay selección
    override val selectedIndex: State<Int> = _selectedIndex

    private val _showEditStudent = mutableStateOf(false)
    override val showEditStudent: State<Boolean> = _showEditStudent

    private val _editStudent = mutableStateOf("")
    override val editStudent: State<String> = _editStudent


    override fun addStudent() {
        if (_newStudent.value.isNotBlank()) {
            _students.add(_newStudent.value.trim())
            _newStudent.value = ""
        }
    }

    override fun removeStudent(index: Int) {
        if (index in _students.indices) {
            _students.removeAt(index)
        }
    }

    override fun loadStudents() {
        _students.addAll(studentRepository.getAllStudents())
    }

    override fun saveStudents() {
        try {
            studentRepository.updateStudents(_students)
            updateInfoMessage("Estudiantes guardados correctamente")
        } catch (e: Exception) {
            updateInfoMessage("Error guardando.")
        }
    }

    override fun clearStudents() {
        _students.clear()
    }

    override fun shouldShowScrollStudentListImage() = _students.size > MAXNUMSTUDENTSVISIBLE

    override fun newStudentChange(name: String) {
        if (name.length <= MAXCHARACTERS) {
            _newStudent.value = name
        }
    }

    override fun studentSelected(index: Int) {
        _selectedIndex.value = index
    }

    private fun updateInfoMessage(message: String) {
        _infoMessage.value = message
        _showInfoMessage.value = true
        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)
            _showInfoMessage.value = false
            _infoMessage.value = ""
        }
    }

    override fun showInfoMessage(show: Boolean) {
        _showInfoMessage.value = show
    }

    override fun showEditStudent(show: Boolean) {
        _showEditStudent.value = show
    }

    override fun editStudent(selectedStudent:Int, newName:String) {

        _students[selectedStudent] = newName
    }
}

