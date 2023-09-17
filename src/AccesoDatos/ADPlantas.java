package AccesoDatos;

import Config.Config;
import Entidades.EntidadPlanta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ADPlantas {

    //atributos
    private Connection _conexion;//el objeto CONEXION

    private String _mensaje;

    //constructor
    public ADPlantas() throws Exception {
        try {

            //necesitamos la cadena de conexion
            String Url = Config.getConnectionString();
            _conexion = DriverManager.getConnection(Url);
            /*
             
             Driver manager se utiliza para abrir conexiones.
             El método getConnection("String) toma una URL en string y develve un objeto conexion
             La conexion ya viene abierta y no necesita un método open
             */

        } catch (ClassNotFoundException | SQLException e) {

            throw e;

        }

    }

    //propiedades
    public String getMensaje() {
        return _mensaje;
    }

    //metodos
    //este metodo ahace una consulta a la base de datos para obtener el id y nombre de las categorias
    //y se va guardando cada elemento en un arreglo de strings que va ser lo que retorna el metodo
    //se tienen que gurdar de esta forma: id:nombrecategoria
    public String[] obtenerNombresPlantas() throws SQLException {

        String[] nombresPlantas = null;

        try {

            String consulta = "select ID_CATEGORIA, NOMBRE from CATEGORIAS";
            PreparedStatement statement = _conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            // Contar la cantidad de resultados para poder asignarle el tamaño al arreglo
            int rowCount = 0;
            if (resultSet.next()) {//si hay aunque sea un solo registro

                rowCount++;

                while (resultSet.next()) {
                    rowCount++;
                }

                resultSet.close();
                statement.close();

                // Volver a ejecutar la consulta y llenar el arreglo de nombres
                statement = _conexion.prepareStatement(consulta);
                resultSet = statement.executeQuery();

                nombresPlantas = new String[rowCount];
                int i = 0;
                while (resultSet.next()) {
                    String nombrePlanta = resultSet.getInt("ID_CATEGORIA") + ": " + resultSet.getString("NOMBRE");
                    nombresPlantas[i++] = nombrePlanta;
                }

            } else {
                return nombresPlantas;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _conexion.close();
        }

        return nombresPlantas;
    }

    
    //metodo que llama al SP de la BD para insertar planta
    //recibe el objeto planta que va ser utilizada para enviar parametros al sp
    //retorna la cantidad de registros afectados que tendria que ser 1
    public int InsertarPlanta(EntidadPlanta planta) throws Exception {

        int resultado = 0;

        try {
            //Hacemos el llamado del SP eliminar
            CallableStatement cs = _conexion.prepareCall("{call SP_INSERTAR_PLANTA(?,?,?,?,?,?,?)}");

            //Parametros
            cs.setString(1, planta.getNombre());
            cs.setDouble(2, planta.getPrecio());
            cs.setInt(3, planta.getCantidadExistente());
            cs.setString(4, planta.getDescripcion());
            cs.setInt(5, 1);
            cs.setInt(6, planta.getCategoria());
            cs.registerOutParameter(7, Types.VARCHAR);

            //Executamos el SP
            resultado = cs.executeUpdate();

            //Obtenemos el parametro de salida del SP
            _mensaje = cs.getString(7);
            
            return resultado;

        } catch (Exception e) {
            throw e;

        } finally {
            _conexion.close();
        }

    }

}
