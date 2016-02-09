package net.erickpineda.sumamental;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

public class App extends JFrame {
    private static final long serialVersionUID = 1L;
    private URL icono = getClass().getResource("/mantis.png");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App frame = new App();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public App() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(icono));
        setTitle("¡¡A Sumar!!");
        setBounds(400, 100, 450, 350);
        teclado();

        BarraMenu menuBar = new BarraMenu();
        menuBar.setContainer(getContentPane());
        menuBar.iniciarTestMental();
        menuBar.setLocation(getLocation());
        setJMenuBar(menuBar);

        panelSur();
    }
    /**
     * Panel de información.
     */
    private void panelSur() {
        JPanel panel = new JPanel();
        panel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
        panel.setBackground(SystemColor.text);
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel informacion = new JLabel("Información");
        informacion.setFont(new Font("Yu Gothic Light", Font.BOLD, 12));
        informacion.setHorizontalTextPosition(SwingConstants.CENTER);
        informacion.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(informacion);
    }
    /**
     * Atajos del teclado.
     */
    private void teclado() {
        JPanel p = new JPanel();
        ActionMap actionMap = new ActionMapUIResource();
        actionMap.put("action_exit", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        InputMap keyMap = new ComponentInputMap(p);
        keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK),
                "action_exit");

        SwingUtilities.replaceUIActionMap(p, actionMap);
        SwingUtilities.replaceUIInputMap(p, JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);
        getRootPane().add(p);
    }

}
