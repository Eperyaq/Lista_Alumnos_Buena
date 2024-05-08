import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
object DataBase {

    const val URL = "jdbc:mysql://localhost:3306/studentdb"
    const val USUARIO = "studentuser"
    const val CONTRASENIA = "password"

    init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: LinkageError){
            e.printStackTrace()
        } catch (e: ExceptionInInitializerError){
            e.printStackTrace()
        } catch (e: ClassNotFoundException){
            e.printStackTrace()
        }

    }


    /**
     * Funcion que establece la conexion con la base de datos
     * @return La conexion con la base de datos
     */
    fun connectDB(): Connection?{
        var conexion : Connection? = null

        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENIA) //Dar conexion con la base de datos
        } catch ( e: SQLException){
            e.printStackTrace()
        }

        return conexion
    }

    /**
     * Funcion que cierra la database
     * @param conexion Conexion que hay que cerrar
     */
    fun closeDB(conexion:Connection?) {
        try {
            conexion?.close()
        } catch (e:SQLException){
            e.printStackTrace()
        }
    }

    data class Estudiante(val name: String, val id:Int){ //Crear la "tabla"
        override fun toString(): String {
            return name
        }
    }


}