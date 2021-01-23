/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Base.Conexion;
import Gui.BotonExamen;
import Gui.PanelReactivo;
import Modelo.Examen;
import Modelo.Reactivo;
import static Modelo.Reactivo.SIN_RESPONDER;
import Sesion.Usuario;
import evaluadorexamenes.FramePrincipal;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 *
 * @author calebbolanos
 */
public class AplicadorExamen extends JPanel implements ActionListener {

    FramePrincipal framePrincipal;
    Examen examen;
    Conexion base;

    JPanel panelSuperior, panelContenido, panelControles, panelReactivos;
    JScrollPane scrollPane;
    JLabel lblTituloExamen, lblTiempo;
    JButton buttonAbandonar, buttonSiguiente;
    Thread tiempo;

    Vector<Reactivo> reactivos;
    List<PanelReactivo> panelPreguntas;

    int posicionPregunta;

    public AplicadorExamen(FramePrincipal framePrincipal, Examen examen) {
        super();
        this.framePrincipal = framePrincipal;
        this.examen = examen;
        this.posicionPregunta = examen.getUltimaPregunta();
        crearGUI();
    }

    private void crearGUI() {
        base = new Conexion();
        /**
         * Parte superior de la GUI en donde va el titulo y botones con opciones
         *
         */
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));

        buttonAbandonar = new JButton("Abandonar");
        buttonAbandonar.addActionListener(this);

        lblTituloExamen = new JLabel(examen.getTitulo());

        lblTiempo = new JLabel("");

        panelSuperior.add(buttonAbandonar);
        panelSuperior.add(lblTituloExamen);
        panelSuperior.add(lblTiempo);

        /**
         * Panel con el contenido del examen, en donde se mostraran cada uno de
         * los reactivos
         *
         */
        obtenerReactivos();

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        panelReactivos = new JPanel(new CardLayout());
        for (int i = 0; i < panelPreguntas.size(); i++) {
            panelReactivos.add(panelPreguntas.get(i), String.valueOf(i));
        }
        CardLayout cl = (CardLayout) (panelReactivos.getLayout());
        cl.show(panelReactivos, String.valueOf(posicionPregunta));

        panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.X_AXIS));

        buttonSiguiente = new JButton("Siguiente");
        buttonSiguiente.addActionListener(this);
        panelControles.add(buttonSiguiente);

        panelContenido.add(panelReactivos);
        panelContenido.add(panelControles);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(panelSuperior);
        add(panelContenido);
        /**
         * Timer timer = new Timer(); timer.schedule(new Temporalizador(1, 5),
         * 0, 1000);*
         */
        
        obtenerTiempo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSiguiente) {
            if (!panelPreguntas.get(posicionPregunta).obtenerRespuesta().equals(SIN_RESPONDER)) {
                ponerRespuesta();
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estas seguro de pasar a la siguiente pregunta? Aun no has seleccionado una respuesta", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ponerRespuesta();
                } else {
                    return;
                }
            }
            if (buttonSiguiente.getText().equals("Terminar examen") && posicionPregunta == reactivos.size()) {
                terminarExamen("Examen finalizado");
            }
        }
        if (e.getSource() == buttonAbandonar) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que deseas abandonar el examen? Se guardara tu progreso", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                tiempo.stop();
                framePrincipal.mostrarPanel(new Inicio(framePrincipal));
            }
        }

    }

    public void obtenerReactivos() {
        reactivos = new Vector<>();
        panelPreguntas = new ArrayList<>();
        Reactivo reactivox;
        try {
            base.conectar();
            ResultSet rs = base.ejecutaQuery("select * from mostraexa where idExamen = " + examen.getIdExamen() + ";");//"++"
            while (rs.next()) {
                reactivox = new Reactivo(
                        rs.getInt("idPregunta"),
                        rs.getString("pregunta"),
                        rs.getString("opcionA"),
                        rs.getString("opcionB"),
                        rs.getString("opcionC"),
                        rs.getString("opcionD"),
                        SIN_RESPONDER
                );
                reactivos.add(reactivox);
                panelPreguntas.add(new PanelReactivo(reactivox));
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void ponerRespuesta() {
        reactivos.get(posicionPregunta).setRespuesta(panelPreguntas.get(posicionPregunta).obtenerRespuesta());
        try {
            base.conectar();
            ResultSet rs = base.ejecutaQuery("call Respues(" + Usuario.getId() + "," + reactivos.get(posicionPregunta).getIdReactivo() + ",\"" + reactivos.get(posicionPregunta).getRespuesta() + "\");");//"++"
            if (rs.next()) {
                posicionPregunta++;
                if (rs.getString("msj").equals("ok")) {
                    System.out.println("guardado a la base");
                    ResultSet rsprogreso = base.ejecutaQuery("call AgreClien(" + Usuario.getId() + ", " + posicionPregunta + ", " + examen.getIdExamen() + ");");
                    if (rsprogreso.next()) {

                    }
                }
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        CardLayout cl = (CardLayout) (panelReactivos.getLayout());
        cl.show(panelReactivos, String.valueOf(posicionPregunta));
        if (posicionPregunta == panelPreguntas.size() - 1) {
            buttonSiguiente.setText("Terminar examen");
        }
    }

    public void terminarExamen(String mensaje) {
        calificarExamen();
        //llamada a base de datos para guardar respuestas
        JOptionPane.showMessageDialog(null, mensaje, "Examen concluido", JOptionPane.INFORMATION_MESSAGE);
        framePrincipal.mostrarPanel(new Resultados(framePrincipal, examen));
        tiempo.stop();
    }

    public void calificarExamen() {
        int calificacion = 0;
        try {
            base.conectar();
            base.ejecuta("SET sql_mode=(SELECT REPLACE(@@sql_mode,\"ONLY_FULL_GROUP_BY\",\"\"));");
            if (posicionPregunta < 10) {
                ResultSet rsprogreso = base.ejecutaQuery("call AgreClien(" + Usuario.getId() + ", " + 10 + ", " + examen.getIdExamen() + ");");
            }
            ResultSet rs = base.ejecutaQuery("select respuestaCorrecta, respuestaCliente from vwmostrarResultadosCompletos "
                    + "where idCliente = " + Usuario.getId() + " and idExamen = " + examen.getIdExamen() + ";");//
            while (rs.next()) {
                if (rs.getString("respuestaCorrecta").equals(rs.getString("respuestaCliente"))) {
                    calificacion++;
                }
            }
            ResultSet rsCalificacion = base.ejecutaQuery("call spPonerCalificacionExamen(" + examen.getIdExamen() + ", " + Usuario.getId() + ", " + calificacion + ");");
            if (rsCalificacion.next()) {
                if (rsCalificacion.getString("msj").equals("ok")) {
                    System.out.println("Calificacion guardada");
                }
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        examen.setCalificación(calificacion);
    }
    
    public void obtenerTiempo(){
        try {
            base.conectar();
            ResultSet rs = base.ejecutaQuery("select tiempo from examen where idExamen = "+examen.getIdExamen()+";");//"++"
            if (rs.next()) {
                Time duracion = rs.getTime("tiempo");
                tiempo = new Thread(new Temporalizador(duracion.getMinutes(), duracion.getSeconds()));
                tiempo.start();
            }
            base.cierraConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    class Temporalizador implements Runnable {

        int minutos, segundos;

        public Temporalizador(int minutos, int segundos) {
            this.minutos = minutos;
            this.segundos = segundos;
        }

        public void run() {
            while (true) {
                try {
                    if (segundos == 0) {

                        if (minutos == 0) {
                            System.out.println("acabo");
                            terminarExamen("Se agoto el tiempo para responder el examen");
                            //tiempo.stop();
                        } else {
                            segundos = 60;
                        }
                        minutos--;
                    }

                    segundos--;
                    System.out.println(minutos + ":" + segundos);
                    lblTiempo.setText("Tiempo restante: " + minutos + ":" + segundos);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AplicadorExamen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
