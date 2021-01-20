/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author calebbolanos
 */
public class Reactivo {
    public static final int SIN_RESPONDER = 0;
    public static final int INCISO_A = 1;
    public static final int INCISO_B = 2;
    public static final int INCISO_C = 3;
    public static final int INCISO_D = 4;
    
    private String pregunta, opcionA, opcionB, opcionC, opcionD;
    private int idReactivo, respuesta;

    public Reactivo(int idReactivo, String pregunta, String opcionA, String opcionB, String opcionC, String opcionD, int respuesta) {
        this.idReactivo = idReactivo;
        this.pregunta = pregunta;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuesta = respuesta;
    }

    public int getIdReactivo() {
        return idReactivo;
    }

    public void setIdReactivo(int idReactivo) {
        this.idReactivo = idReactivo;
    }
    
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

}
