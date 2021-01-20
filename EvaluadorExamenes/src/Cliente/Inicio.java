/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Gui.BotonExamen;
import Gui.ComunicacionExamen;
import Modelo.Examen;
import static Modelo.Examen.CONCLUIDO;
import static Modelo.Examen.EN_PROCESO;
import static Modelo.Examen.NO_EMPEZADO;
import evaluadorexamenes.FramePrincipal;
import evaluadorexamenes.InicioSesion;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author calebbolanos
 */
public class Inicio extends JPanel implements ActionListener, ComunicacionExamen {
    FramePrincipal framePrincipal;
    
    JPanel panelSuperior, panelContenido;
    JScrollPane scrollPane;
    JLabel lblInicio;
    JButton buttonCuenta, buttonCerrarSesion;
    
    List<BotonExamen> examenes;
    
    public Inicio(FramePrincipal framePrincipal){
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }
    
    private void crearGUI() {

        /**
         * Parte superior de la GUI en donde va el titulo y botones con
         * opciones
         * 
         */
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        
        buttonCerrarSesion = new JButton("Cerrar Sesion");
        buttonCerrarSesion.addActionListener(this);
        
        lblInicio = new JLabel("Inicio");
        
        
        buttonCuenta = new JButton("Configuración de la cuenta");
        buttonCuenta.addActionListener(this);
        
        panelSuperior.add(buttonCerrarSesion);
        panelSuperior.add(lblInicio);
        panelSuperior.add(buttonCuenta);
        
        /**
         * Panel con el contenido del inicio, se mostraran todos los examenes 
         * disponibles y ya resultos
         * 
         */
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        obtenerExamenes();
        for (BotonExamen examen : examenes) {
            panelContenido.add(examen);
        }
        
        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(panelSuperior);
        add(scrollPane);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonCerrarSesion){
            framePrincipal.mostrarPanel(new InicioSesion(framePrincipal));
        }
        if(e.getSource() == buttonCuenta){
            framePrincipal.mostrarPanel(new ConfiguracionCuenta(framePrincipal));
        }
    }
    
    @Override
    public void AccionExamen(Examen examen){
        switch (examen.getEstado()){
            case NO_EMPEZADO:
                framePrincipal.mostrarPanel(new AplicadorExamen(framePrincipal, examen));
                break;
            case EN_PROCESO:
                framePrincipal.mostrarPanel(new AplicadorExamen(framePrincipal, examen));
                break;
            case CONCLUIDO:
                framePrincipal.mostrarPanel(new Resultados(framePrincipal, examen));
                break;
            
        }
        
    }
    
    public void obtenerExamenes(){
        examenes = new ArrayList<>();
        Examen examenx;
        for (int i = 0; i < 15; i++) {
            examenx = new Examen(i, "Examen"+ i, "presentaexamen", new Date(2020, 1, 15), i, 0);
            examenes.add(new BotonExamen(examenx, this));
        }
    }

}
