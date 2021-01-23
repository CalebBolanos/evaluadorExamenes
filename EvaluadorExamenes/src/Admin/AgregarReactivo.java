/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Base.Conexion;
import Base.Validaciones;
import Gui.AgregarAdmin;
import Sesion.Usuario;
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
 * @author calebbolanos
 */
public class AgregarReactivo extends JPanel implements ActionListener {

    FramePrincipal framePrincipal;
    JPanel panelSuperior, panelContenido, panelbtn;
    JButton buttonInicio, buttonSalir, buttonAtras, Agr, vaciar;
    JTextField txtPregunta, txtOpcionA, txtOpcionB, txtOpcionC, txtOpcionD, txtRespuesta;

    private Conexion base;

    public AgregarReactivo(FramePrincipal framePrincipal) {
        super();
        this.framePrincipal = framePrincipal;
        crearGUI();
    }

    public void crearGUI() {

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

        JLabel lblTitulo = new JLabel("Agregar Reactivo");
        panelSuperior.add(lblTitulo);

        buttonSalir = new JButton("Salir");
        buttonSalir.addActionListener(this);
        panelSuperior.add(buttonSalir);

        //Panel Contenido
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        JLabel preg = new JLabel("Pregunta");
        panelContenido.add(preg);

        JLabel opcA = new JLabel("Opcion A");
        panelContenido.add(opcA);

        JLabel opcB = new JLabel("Opcion B");
        panelContenido.add(opcB);

        JLabel opcC = new JLabel("Opcion c");
        panelContenido.add(opcC);

        JLabel opcD = new JLabel("Opcion D");
        panelContenido.add(opcD);

        JLabel resp = new JLabel("Respuesta");
        panelContenido.add(resp);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        txtPregunta = new JTextField();
        txtPregunta.setSize(10, 50);
        panelContenido.add(txtPregunta);

        txtOpcionA = new JTextField();
        txtOpcionA.setSize(10, 50);
        panelContenido.add(txtOpcionA);

        txtOpcionB = new JTextField();
        txtOpcionB.setSize(10, 50);
        panelContenido.add(txtOpcionB);

        txtOpcionC = new JTextField();
        txtOpcionC.setSize(10, 50);
        panelContenido.add(txtOpcionC);

        txtOpcionD = new JTextField();
        txtOpcionD.setSize(10, 50);
        panelContenido.add(txtOpcionD);

        txtRespuesta = new JTextField();
        txtRespuesta.setSize(10, 50);
        panelContenido.add(txtRespuesta);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Panel Botones
        panelbtn = new JPanel();
        panelbtn.setLayout(new BoxLayout(panelbtn, BoxLayout.X_AXIS));

        //Inserta los datos en la tabla Examen
        Agr = new JButton("Agregar");
        Agr.addActionListener(this);
        panelbtn.add(Agr);

        //Hace la consulta de lo que agrego
        vaciar = new JButton("Vaciar");
        vaciar.addActionListener(this);
        panelbtn.add(vaciar);

        add(panelSuperior);
        add(panelContenido);
        add(panelbtn);

    }

    public void VaciarTextField() {
        txtPregunta.setText("");
        txtOpcionA.setText("");
        txtOpcionB.setText("");
        txtOpcionC.setText("");
        txtOpcionD.setText("");
        txtRespuesta.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonInicio) {
            framePrincipal.mostrarPanel(new InicioAdmin(framePrincipal));
        }

        if (e.getSource() == buttonAtras) {
            framePrincipal.mostrarPanel(new ConfiguracionCuentaAdmin(framePrincipal));
        }

        if (e.getSource() == buttonSalir) {
            framePrincipal.mostrarPanel(new InicioSesion(framePrincipal));
        }

        if (e.getSource() == Agr) {
            String pregunta, opcionA, opcionB, opcionC, opcionD, respuesta;
            pregunta = txtPregunta.getText();
            opcionA = txtOpcionA.getText();
            opcionB = txtOpcionB.getText();
            opcionC = txtOpcionC.getText();
            opcionD = txtOpcionD.getText();
            respuesta = txtRespuesta.getText();
            if (Validaciones.StringsNoVacios(pregunta, opcionA, opcionB, opcionC, opcionD, respuesta)) {
                try {
                    base.conectar();
                    ResultSet rs = base.ejecutaQuery("call CreaReac(\""+pregunta+"\", \""+opcionA+"\", \""+opcionB+"\", \""+opcionC+"\", \""+opcionD+"\", \""+respuesta+"\", "+Usuario.getId()+");");
                    if (rs.next()) {
                        if (rs.getString("msj").equals("ok")) {
                            JOptionPane.showMessageDialog(null, "Reactivo agregado");
                        }
                    }
                    base.cierraConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llena todos los campos vacios", "Agregar Reactivo", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if (e.getSource() == vaciar) {
            VaciarTextField();
        }
    }

}
