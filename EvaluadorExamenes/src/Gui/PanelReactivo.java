/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Modelo.Reactivo;
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
        
        radioIncisoA = new JRadioButton(reactivo.getOpcionA());
        radioIncisoA.addActionListener(this);
        
        radioIncisoB = new JRadioButton(reactivo.getOpcionB());
        radioIncisoB.addActionListener(this);
        
        radioIncisoC = new JRadioButton(reactivo.getOpcionC());
        radioIncisoC.addActionListener(this);
        
        radioIncisoD = new JRadioButton(reactivo.getOpcionD());
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
    
    public String obtenerRespuesta(){
        return reactivo.getRespuesta();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(radioIncisoA.isSelected()){
            reactivo.setRespuesta(reactivo.getOpcionA());
        }
        if(radioIncisoB.isSelected()){
            reactivo.setRespuesta(reactivo.getOpcionB());
        }
        if(radioIncisoC.isSelected()){
            reactivo.setRespuesta(reactivo.getOpcionC());
        }
        if(radioIncisoD.isSelected()){
            reactivo.setRespuesta(reactivo.getOpcionD());
        }
    }
    
}
