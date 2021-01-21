/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

/**
 *
 * @author calebbolanos
 */
public class Usuario {
    public final static int CLIENTE = 1;
    public final static int ADMINISTRADOR = 2;
    
    private static int id, idTipo;
    private static String nombre, paterno, materno, correo;

    public static void almacenarDatos(int id, int idTipo, String nombre, String paterno, String materno, String correo) {
        Usuario.id = id;
        Usuario.idTipo = idTipo;
        Usuario.nombre = nombre;
        Usuario.paterno = paterno;
        Usuario.materno = materno;
        Usuario.correo = correo;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Usuario.id = id;
    }

    public static int getIdTipo() {
        return idTipo;
    }

    public static void setIdTipo(int idTipo) {
        Usuario.idTipo = idTipo;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Usuario.nombre = nombre;
    }

    public static String getPaterno() {
        return paterno;
    }

    public static void setPaterno(String paterno) {
        Usuario.paterno = paterno;
    }

    public static String getMaterno() {
        return materno;
    }

    public static void setMaterno(String materno) {
        Usuario.materno = materno;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        Usuario.correo = correo;
    }
    
    public static void limpiarDatos(){
        Usuario.id = 0;
        Usuario.idTipo = 0;
        Usuario.nombre = "";
        Usuario.paterno = "";
        Usuario.materno = "";
        Usuario.correo = "";
    }

}
