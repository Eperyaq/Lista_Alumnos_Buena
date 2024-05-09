interface IStudentRepo {

    fun getAllStudents(): MutableList<String>
    fun updateStudents( studentList: List<String>): Result<Unit>
}