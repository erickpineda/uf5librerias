package net.erickpineda.sumamental;

import java.awt.Container;
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
    private static final String ICO_REGLAS = "/javax/swing/plaf/metal/icons/ocean/iconify-pressed.gif";
    private static final String ICO_PREF = "/com/sun/java/swing/plaf/windows/icons/Computer.gif";
    private static final String[][] BIDI = {
            { "Archivo", "nuevotest;Nuevo Test", "salir;Salir Ctrl + Q" },
            { "Ventana", "preferencias;Preferencias" },
            { "Ayuda", "reglas;Reglas", "acerca;Acerca de" } };
    private TestMental testMental;
    private Container contentPane;
    private URL url;

    /**
     * Create the panel.
     * 
     * @param testMental
     */
    public BarraMenu() {
        setBackground(SystemColor.window);
        crearMenus();
    }

    private void crearMenus() {
        for (int fil = 0; fil < BIDI.length; fil++) {
            JMenu menu = new JMenu(BIDI[fil][0]);
            for (int col = 1; col < BIDI[fil].length; col++) {
                menu.add(creaItem(BIDI[fil][col].split(";")));
                add(menu);
            }
        }
    }

    private JMenuItem creaItem(final String[] arr) {
        JMenuItem item = new JMenuItem(arr[1]);
        item.setName(arr[0]);
        item.setBackground(SystemColor.controlHighlight);
        agregarIconoItem(item);
        addAction(item);
        return item;
    }

    private void agregarIconoItem(final JMenuItem item) {
        if (item.getName().equals("nuevotest")) {
            url = getClass().getResource(ICO_REGLAS);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("salir")) {
            url = getClass().getResource(ICO_SALIR);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("preferencias")) {
            url = getClass().getResource(ICO_PREF);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("reglas")) {
            url = getClass().getResource(ICO_REGLAS);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("acerca")) {
            url = getClass().getResource(ICO_ACERCA);
            item.setIcon(new ImageIcon(url));
        }
    }

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
                if (item.getName().equals("reglas")) {
                    reglas();
                }
                if (item.getName().equals("nuevotest")) {
                    if (testMental == null) {
                        iniciarTestMental();
                    } else {
                        String text = "Se borrará el test actual\nRecuerda leer las reglas en ayuda";
                        int res = mensaje(text);
                        if (res == JOptionPane.OK_OPTION) {
                            contentPane.remove(testMental);
                            testMental = nuevoTest();
                            testMental.iniciarConteo();
                        }
                        if (res == JOptionPane.CLOSED_OPTION) {
                        }
                    }
                    agregarAlContainer();
                }
            }
        });
    }

    private void agregarAlContainer() {
        if (testMental != null) {
            contentPane.add(testMental);
            contentPane.repaint();
        }
    }

    public int mensaje(String msj) {
        return JOptionPane.showConfirmDialog(null, msj, "¡¡¡Mensaje!!!", JOptionPane.PLAIN_MESSAGE);
    }

    private TestMental nuevoTest() {
        TestMental test = new TestMental();
        test.setBackground(SystemColor.textInactiveText);
        return test;
    }

    public TestMental getTest() {
        return testMental;
    }

    public void iniciarTestMental() {
        int res = reglas();
        if (res == JOptionPane.OK_OPTION) {
            testMental = nuevoTest();
            testMental.iniciarConteo();
            agregarAlContainer();
        }
        if (res == JOptionPane.CLOSED_OPTION) {
        }
    }

    public int reglas() {
        String msj = "¿Cuántos segundos tardas en acertar "
                + "las operaciones que aparecen por pantalla?\n\n" + "Son 3 operaciones:\n"
                + "\t- Suma\n\t- Resta\n\t- Multiplicación\n\n"
                + "Si no aciertas mostrará incorrecto"
                + " y no mostrará la siguiente operación\nhasta que sea correcta. "
                + "Aunque mostrara otro operador.";

        int respuesta = JOptionPane.showConfirmDialog(null, msj, "Reglas",
                JOptionPane.PLAIN_MESSAGE);

        return respuesta;
    }

    public void setContainer(Container contentPane) {
        this.contentPane = contentPane;
    }
}
