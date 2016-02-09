package net.erickpineda.calculadora;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
        setIconImage(Toolkit.getDefaultToolkit().getImage(icono));
        setTitle("Super Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        getContentPane().setLayout(new BorderLayout(0, 0));

        Calculadora calculadora = new Calculadora();
        getContentPane().add(calculadora, BorderLayout.CENTER);

        BarraMenu menuBar = new BarraMenu();
        setJMenuBar(menuBar);
        teclado();
    }
    /**
     * Atajos de teclado.
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
