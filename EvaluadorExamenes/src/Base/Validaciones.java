/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author calebbolanos
 */
public class Validaciones {
    //metodo que se usa para verificar que ningun textfield dado este vacio
    public static boolean StringsNoVacios(String... textfileds) {
        for (String contenido : textfileds) {
            if (contenido.equals("")) {
                return false;
            }
        }
        return true;
    }
}