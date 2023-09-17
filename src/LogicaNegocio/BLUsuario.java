/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import AccesoDatos.ADUsuarios;
import Entidades.EntidadUsuarios;
import java.sql.SQLException;

/**
 *
 * @author Progra
 */
public class BLUsuario {

    //atributos
    private String _mensaje;

    //constructor
    public BLUsuario() {
        this._mensaje = "";
    }

    //propiedades
    public String getMensaje() {
        return _mensaje;
    }

    //metodos
    //este metodo va recibir un usuario y retorna 1 si el usuario es administrador, 0 si es cliente
    //y -1 si no existe  
    public int VerificarUsuario(EntidadUsuarios usuario) throws SQLException, Exception {

        ADUsuarios acceso = new ADUsuarios();

        try {
            if (!usuario.getCorreo().equals("") && !usuario.getClave().equals("")) {
                return acceso.VerificarUsuario(usuario);
            } else {
                _mensaje = "Debes de ingresar correo y contraseña";
                return -1;
            }

        } catch (SQLException e) {
            throw e;
        }

    }

    //este metodo recibe un usuario y llama un sp de la BD para insertar el usuario
    //retorna el numero de filas afectadas que tiene que ser una o -1 si algo no salio bien
    public int InsertarUsuario(EntidadUsuarios usuario) throws Exception {

        try {

            if (!usuario.getNombre().equals("") && !usuario.getApellidos().equals("") && !usuario.getCorreo().equals("")
                    && !usuario.getClave().equals("")) { //validamos que todos los campos esten llenos

                ADUsuarios acceso = new ADUsuarios();
                int retorno = acceso.InsertarUsuario(usuario);
                _mensaje = acceso.getMensaje();
                return retorno;

            } else {//si algun campo viene vacio aunque desde la capa de presentacion igual se valida
                return -1;
            }

        } catch (Exception e) {

            throw e;

        }

    }

}
