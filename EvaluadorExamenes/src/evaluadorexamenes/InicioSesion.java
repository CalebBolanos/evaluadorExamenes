/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluadorexamenes;

import Cliente.Inicio;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author calebbolanos
 */
public class InicioSesion extends JPanel implements ActionListener{
    
    private final FramePrincipal framePrincipal;
    
    private JLabel labelIniciarSesion, labelUsuario, labelContrasena;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JButton buttonIniciarSesion;
    
    public InicioSesion(FramePrincipal framePrincipal){
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }

    private void crearGUI() {
        setLayout(new GridBagLayout());
        
        GridBagConstraints dimensiones = new GridBagConstraints();
        dimensiones.fill = GridBagConstraints.HORIZONTAL;
        
        labelIniciarSesion = new JLabel("Generador Evaluador de Examenes");
        labelIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
        dimensiones.gridx = 0;
        dimensiones.gridy = 0;
        dimensiones.gridwidth = 4;
        add(labelIniciarSesion, dimensiones);
        
        
        labelIniciarSesion = new JLabel("Iniciar Sesión");
        labelIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
        dimensiones.gridx = 0;
        dimensiones.gridy = 1;
        dimensiones.gridwidth = 4;
        add(labelIniciarSesion, dimensiones);
        
        labelUsuario = new JLabel("Usuario");
        dimensiones.gridx = 0;
        dimensiones.gridy = 2;
        dimensiones.gridwidth = 1;
        add(labelUsuario, dimensiones);
        
        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(300, 20));
        dimensiones.gridx = 1;
        dimensiones.gridy = 2;
        dimensiones.gridwidth = 3;
        add(txtUsuario, dimensiones);
        
        labelContrasena = new JLabel("Contraseña");
        dimensiones.gridx = 0;
        dimensiones.gridy = 3;
        dimensiones.gridwidth = 1;
        add(labelContrasena, dimensiones);
        
        pswContrasena = new JPasswordField();
        txtUsuario.setPreferredSize(new Dimension(300, 20));
        dimensiones.gridx = 1;
        dimensiones.gridy = 3;
        dimensiones.gridwidth = 3;
        add(pswContrasena, dimensiones);
        
        buttonIniciarSesion = new JButton("Iniciar Sesion");
        buttonIniciarSesion.addActionListener(this);
        dimensiones.gridx = 0;
        dimensiones.gridy = 4;
        dimensiones.gridwidth = 4;
        add(buttonIniciarSesion, dimensiones);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        framePrincipal.mostrarPanel(new Inicio(framePrincipal));
    }
}
