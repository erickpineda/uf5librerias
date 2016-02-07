package net.erickpineda.sumamental;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class TestMental extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField respuesta;
    private JLabel lblTiempo;
    private JLabel lblOperacion;
    private JLabel lblMensaje;
    private JButton btnCalcular;
    private Timer timer;
    private Operacion operacion;
    private String text = "";
    private long mili;
    private int cont = 0;

    /**
     * Create the panel.
     */
    public TestMental() {
        operacion = new Operacion(Util.rand(0, 12), Util.rand(0, 12));
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                "Calcula", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 240, 240)));

        setBackground(SystemColor.controlShadow);
        setLayout(new MigLayout("", "[][grow][]", "[][grow][][][][][][][][]"));

        crearComponentes();
        agregarEventos();
    }

    private void crearComponentes() {
        lblTiempo = new JLabel("0");
        lblTiempo.setName("tiempo");
        lblTiempo.setFont(new Font("Yu Gothic Light", Font.BOLD, 40));
        lblTiempo.setForeground(SystemColor.textHighlightText);
        add(lblTiempo, "cell 1 0 1 3,alignx center");

        lblOperacion = new JLabel("Operacion");
        lblOperacion.setName("operacion");
        lblOperacion.setFont(new Font("Yu Gothic Light", Font.BOLD, 18));
        lblOperacion.setForeground(SystemColor.textHighlightText);
        text = "¿Cuánto es " + operacion.getOperacion() + " ?";
        lblOperacion.setText(text);
        mili = System.currentTimeMillis();
        add(lblOperacion, "cell 1 3,alignx center");

        lblMensaje = new JLabel("mensaje");
        lblMensaje.setName("mensaje");
        lblMensaje.setFont(new Font("Yu Gothic Light", Font.BOLD, 11));
        lblMensaje.setForeground(SystemColor.textHighlightText);
        add(lblMensaje, "cell 1 4,alignx center");

        respuesta = new IntegerField();
        respuesta.setName("respuesta");
        respuesta.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        respuesta.setBackground(SystemColor.controlHighlight);
        respuesta.setFont(new Font("Yu Gothic Light", Font.BOLD, 12));
        respuesta.setHorizontalAlignment(SwingConstants.CENTER);
        add(respuesta, "cell 1 6,grow");

        btnCalcular = new JButton("Calcular");
        btnCalcular.setName("calcular");
        btnCalcular.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
        btnCalcular.setForeground(SystemColor.textHighlightText);
        btnCalcular.setBackground(SystemColor.controlDkShadow);
        add(btnCalcular, "cell 1 8,alignx center,growy");
    }

    private void agregarEventos() {
        addActions(btnCalcular);
    }

    private void addActions(JComponent componente) {
        componente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                realizarCalculos();
            }
        });
    }

    public void realizarCalculos() {
        text = "¿Cuánto es ";
        if (!respuesta.getText().isEmpty()) {
            if (operacion.esCorrecto(respuesta.getText())) {
                lblMensaje.setText("Correcto");
                lblMensaje.setForeground(Color.green);
                operacion.setN1(Util.rand(0, 12));
                operacion.setN2(Util.rand(0, 12));
                cont++;
            } else {
                lblMensaje.setText("Incorrecto");
                lblMensaje.setForeground(Color.red);
            }
        }
        respuesta.setText("");
        text += operacion.getOperacion() + " ?";
        lblOperacion.setText(text);
    }

    public void iniciarConteo() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double mil = System.currentTimeMillis();
                mil -= mili;
                lblTiempo.setText(String.format("%.3f", mil / 1000));
                if (cont == 3) {
                    timer.stop();
                    showDialog(lblTiempo.getText());
                }
            }
        };
        timer = new Timer(1, listener);
        timer.start();
    }

    private JDialog showDialog(final String msj) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Reglas");
        dialog.setResizable(false);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
        panel.setBackground(SystemColor.text);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        JLabel label = new JLabel("Tiempo que has tardado: " + msj + " segundos");
        label.setFont(new Font("Yu Gothic Light", Font.BOLD, 12));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        dialog.add(panel);

        dialog.setSize(400, 80);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return dialog;
    }
}
