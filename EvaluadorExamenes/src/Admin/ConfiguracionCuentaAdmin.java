package Admin;

/**
 *
 * @author Lalo
 */
import evaluadorexamenes.FramePrincipal;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ConfiguracionCuentaAdmin extends JPanel implements ActionListener{
    FramePrincipal framePrincipal;
    
    JPanel panelSuperior, panelContenido;
    JScrollPane scrollPane;
    JLabel lblTitulo, lblUsuario, lblCorreo;
    JButton buttonInicio, buttonAcercaDe;
    
    public ConfiguracionCuentaAdmin(FramePrincipal framePrincipal){
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }
    
    private void crearGUI() {
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        
        buttonInicio = new JButton("Inicio");
        buttonInicio.addActionListener(this);
        panelSuperior.add(buttonInicio);
        
        lblTitulo = new JLabel("Configuración de la cuenta");
        panelSuperior.add(lblTitulo);
        
        buttonAcercaDe = new JButton("Acerca de");
        buttonAcercaDe.addActionListener(this);
        panelSuperior.add(buttonAcercaDe);
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        lblUsuario = new JLabel("Nombre");
        panelContenido.add(lblUsuario);
        
        lblCorreo = new JLabel("Correo electrónico");
        panelContenido.add(lblCorreo);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(panelSuperior);
        add(panelContenido);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonInicio){
            framePrincipal.mostrarPanel(new InicioAdmin(framePrincipal));
        }
        if(e.getSource() == buttonAcercaDe){
            JOptionPane.showMessageDialog(null, "Aplicador y evaluador de examenes de opción multiple\nProyecto de Programación Orientada a Objetos", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
   
}
