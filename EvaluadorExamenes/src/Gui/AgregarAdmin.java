package Gui;

import Admin.ConfiguracionCuentaAdmin;
import Admin.InicioAdmin;
import evaluadorexamenes.FramePrincipal;
import evaluadorexamenes.InicioSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lalo
 */
public class AgregarAdmin extends JPanel implements ActionListener{
     FramePrincipal framePrincipal;
     JPanel panelSuperior, panelContenido,panelbtn;
     JButton buttonInicio, buttonSalir,buttonAtras,crear,vaciar,verificar;
     JTextField idAdm,Nomb,ApPater,ApMater,mail,contras;
     
     public AgregarAdmin(FramePrincipal framePrincipal){
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }
     
     public void crearGUI(){
       
       //Panel Encabezado
       panelSuperior = new JPanel();
       panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
       
       buttonInicio = new JButton("Inicio");
       buttonInicio.addActionListener(this);
       panelSuperior.add(buttonInicio);
       
       buttonAtras = new JButton("Atras");
       buttonAtras.addActionListener(this);
       panelSuperior.add(buttonAtras);
       
       JLabel lblTitulo = new JLabel("Agregar Administrador");
        panelSuperior.add(lblTitulo);
        
        buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(this);
        panelSuperior.add(buttonSalir);
        
       //Panel Contenido
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        JLabel id=new JLabel("id");
        panelContenido.add(id);
        
        JLabel nom=new JLabel("Nombre");
        panelContenido.add(nom);
        
        JLabel AP=new JLabel("Apellido Paterno");
        panelContenido.add(AP);
        
        JLabel AM=new JLabel("Apellido Materno");
        panelContenido.add(AM);
        
        JLabel corr=new JLabel("Correo");
        panelContenido.add(corr);
        
        JLabel contr=new JLabel("Contraseña");
        panelContenido.add(contr);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
         
        idAdm=new JTextField();
        idAdm.setSize(10,50);
        panelContenido.add(idAdm);
        
        Nomb=new JTextField();
        Nomb.setSize(10,50);
        panelContenido.add(Nomb);
        
        ApPater=new JTextField();
        ApPater.setSize(10,50);
        panelContenido.add(ApPater);
        
        ApMater=new JTextField();
        ApMater.setSize(10,50);
        panelContenido.add(ApMater);
        
        mail=new JTextField();
        mail.setSize(10,50);
        panelContenido.add(mail);
        
        contras=new JTextField();
        contras.setSize(10,50);
        panelContenido.add(contras);
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        
       
      //Panel Botones
        panelbtn=new JPanel();
        panelbtn.setLayout(new BoxLayout(panelbtn, BoxLayout.X_AXIS));
        
        //Inserta los datos en la tabla Examen
        crear=new JButton("Crear");
        crear.addActionListener(this);
        panelbtn.add(crear);
        
        //Hace la consulta de lo que agrego
        verificar=new JButton("Verificar");
        verificar.addActionListener(this);
        panelbtn.add(verificar);
        
        //Vacia los campos para que el usuario pueda escribir
        vaciar=new JButton("Vaciar");
        vaciar.addActionListener(this);
        panelbtn.add(vaciar);
        
        
        
         add(panelSuperior);
         add(panelContenido);
         add(panelbtn);
      
     }
     
     public void VaciarTextField(){
        idAdm.setText("");
        Nomb.setText("");
        ApPater.setText("");
        ApMater.setText("");
        mail.setText("");
        contras.setText(""); 
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==buttonInicio){
         framePrincipal.mostrarPanel(new InicioAdmin(framePrincipal));
       }
       
       if(e.getSource()==buttonAtras){
         framePrincipal.mostrarPanel(new ConfiguracionCuentaAdmin(framePrincipal));
       }
      
       if(e.getSource()==buttonSalir){
         framePrincipal.mostrarPanel(new InicioSesion(framePrincipal)); 
       }
       
       if(e.getSource()==crear){
          
       }
       
       if(e.getSource()==vaciar){
           VaciarTextField();
       }    
    }
    
}
