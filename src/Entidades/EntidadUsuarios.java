/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Progra
 */
public class EntidadUsuarios {

    private int idUsuario;
    private String nombre, apellidos, correo, clave;
    private boolean acceso;

    private String _mensaje;

    public EntidadUsuarios() {
        this.idUsuario = -1;
        this.nombre = "";
        this.apellidos = "";
        this.correo = "";
        this.clave = "";
        this.acceso = false;
        this._mensaje="";
    }

    public String getMensaje() {
        return _mensaje;
    }

    public void setMensaje(String _mensaje) {
        this._mensaje = _mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

}
