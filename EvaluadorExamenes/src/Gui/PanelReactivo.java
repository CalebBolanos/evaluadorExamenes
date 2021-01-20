/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Modelo.Reactivo;
import static Modelo.Reactivo.INCISO_A;
import static Modelo.Reactivo.INCISO_B;
import static Modelo.Reactivo.INCISO_C;
import static Modelo.Reactivo.INCISO_D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author calebbolanos
 */
public class PanelReactivo extends JPanel implements ActionListener{
    
    ButtonGroup respuestas = new ButtonGroup();
    JRadioButton radioIncisoA, radioIncisoB, radioIncisoC, radioIncisoD;
    JLabel lblPregunta;
    
    private Reactivo reactivo;
    public PanelReactivo(Reactivo reactivo){
        this.reactivo = reactivo;
        crearGUI();
    }
    
    private void crearGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        lblPregunta = new JLabel(reactivo.getPregunta());
        
        radioIncisoA = new JRadioButton("a");
        radioIncisoA.addActionListener(this);
        
        radioIncisoB = new JRadioButton("b");
        radioIncisoB.addActionListener(this);
        
        radioIncisoC = new JRadioButton("c");
        radioIncisoC.addActionListener(this);
        
        radioIncisoD = new JRadioButton("d");
        radioIncisoD.addActionListener(this);
        
        respuestas.add(radioIncisoA);
        respuestas.add(radioIncisoB);
        respuestas.add(radioIncisoC);
        respuestas.add(radioIncisoD);
        
        
        add(lblPregunta);
        add(radioIncisoA);
        add(radioIncisoB);
        add(radioIncisoC);
        add(radioIncisoD);
    }
    
    public int obtenerRespuesta(){
        return reactivo.getRespuesta();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(radioIncisoA.isSelected()){
            reactivo.setRespuesta(INCISO_A);
        }
        if(radioIncisoB.isSelected()){
            reactivo.setRespuesta(INCISO_B);
        }
        if(radioIncisoC.isSelected()){
            reactivo.setRespuesta(INCISO_C);
        }
        if(radioIncisoD.isSelected()){
            reactivo.setRespuesta(INCISO_D);
        }
    }
    
}
