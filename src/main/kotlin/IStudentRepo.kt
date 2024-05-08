interface IStudentRepo {

    fun selectAllStudents(): MutableList<DataBase.Estudiante>
    fun updateStudent(id:Int, name:String)
}