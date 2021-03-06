package net.erickpineda.descargaimagenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class App extends JFrame {
  private static final long serialVersionUID = 1L;
  private URL icono = getClass().getResource("/mantis.png");
  private JPanel contentPane;

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
    setTitle("Descargador de imágenes");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(Toolkit.getDefaultToolkit().getImage(icono));
    setBounds(400, 150, 550, 300);
    contentPanelPrincipal();
    crearDescargadorImagen();
  }

  /**
   * Contenido principal del programa.
   */
  private void contentPanelPrincipal() {
    contentPane = new JPanel();
    contentPane.setBorder(new CompoundBorder(
        new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Descarga Imágenes",
            TitledBorder.LEADING, 
            TitledBorder.TOP, 
            null, 
            new Color(0, 0, 0)),
        new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
  }

  /**
   * Crea el JPanel de la clase {@code DescargadorImg()} para ejecutar el programa.
   */
  private void crearDescargadorImagen() {
    JPanel di = new DescargadorImg();
    contentPane.add(di, BorderLayout.CENTER);
  }
}
