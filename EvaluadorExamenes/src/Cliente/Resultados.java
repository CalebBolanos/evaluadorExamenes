/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Modelo.Examen;
import evaluadorexamenes.FramePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author calebbolanos
 */
public class Resultados extends JPanel implements ActionListener{
    FramePrincipal framePrincipal;
    
    JPanel panelSuperior, panelContenido;
    JScrollPane scrollPane;
    JLabel lblTitulo, lblTituloExamen, lblAlumno, lblFecha, lblCalificacion;
    JButton buttonInicio;
    Examen examen;
    
    public Resultados(FramePrincipal framePrincipal, Examen examen){
        super();
        this.framePrincipal = framePrincipal;
        this.examen = examen;
        crearGUI();
    }
    
    private void crearGUI() {
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        
        buttonInicio = new JButton("Inicio");
        buttonInicio.addActionListener(this);
        panelSuperior.add(buttonInicio);
        
        lblTitulo = new JLabel("Resultados del examen");
        panelSuperior.add(lblTitulo);
       
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        lblTituloExamen = new JLabel("Titulo del examen: "+ examen.getTitulo());
        panelContenido.add(lblTituloExamen);
        
        lblAlumno = new JLabel("Alumno que presentó: "+ examen.getNombrePresentaExamen());
        panelContenido.add(lblAlumno);
        
        lblFecha = new JLabel("Fecha de aplicación: "+ examen.getFecha());
        panelContenido.add(lblFecha);
        
        lblCalificacion = new JLabel("Calificación: "+ examen.getCalificación());
        panelContenido.add(lblCalificacion);
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(panelSuperior);
        add(panelContenido);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonInicio){
            framePrincipal.mostrarPanel(new Inicio(framePrincipal));
        }
        
    }
}
