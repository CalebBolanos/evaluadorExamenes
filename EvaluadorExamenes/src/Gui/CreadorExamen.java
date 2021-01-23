package Gui;

import Admin.InicioAdmin;
import Base.Conexion;
import Base.Validaciones;
import evaluadorexamenes.FramePrincipal;
import evaluadorexamenes.InicioSesion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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
public class CreadorExamen extends JPanel implements ActionListener{
     FramePrincipal framePrincipal;
     JPanel panelSuperior, panelContenido,panelbtn;
     JButton buttonInicio, buttonSalir,crear,vaciar,verificar;
     JTextField FechEx,Tmpo,TypExa;
     
      private Conexion base;
      
     public CreadorExamen(FramePrincipal framePrincipal){
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
       
       JLabel lblTitulo = new JLabel("Crear Examen Nuevo");
        panelSuperior.add(lblTitulo);
        
        buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(this);
        panelSuperior.add(buttonSalir);
        
       //Panel Contenido
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
       
        
        JLabel Fch=new JLabel("Fecha");
        panelContenido.add(Fch);
        
        JLabel Lpr=new JLabel("Tiempo");
        panelContenido.add(Lpr);
        
        JLabel Ti=new JLabel("Nombre");
        panelContenido.add(Ti);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       
        
        FechEx=new JTextField();
        FechEx.setSize(10,50);
        panelContenido.add(FechEx);
        
        Tmpo=new JTextField();
        Tmpo.setSize(10,50);
        panelContenido.add(Tmpo);
        
        TypExa=new JTextField();
        TypExa.setSize(10,50);
        panelContenido.add(TypExa);
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       
        
       
      //Panel Botones
        panelbtn=new JPanel();
        panelbtn.setLayout(new BoxLayout(panelbtn, BoxLayout.X_AXIS));
        
        //Inserta los datos en la tabla Examen
        crear=new JButton("Crear");
        crear.addActionListener(this);
        panelbtn.add(crear);
        
        //Vacia los campos para que el usuario pueda escribir
        vaciar=new JButton("Vaciar");
        vaciar.addActionListener(this);
        panelbtn.add(vaciar);
        
        
        
         add(panelSuperior);
         add(panelContenido);
         add(panelbtn);
      
     }
     
     public void VaciarTextField(){
        FechEx.setText("");
        Tmpo.setText("");
        TypExa.setText(""); 
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==buttonInicio){
         framePrincipal.mostrarPanel(new InicioAdmin(framePrincipal));
       }
      
       if(e.getSource()==buttonSalir){
         framePrincipal.mostrarPanel(new InicioSesion(framePrincipal)); 
       }
       
       if(e.getSource()==crear){
          
           int id;
           String  f,tm,te;
           f=FechEx.getText();
           tm=Tmpo.getText();
           te=TypExa.getText();
           
            if(Validaciones.StringsNoVacios(f,tm,te)){
               try {
                   base.conectar();
                   ResultSet rs = base.ejecutaQuery("call CreaExam(\"" + f + "\", \"" + tm + "\");");
                   if(rs.next()){
                    id=rs.getInt("idExamen");
                    ResultSet rs2=base.ejecutaQuery("call RandRe(\"" + id + "\", \"" + te+ "\");");
                     if(rs2.next()){
                        if (rs2.getString("msj").equals("ok")) {
                            JOptionPane.showMessageDialog(null, "Examen Agregado Corrrectamente");
                        } else {
                            JOptionPane.showMessageDialog(null, rs.getString("msj1"));
                        }   
                   }
                  } 
                   base.cierraConexion();
               } catch (SQLException ex) {
                   Logger.getLogger(CreadorExamen.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos vacios", "Agregar Admin", JOptionPane.INFORMATION_MESSAGE);
            }
          
       }   
    }
    
}