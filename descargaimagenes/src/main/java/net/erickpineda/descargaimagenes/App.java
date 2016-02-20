package net.erickpineda.descargaimagenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class App extends JFrame {
  private static final long serialVersionUID = 1L;
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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(400, 150, 550, 300);
    contentPanelPrincipal();
    crearDescargadorImagen();
  }

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

  private void crearDescargadorImagen() {
    JPanel di = new DescargadorImg();
    contentPane.add(di, BorderLayout.CENTER);
  }
}
