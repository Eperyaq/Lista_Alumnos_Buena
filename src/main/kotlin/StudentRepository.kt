import java.sql.SQLException

class StudentRepository {

    /**
     * Realiza una sentencia SELECT para sacar todos los estudiantes de la DB
     */
    fun selectAllStudents(): MutableList<DataBase.Estudiante>{

        val conexion = DataBase.connectDB()
        val query = "SELECT * FROM STUDENTS"
        val listaEstudiante = mutableListOf<DataBase.Estudiante>()

        try {
            val statement = conexion?.createStatement() //Sirve para crear una query
            val resultado = statement?.executeQuery(query) //Esto te va a ejecutar la query y va a preparar el resultado

            while (resultado?.next() == true){

                val idStudent = resultado.getInt("id")//Saca el id y lo mete en la tabla
                val nameStudent = resultado.getString("name") //Este saca el nombre y lo mete en la tabla
                listaEstudiante.add(DataBase.Estudiante(nameStudent, idStudent))
            }
            resultado?.close()
            statement?.close()

        } catch (e: SQLException){
            e.printStackTrace()
        }  finally {
            DataBase.closeDB(conexion)
        }
        return listaEstudiante
    }

    /**
     * Realiza una sentencia UPDATE para cambiar a los estudiantes de la DB
     * @param id ID nuevo que va a ser introducido en la tabla
     * @param name Nombre nuevo que va a ser introducido en la tabla
     */
    fun updateStudents(id: Int, name:String){

        val conexion = DataBase.connectDB()
        val query = ("UPDATE STUDENTS SET ID = ?, NAME = ?")

        try {
            val statement = conexion?.prepareStatement(query)//realizamos la conexion

            statement?.setInt(1, id)//le introducimos los valores
            statement?.setString(2, name)

            statement?.executeUpdate()//le decimos que se ejecute
            statement?.close() // y que cierre
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DataBase.closeDB(conexion)
        }
    }

}