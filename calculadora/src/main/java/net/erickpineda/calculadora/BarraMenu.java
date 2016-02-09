package net.erickpineda.calculadora;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class BarraMenu extends JMenuBar {
    private static final long serialVersionUID = 1L;
    private static final String ICO_SALIR = "/javax/swing/plaf/metal/icons/ocean/close.gif";
    private static final String ICO_ACERCA = "/javax/swing/plaf/basic/icons/JavaCup16.png";
    private final URL URL_SALIR = getClass().getResource(ICO_SALIR);
    private final URL URL_ACERCA = getClass().getResource(ICO_ACERCA);
    private static final String[] MENUS = { "Archivo", "Ventana" };

    /**
     * Create the panel.
     */
    public BarraMenu() {
        setBackground(SystemColor.window);
        crearMenus();
    }
    /**
     * Recorre un array bidimensional para crear los items o submenús de los distintos apartados.
     */
    private void crearMenus() {
        for (int i = 0; i < MENUS.length; i++) {
            JMenu jm = new JMenu(MENUS[i]);
            JMenuItem jmI = null;
            if (i == 0) {
                jmI = new JMenuItem("Salir Ctrl + Q");
                jmI.setIcon(new ImageIcon(URL_SALIR));
                jmI.setName("salir");
            } else {
                jmI = new JMenuItem("Acerca de");
                jmI.setIcon(new ImageIcon(URL_ACERCA));
                jmI.setName("acerca");
            }
            addAction(jmI);
            jmI.setBackground(SystemColor.controlHighlight);
            jm.add(jmI);
            add(jm);
        }
    }
    /**
     * Agrega una acción de teclado al item del menú.
     * @param item item del menú para agregar la acción.
     */
    private void addAction(JMenuItem item) {
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (item.getName().equals("salir")) {
                    System.exit(0);
                }
                if (item.getName().equals("acerca")) {
                    mensaje("Creado por Erick Pineda Corporation Unlimited");
                }
            }
        });
    }
    /**
     * Muestra mensajes por pantalla en un JOptionPane.
     * @param msj mensaje a mostrar.
     */
    private void mensaje(String msj) {
        JOptionPane.showMessageDialog(null, msj, "¡¡¡MENSAJE!!!", JOptionPane.INFORMATION_MESSAGE);
    }
}
