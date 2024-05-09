import androidx.compose.runtime.State

interface IStudentViewModel {
    val newStudent: State<String>
    val students: List<String>
    val infoMessage: State<String>
    val showInfoMessage: State<Boolean>
    val selectedIndex: State<Int>
    val showEditStudent: State<Boolean>
    val editStudent: State<String>

    fun addStudent()
    fun removeStudent(index: Int)
    fun loadStudents()
    fun saveStudents()
    fun clearStudents()
    fun shouldShowScrollStudentListImage(): Boolean
    fun newStudentChange(name: String)
    fun studentSelected(index: Int)
    fun showInfoMessage(show: Boolean)
    fun showEditStudent(show: Boolean)
    fun confirmEditStudent(editedName:String)
    fun editStudent(index: Int, name: String)
}