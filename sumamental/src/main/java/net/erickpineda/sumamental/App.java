package net.erickpineda.sumamental;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.plaf.LayerUI;

public class App extends JFrame {

    /**
     * 
     */
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
        setBounds(100, 100, 450, 320);

        TestSuma test = new TestSuma();
        getContentPane().add(test, BorderLayout.CENTER);

        BarraMenu menuBar = new BarraMenu();
        setJMenuBar(menuBar);
        teclado();
        
        /*
        Dimension labelSize = new Dimension(80, 20);
        // A single LayerUI for all the fields.
        LayerUI<JFormattedTextField> layerUI = new ValidationLayerUI();
        // Number field.
        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberLabel.setPreferredSize(labelSize);

        NumberFormat numberFormat = NumberFormat.getInstance();
        JFormattedTextField numberField = new JFormattedTextField(numberFormat);
        numberField.setColumns(16);
        numberField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        numberField.setValue(42);

        JPanel numberPanel = new JPanel();
        numberPanel.add(numberLabel);
        numberPanel.add(new JLayer<JFormattedTextField>(numberField, layerUI));
        getContentPane().add(numberPanel, BorderLayout.SOUTH);*/
    }
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
