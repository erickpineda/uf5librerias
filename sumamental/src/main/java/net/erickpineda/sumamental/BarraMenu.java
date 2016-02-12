package net.erickpineda.sumamental;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

public class BarraMenu extends JMenuBar {
    private static final long serialVersionUID = 1L;
    private static final String ICO_SALIR = "/16x16-icons/gif/16x16/Exit.gif";
    private static final String ICO_NUEVO = "/16x16-icons/gif/16x16/Create.gif";
    private static final String ICO_ACERCA = "/16x16-icons/gif/16x16/Help book.gif";
    private static final String ICO_REGLAS = "/16x16-icons/gif/16x16/Text.gif";
    private static final String ICO_PREF = "/16x16-icons/gif/16x16/Display 16x16.gif";
    private static final String ICO_HIST = "/16x16-icons/gif/16x16/Report.gif";
    private TestMental testMental;
    private Container contentPane;
    private URL url;
    /**
     * Array bidimensional con la información de cada menú y submenu.
     */
    private static final String[][] BIDI = {
            { "Archivo", "nuevotest;Nuevo Test", "salir;Salir Ctrl + Q" },
            { "Ver", "historial;Historial" },
            { "Ventana", "preferencias;Preferencias" },
            { "Ayuda", "reglas;Reglas", "acerca;Acerca de" } };

    /**
     * Create the panel.
     */
    public BarraMenu(Container cont) {
        setContainer(cont);
        setBackground(SystemColor.window);
        crearMenus();
        teclado();
        panelSur();
    }
    /**
     * Recorre un array bidimensional para crear los diferentes menus.
     */
    private void crearMenus() {
        for (int fil = 0; fil < BIDI.length; fil++) {
            JMenu menu = new JMenu(BIDI[fil][0]);
            for (int col = 1; col < BIDI[fil].length; col++) {
                menu.add(creaItem(BIDI[fil][col].split(";")));
            }
            add(menu);
        }
    }
    /**
     * 
     * @param arr array que tiene la información del submenu.
     * @return retorna un JMenuItem para agregarlo al menu.
     */
    private JMenuItem creaItem(final String[] arr) {
        JMenuItem item = new JMenuItem(arr[1]);
        item.setName(arr[0]);
        item.setBackground(SystemColor.controlHighlight);
        agregarIconoItem(item);
        addAction(item);
        return item;
    }
    /**
     * Agregar un icono a cada submenu.
     * @param item {@code JMenuItem} que se le agregará el icono.
     */
    private void agregarIconoItem(final JMenuItem item) {
        if (item.getName().equals("nuevotest")) {
            url = getClass().getResource(ICO_NUEVO);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("salir")) {
            url = getClass().getResource(ICO_SALIR);
            item.setIcon(new ImageIcon(url));
        }
        if (item.getName().equals("historial")) {
            url = getClass().getResource(ICO_HIST);
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
    /**
     * Método que agregará una acción de mouse a un submenu.
     * @param item {@code JMenuItem} que se le agregará la acción.
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
                if (item.getName().equals("reglas")) {
                    reglas();
                }
                if (item.getName().equals("historial")) {
                    mensaje("Sin implementar");
                }
                if (item.getName().equals("preferencias")) {
                    mensaje("Sin implementar");
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
    /**
     * Agrega al frame principal el panel del test mental.
     */
    private void agregarAlContainer() {
        if (testMental != null) {
            contentPane.add(testMental);
            contentPane.repaint();
        }
    }
    /**
     * 
     * @param msj mensaje que llevará el cuerpo del panel a mostrar.
     * @return retorna un número que será la opción escogida, ya sea OK, CERRAR etc...
     */
    public int mensaje(String msj) {
        return JOptionPane.showConfirmDialog(null, msj, "¡¡¡Mensaje!!!", JOptionPane.PLAIN_MESSAGE);
    }
    /**
     * 
     * @return retorna un nuevo test mental.
     */
    private TestMental nuevoTest() {
        return new TestMental();
    }
    /**
     * 
     * @return retorna el test mental que esta instanciado actualmente.
     */
    public TestMental getTest() {
        return testMental;
    }
    /**
     * Inicia el conteo del test mental.
     */
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
    /**
     * Reglas del test.
     */
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

    /**
     * 
     * @param contentPane
     *            container del frame.
     */
    public void setContainer(Container contentPane) {
        this.contentPane = contentPane;
    }

    /**
     * Panel de información.
     */
    private void panelSur() {
        JPanel panel = new JPanel();
        panel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
        panel.setBackground(SystemColor.text);
        contentPane.add(panel, BorderLayout.SOUTH);
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
        actionMap.put("action_reglas", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                reglas();
            }
        });
        InputMap keyMap = new ComponentInputMap(p);
        keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK),
                "action_exit");
        keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.Event.CTRL_MASK),
                "action_reglas");

        SwingUtilities.replaceUIActionMap(p, actionMap);
        SwingUtilities.replaceUIInputMap(p, JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);
        contentPane.add(p);
    }
}