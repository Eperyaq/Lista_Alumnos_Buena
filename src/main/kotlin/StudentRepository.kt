import java.sql.Connection
import java.sql.SQLException

class StudentRepository: IStudentRepo{

    /**
     * Realiza una sentencia SELECT para sacar todos los estudiantes de la DB
     */
    override fun getAllStudents(): MutableList<String>{

        val conexion = DataBase.connectDB()
        val query = "SELECT * FROM STUDENTS"
        val listaEstudiante = mutableListOf<String>()

        try {
            val statement = conexion?.createStatement() //Sirve para crear una query
            val resultado = statement?.executeQuery(query) //Esto te va a ejecutar la query y va a preparar el resultado

            while (resultado?.next() == true){
                listaEstudiante.add(resultado.getString("name"))
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
    override fun updateStudents(students: List<String>): Result<Unit> {


        val conexion = DataBase.connectDB()
        var ps: java.sql.PreparedStatement? = null
        val sqlDelete = "DELETE FROM students"
        val sqlInsert = "INSERT INTO students (name) VALUES (?)"

        return try {
            conexion?.autoCommit = false

            val stmt = conexion?.createStatement()
            stmt?.execute(sqlDelete)
            stmt?.close()
            ps = conexion?.prepareStatement(sqlInsert)

            for (student in students) {
                ps?.setString(1, student)
                ps?.executeUpdate()
            }

            conexion?.commit()
            Result.success(Unit)

        } catch (e: SQLException) {
            conexion?.rollback()
            Result.failure(e)

        } finally {
            ps?.close()
            conexion?.autoCommit = true
            conexion?.close()
        }
    }

}