package net.erickpineda.sumamental;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class TestSuma extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField respuesta;
    private JLabel tiempo;
    private JLabel operacion;
    private JLabel mensaje;
    private JButton calcular;

    /**
     * Create the panel.
     */
    public TestSuma() {
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Calcula", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 240, 240)));

        setBackground(SystemColor.inactiveCaptionText);
        setLayout(new MigLayout("", "[][grow][]", "[][grow][][][][][][][][]"));

        crearComponentes();
        agregarEventos();
    }

    private void crearComponentes() {
        tiempo = new JLabel("0");
        tiempo.setName("tiempo");
        tiempo.setFont(new Font("Yu Gothic Light", Font.BOLD, 40));
        tiempo.setForeground(SystemColor.textHighlightText);
        add(tiempo, "cell 1 0 1 3,alignx center");

        operacion = new JLabel("Operacion");
        operacion.setName("operacion");
        operacion.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
        operacion.setForeground(SystemColor.textHighlightText);
        add(operacion, "cell 1 3,alignx center");

        mensaje = new JLabel("mensaje");
        mensaje.setName("mensaje");
        mensaje.setFont(new Font("Yu Gothic Light", Font.BOLD, 11));
        mensaje.setForeground(SystemColor.textHighlightText);
        add(mensaje, "cell 1 4,alignx center");

        respuesta = new JTextField();
        respuesta.setName("respuesta");
        respuesta.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        respuesta.setBackground(SystemColor.controlHighlight);
        respuesta.setFont(new Font("Yu Gothic Light", Font.BOLD, 12));
        respuesta.setHorizontalAlignment(SwingConstants.CENTER);
        add(respuesta, "cell 1 6,grow");

        calcular = new JButton("Calcular");
        calcular.setName("calcular");
        calcular.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
        calcular.setForeground(SystemColor.textHighlightText);
        calcular.setBackground(SystemColor.controlDkShadow);
        add(calcular, "cell 1 8,alignx center,growy");
    }

    private void agregarEventos() {
        addActions(tiempo);
        addActions(operacion);
        addActions(mensaje);
        addActions(respuesta);
        addActions(calcular);
    }

    private void addActions(JComponent componente) {
        componente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                
            }
        });
    }
}
