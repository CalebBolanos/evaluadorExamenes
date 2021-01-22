/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluadorexamenes;

import Admin.InicioAdmin;
import Base.Conexion;
import Base.Validaciones;
import Cliente.Inicio;
import Sesion.Usuario;
import static Sesion.Usuario.ADMINISTRADOR;
import static Sesion.Usuario.CLIENTE;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * Clase en donde se muestra la gui para que tanto clientes como 
 * administradores inicien sesion. 
 */
public class InicioSesion extends JPanel implements ActionListener {

    private final FramePrincipal framePrincipal;

    private JLabel labelIniciarSesion, labelUsuario, labelContrasena;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JButton buttonIniciarSesion;

    private Conexion base;

    
    /**
     * Constructor de la clase, el cual tiene como parametro 
     * framePrincipal el cual es un JFrame en donde se pondra la nueva gui 
     * generada por esta clase
     */
    public InicioSesion(FramePrincipal framePrincipal) {
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }

    
    /**
     * metodo encargado de inicializar los componentes de java swing que forman 
     * parte de la GUI. cabe mencionar que tambien se inicializan algunas 
     * variables de instancia como base, objeto utilizado para hacer la 
     * conexion a la base de datos
     */
    private void crearGUI() {
        setLayout(new GridBagLayout());

        base = new Conexion();

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
    
    /**
     * dentro de este metodo implementado se verifica si se ha presionado 
     * buttonIniciarSesion para hacer una consulta a la base de datos para 
     * iniciar sesion y obtener la informacion del usuario. Dependiendo del tipo 
     * de usuario se redigira a Inicio(Clientes) o a InicioAdmin(Administrador)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonIniciarSesion) {
            String usuario = txtUsuario.getText();
            String contrasena = new String(pswContrasena.getPassword());

            if (Validaciones.StringsNoVacios(usuario, contrasena)) {
                try {
                    base.conectar();
                    ResultSet rs = base.ejecutaQuery("call spIniciarSesion(\"" + usuario + "\", \"" + contrasena + "\");");//"++"
                    if (rs.next()) {
                        if (rs.getString("msj").equals("ok")) {
                            int id = rs.getInt("id");
                            int tipo = rs.getInt("tipo");
                            String nombre = rs.getString("nom");
                            String paterno = rs.getString("pat");
                            String materno = rs.getString("mat");
                            String correo = rs.getString("corr");
                            Usuario.almacenarDatos(id, tipo, nombre, paterno, materno, correo);
                            
                        } else {
                            JOptionPane.showMessageDialog(null, rs.getString("msj"), "Iniciar Sesión", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    base.cierraConexion();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos vacios", "Iniciar Sesión", JOptionPane.INFORMATION_MESSAGE);
            }
            switch (Usuario.getIdTipo()){
                case CLIENTE:
                    framePrincipal.mostrarPanel(new Inicio(framePrincipal));
                    break;
                case ADMINISTRADOR:
                    framePrincipal.mostrarPanel(new InicioAdmin(framePrincipal));
                    break;
            }
        }
    }

}
