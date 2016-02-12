package net.erickpineda.sumamental;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(icono));
        setTitle("¡¡A Sumar!!");
        setBounds(400, 100, 450, 350);

        BarraMenu menuBar = new BarraMenu(getContentPane());
        menuBar.iniciarTestMental();
        menuBar.setLocation(getLocation());
        setJMenuBar(menuBar);
    }
}
