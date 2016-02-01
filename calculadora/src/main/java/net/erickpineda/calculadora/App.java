package net.erickpineda.calculadora;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;

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
    }
}
