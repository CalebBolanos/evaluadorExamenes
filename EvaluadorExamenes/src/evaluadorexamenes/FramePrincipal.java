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
 * @author calebbolanos
 */
public class FramePrincipal extends JFrame{

    /**
     * Clase principal en donde se mostraran los JPanels que contienen la GUI
     */
    
    private JPanel panelPrincipal;

    public FramePrincipal(String titulo) {
        super(titulo);
        crearGUI();
    }
    
    public static void main(String[] args) {
        FramePrincipal ventana = new FramePrincipal("Iniciar Sesion");
        ventana.setBounds(0, 0, 800, 600);
        ventana.setVisible(true);
    }

    private void crearGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal, BorderLayout.CENTER);

        mostrarPanel(new InicioSesion(this));
        pack();
    }

    public void mostrarPanel(JPanel panel) {
        panelPrincipal.removeAll();
        panelPrincipal.add(panel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
   }
}
