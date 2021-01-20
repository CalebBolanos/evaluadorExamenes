package Gui;

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
public class CreadorExamen extends JPanel implements ActionListener{
     FramePrincipal framePrincipal;
     JPanel panelSuperior, panelContenido,panelbtn;
     JButton buttonInicio, buttonSalir,crear,vaciar,verificar;
     JTextField idEx,TypEx,FechEx,LpEx,TmpoEx;
     
     public CreadorExamen(FramePrincipal framePrincipal){
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
       
       JLabel lblTitulo = new JLabel("Crear Examen Nuevo");
        panelSuperior.add(lblTitulo);
        
        buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(this);
        panelSuperior.add(buttonSalir);
        
       //Panel Contenido
        
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        
        JLabel id=new JLabel("id");
        panelContenido.add(id);
        
        JLabel Tex=new JLabel("Tipo de Examen");
        panelContenido.add(Tex);
        
        JLabel Fch=new JLabel("Fecha");
        panelContenido.add(Fch);
        
        JLabel Lpr=new JLabel("Ultima Pregunta");
        panelContenido.add(Lpr);
        
        JLabel Ti=new JLabel("Tiempo");
        panelContenido.add(Ti);
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
         
        idEx=new JTextField();
        idEx.setSize(10,50);
        panelContenido.add(idEx);
        
        TypEx=new JTextField();
        TypEx.setSize(10,50);
        panelContenido.add(TypEx);
        
        FechEx=new JTextField();
        FechEx.setSize(10,50);
        panelContenido.add(FechEx);
        
        LpEx=new JTextField();
        LpEx.setSize(10,50);
        panelContenido.add(LpEx);
        
        TmpoEx=new JTextField();
        TmpoEx.setSize(10,50);
        panelContenido.add(TmpoEx);
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
        idEx.setText("");
        TypEx.setText("");
        FechEx.setText("");
        LpEx.setText("");
        TmpoEx.setText(""); 
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
          
       }
       
       if(e.getSource()==vaciar){
           VaciarTextField();
       }    
    }
    
}

