/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author calebbolanos
 */
public class Examen {
    public static final int NO_EMPEZADO = 0;
    public static final int EN_PROCESO = 1;
    public static final int CONCLUIDO = 2;
    
    private String titulo, nombrePresentaExamen;
    private Date fecha;
    private int ultimaPregunta, calificación;
    private int estado;

    public Examen(String titulo, String nombrePresentaExamen, Date fecha, int ultimaPregunta, int calificación) {
        this.titulo = titulo;
        this.nombrePresentaExamen = nombrePresentaExamen;
        this.fecha = fecha;
        this.ultimaPregunta = ultimaPregunta;
        this.calificación = calificación;
        setEstado();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombrePresentaExamen() {
        return nombrePresentaExamen;
    }

    public void setNombrePresentaExamen(String nombrePresentaExamen) {
        this.nombrePresentaExamen = nombrePresentaExamen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUltimaPregunta() {
        return ultimaPregunta;
    }

    public void setUltimaPregunta(int ultimaPregunta) {
        this.ultimaPregunta = ultimaPregunta;
    }

    public int getCalificación() {
        return calificación;
    }

    public void setCalificación(int calificación) {
        this.calificación = calificación;
    }
    
    private void setEstado(){
        if(ultimaPregunta == 0){
            estado = NO_EMPEZADO;
        }
        else{
            if(ultimaPregunta > 0 && ultimaPregunta <= 9){
                estado = EN_PROCESO;
            }
            else{
                estado = CONCLUIDO;
            }
        }
    }

    public int getEstado() {
        return estado;
    }

}
