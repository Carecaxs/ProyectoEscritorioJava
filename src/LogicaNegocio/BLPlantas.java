/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.ADPlantas;
import Entidades.EntidadPlanta;
import java.sql.SQLException;

public class BLPlantas {

    //atributos
    private String _mensaje;

    //constructor
    public BLPlantas() {
        this._mensaje = "";
    }

    //propiedades
    public String getMensaje() {
        return _mensaje;
    }

    //metodos
    //este metodo ahace una consulta a la base de datos para obtener el id y nombre de las categorias
    //y se va guardando cada elemento en un arreglo de strings que va ser lo que retorna el metodo
    //se tienen que gurdar de esta forma: id:nombrecategoria
    public String[] obtenerNombresPlantas() throws SQLException, Exception {

        try {
            ADPlantas acceso = new ADPlantas();

            String[] nombresPlantas = acceso.obtenerNombresPlantas();

            return nombresPlantas;

        } catch (SQLException e) {
            throw e;
        }
    }

    //metodo que llama al SP de la BD para insertar planta
    //recibe el objeto planta que va ser utilizada para enviar parametros al sp
    //retorna la cantidad de registros afectados que tendria que ser 1
    public int InsertarPlanta(EntidadPlanta planta) throws Exception {
        int resultado=0;
        
        
        try {
            //validamos que los campos requeridos vengan llenos
            if(!planta.getNombre().equals("") && planta.getCantidadExistente()!=0 && 
                    planta.getCategoria()!=0 && planta.getPrecio()!=0){
                
                
                ADPlantas acceso = new ADPlantas();//capa de acceso datos
                
                resultado=acceso.InsertarPlanta(planta);//llamamos el metodo que ejecuta el SP
                _mensaje=acceso.getMensaje();//obtener mensaje de la capa de acceso
                
            }
            
            return resultado;
            
        } catch (Exception e) {
            throw e;
        }
        
        
        
    }

}
