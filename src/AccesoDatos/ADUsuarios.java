/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import Config.Config;
import Entidades.EntidadUsuarios;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Progra
 */
public class ADUsuarios {

    //atributos
    private Connection _conexion;//el objeto CONEXION

    private String _mensaje;

    //constructor
    public ADUsuarios() throws Exception {
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
    //este metodo va recibir un usuario y retorna 1 si el usuario es administrador, 0 si es cliente
    //y -1 si no existe
    public int VerificarUsuario(EntidadUsuarios usuario) throws SQLException {

        int retorno = -1;

        String sentencia = "select ACCESO from USUARIOS where CORREO=? and CLAVE=?";

        try {
            PreparedStatement ps = _conexion.prepareStatement(sentencia);

            //registrar los argumentos de la consulta
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getClave());

            //obtener los identity generados
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // El usuario existe en la base de datos, comprobar si es administrador
                boolean esAdministrador = rs.getBoolean("ACCESO");
                retorno = esAdministrador ? 1 : 0;

            }

            return retorno;

        } catch (SQLException e) {
            throw e;
        } finally {
            _conexion.close();
        }
    }

    //este metodo recibe un usuario y llama un sp de la BD para insertar el usuario
    //retorna el numero de filas afectadas que tiene que ser una o -1 si algo no salio bien
    public int InsertarUsuario(EntidadUsuarios usuario) throws Exception {

        int retorno = -1;

        try {

            //Hacemos el llamado del SP 
            CallableStatement cs = _conexion.prepareCall("{call SP_CREAR_USUARIO(?,?,?,?,?,?)}");

            //Parametros
            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getApellidos());
            cs.setInt(3, usuario.isAcceso() ? 1 : 0);

            cs.setString(4, usuario.getCorreo());
            cs.setString(5, usuario.getClave());

            //Con este registramos el parametro de salida
            cs.registerOutParameter(6, Types.VARCHAR);

            //Executamos el SP
            retorno=cs.executeUpdate();

            //Obtenemos el parametro de salida del SP
            _mensaje = cs.getString(6);

        } catch (SQLException e) {

            throw e;

        } finally {

            _conexion.close();

        }
        
        return retorno;
    }
}
