/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Modelo.Examen;
import static Modelo.Examen.CONCLUIDO;
import static Modelo.Examen.EN_PROCESO;
import static Modelo.Examen.NO_EMPEZADO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Parte de la gui en donde se muestra la información de un examen en 
 * especifico
 */
public class BotonExamen extends JPanel implements ActionListener{
    private Examen examen;
    private JLabel lblTitulo, lblFecha, lblInfoAdicional;
    private JButton buttonVisualizar;
    
    private ComunicacionExamen comunicar;
    
    public BotonExamen(Examen examen, ComunicacionExamen comunicar){
        this.examen = examen;
        this.comunicar = comunicar;
        crearGUI();
    }
    
    private void crearGUI(){
        lblTitulo = new JLabel(examen.getTitulo() +" | ");
        add(lblTitulo);
        
        lblFecha = new JLabel(examen.getFecha().toString() +" | ");
        add(lblFecha);
        
        switch(examen.getEstado()){
            case NO_EMPEZADO:
                construirInfoExamen();
                break;
            case EN_PROCESO:
                construirInfoExamenReanudar();
                break;
            case CONCLUIDO:
                construirInfoExamenConcluido();
                break;
        }
        
    }
    
    private void construirInfoExamen() {
        buttonVisualizar = new JButton("Comenzar examen");
        buttonVisualizar.setBounds(300, 70, 150, 20);
        buttonVisualizar.addActionListener(this);
        add(buttonVisualizar);
    }

    private void construirInfoExamenReanudar() {
        lblInfoAdicional = new JLabel("Ultima pregunta contestada:"+ examen.getUltimaPregunta() +" | ");
        lblInfoAdicional.setBounds(10, 50, 200, 30);
        add(lblInfoAdicional);
                
        buttonVisualizar = new JButton("Reanudar examen");
        buttonVisualizar.setBounds(300, 70, 150, 20);
        buttonVisualizar.addActionListener(this);
        add(buttonVisualizar);
    }

    private void construirInfoExamenConcluido() {
        lblInfoAdicional = new JLabel("Calificación:"+ examen.getCalificación() +" | ");
        lblInfoAdicional.setBounds(10, 50, 200, 30);
        add(lblInfoAdicional);
                
        buttonVisualizar = new JButton("Ver resultados");
        buttonVisualizar.setBounds(300, 70, 150, 20);
        buttonVisualizar.addActionListener(this);
        add(buttonVisualizar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        comunicar.AccionExamen(examen);
    }

}
