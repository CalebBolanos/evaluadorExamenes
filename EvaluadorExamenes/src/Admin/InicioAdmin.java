package Admin;


import Gui.CreadorExamen;
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
 * @author Lalo
 */

public class InicioAdmin extends JPanel implements ActionListener {
  FramePrincipal framePrincipal;
  JPanel panelSuperior, panelContenido;
  JScrollPane scrollPane;
  JLabel lblInicio;
  JButton buttonCuenta, buttonCerrarSesion,nvoExamen;
  
  
  public InicioAdmin(FramePrincipal framePrincipal){
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
        
        nvoExamen=new JButton("Crear Nuevo");
        nvoExamen.addActionListener(this);
        
        panelSuperior.add(buttonCerrarSesion);
        panelSuperior.add(lblInicio);
        panelSuperior.add(buttonCuenta);
        panelSuperior.add(nvoExamen);
        
        /**
         * Panel con el contenido del inicio, se mostraran todos los examenes 
         * disponibles y ya resultos
         * 
         */ 
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
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
            framePrincipal.mostrarPanel(new ConfiguracionCuentaAdmin(framePrincipal));
        }
        
        if(e.getSource()==nvoExamen){
         framePrincipal.mostrarPanel(new CreadorExamen(framePrincipal));
        }
    }
    
}
