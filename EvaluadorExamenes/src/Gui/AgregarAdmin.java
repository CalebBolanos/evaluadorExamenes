package Gui;

import Admin.ConfiguracionCuentaAdmin;
import Admin.InicioAdmin;
import Base.Conexion;
import Base.Validaciones;
import evaluadorexamenes.FramePrincipal;
import evaluadorexamenes.InicioSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lalo
 */
public class AgregarAdmin extends JPanel implements ActionListener{
     FramePrincipal framePrincipal;
     JPanel panelSuperior, panelContenido,panelbtn;
     JButton buttonInicio, buttonSalir,buttonAtras,Agr,vaciar;
     JTextField Nomb,ApPater,ApMater,mail,contras;
     
     private Conexion base;
     
     public AgregarAdmin(FramePrincipal framePrincipal){
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }
     
     public void crearGUI(){
         
        base = new Conexion();
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
        Agr=new JButton("Agregar");
        Agr.addActionListener(this);
        panelbtn.add(Agr);
        
        //Vacia los campos para que el usuario pueda escribir
        vaciar=new JButton("Vaciar");
        vaciar.addActionListener(this);
        panelbtn.add(vaciar);
        
        
        
         add(panelSuperior);
         add(panelContenido);
         add(panelbtn);
      
     }
     
     public void VaciarTextField(){
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
       
       if(e.getSource()==Agr){
           String n,p,m,cr,cn,a;
           n=Nomb.getText();
           p=ApPater.getText();
           m=ApMater.getText();
           cr=mail.getText();
           cn=contras.getText();
           a="a";
           if(Validaciones.StringsNoVacios(n,p,m,cr,cn)){
               try {
                   base.conectar();
                   ResultSet rs = base.ejecutaQuery("call spRegistrarCliente(\"" + n + "\", \"" + p + "\", \"" + m + "\", \"" + cr + "\", \"" + cn + "\",\""+a+"\");");
                   if (rs.next()) {
                        if (rs.getString("msj1").equals("Se agrego nuevo Admin")) {
                            JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, rs.getString("msj2"));
                        }
                    }
                   base.cierraConexion();
               } catch (SQLException ex) {
                   Logger.getLogger(AgregarAdmin.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos vacios", "Agregar Admin", JOptionPane.INFORMATION_MESSAGE);
            }
       }
       
       
       if(e.getSource()==vaciar){
           VaciarTextField();
       }    
    }
    
}
