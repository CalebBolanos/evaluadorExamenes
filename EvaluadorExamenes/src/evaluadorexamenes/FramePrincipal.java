/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluadorexamenes;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * Clase principal en donde se mostraran los JPanels que contienen la GUI
 */
public class FramePrincipal extends JFrame{

    private JPanel panelPrincipal;

    public FramePrincipal(String titulo) {
        super(titulo);
        configurarFrame();
    }
    
    public static void main(String[] args) {
        FramePrincipal ventana = new FramePrincipal("Iniciar Sesion");
        ventana.setBounds(0, 0, 800, 600);
        ventana.setVisible(true);
    }

    private void configurarFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);

        mostrarPanel(new InicioSesion(this));
        pack();
    }

    /**
     * metodo encargado de cargar un panel dentro del Jframe 
     * este metodo será usado a lo largo del programa cuando se 
     * esté navegando por el
     */
    public void mostrarPanel(JPanel panel) {
        panelPrincipal.removeAll();
        panelPrincipal.add(panel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
   }
}
