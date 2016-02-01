package net.erickpineda.calculadora;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

public class Calculadora extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField pantalla;
    private JLabel operador;
    private double resultado;
    private String rutaImg;
    private String operacion;
    private boolean nuevaOperacion = true;
    private static final String[][] BOTONES_CELDAS = {
            { "img;cell 3 0 1 2,grow" },
            { "1;cell 0 2,grow", "2;cell 1 2,grow", "3;cell 2 2,grow", "-;cell 3 2,grow" },
            { "4;cell 0 3,grow", "5;cell 1 3,grow", "6;cell 2 3,grow", "+;cell 3 3,grow" },
            { "7;cell 0 4,grow", "8;cell 1 4,grow", "9;cell 2 4,grow", "DEL;cell 3 4,grow" },
            { ".;cell 0 5,grow", "0;cell 1 5,grow", "=;cell 2 5,grow", "C;cell 3 5,grow" } };
    /**
     * Create the panel.
     */
    public Calculadora() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                //pantalla.setText(getSize(getSize()).toString());
                
            }
        });
        setLayout(new MigLayout("", "[106px,grow][106px,grow][106px,grow][106px,grow]", "[36px,grow][36px][36px][36px][36px][36px]"));
        setBackground(SystemColor.controlHighlight);
        setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Calculadora", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        
        crearPantalla();
        crearBotones();
    }
    
    private void crearPantalla() {
        operador = new JLabel();
        operador.setHorizontalTextPosition(SwingConstants.RIGHT);
        operador.setHorizontalAlignment(SwingConstants.RIGHT);
        operador.setFont(new Font("Yu Gothic Light", Font.BOLD, 11));
        operador.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        operador.setForeground(SystemColor.controlShadow);
        operador.setBackground(SystemColor.control);
        add(operador, "flowy,cell 2 1,grow");

        pantalla = new JTextField();
        pantalla.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        pantalla.setText("0");
        pantalla.setForeground(SystemColor.controlLtHighlight);
        pantalla.setBackground(SystemColor.infoText);
        pantalla.setEditable(false);
        add(pantalla, "cell 0 0 3 2,grow");
    }
    
    private void crearBotones() {
        for (int i = 0; i < BOTONES_CELDAS.length; i++) {
            for (int j = 0; j < BOTONES_CELDAS[i].length; j++) {
                creaBoton(BOTONES_CELDAS[i][j].split(";"));
            }
        }
    }
    
    private void creaBoton(String[] nombrebotonYCelda) {
        JButton btn = new JButton(nombrebotonYCelda[0]);
        btn.setBackground(SystemColor.controlHighlight);
        if (esBotonImagen(btn) && btn.getIcon() != null) {
            cambiarImagenBoton(btn);
        } if (esBotonImagen(btn) && btn.getIcon() == null) {
            btn.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Imagen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        }
        addActions(btn);
        add(btn, nombrebotonYCelda[1]);
    }
    
    private boolean esBotonImagen(JButton btn) {
        return (btn.getText().equals("img") || btn.getText().equals(""));
    }

    private void addActions(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (btn.getText().equals(".")) {
                    btn.setEnabled(false);
                }
                if ((esBotonImagen(btn) && btn.getIcon() != null) || (esBotonImagen(btn) && btn.getIcon() == null)) {
                    abrirArchivo(btn);
                } else {
                    if (esNumero(btn.getText())) {
                        clicNumero(btn.getText());
                    } else {
                        clicOperacion(btn.getText());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (esBotonImagen(btn)){
                    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                btn.setBackground(SystemColor.controlShadow);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (esBotonImagen(btn) && btn.getIcon() != null) {
                    btn.setText("");
                }
                btn.setBackground(SystemColor.controlHighlight);
            }
        });
    }

    private void clicNumero(String text) {
        if (pantalla.getText().equals("0") || nuevaOperacion) {
            pantalla.setText(text);
        } else {
            pantalla.setText(pantalla.getText() + text);
        }
        nuevaOperacion = false;
    }

    private void clicOperacion(String btn) {
        if (btn.equals("+") || btn.equals("-")) {
            operador.setText(btn + " ");
        }
        if (btn.equals("=")) {
            if (operacion != null) {
                calculaResultado();
            }
        }
        if (btn.equals("C")) {
            resultado = 0;
            pantalla.setText("0");
            nuevaOperacion = true;
        }
        if (btn.equals("DEL")) {
            int pos = pantalla.getText().length() - 1;
            String borrar = pantalla.getText().substring(0, pos);

            if (!pantalla.getText().isEmpty() && !pantalla.getText().equals("0")) {
                pantalla.setText(borrar);
            }
            if (pantalla.getText().length() == 0) {
                pantalla.setText("0");
            }

        } else {
            operacion = btn;
            if ((resultado > 0) && !nuevaOperacion) {
                calculaResultado();
            } else {
                resultado = new Double(pantalla.getText());
            }
        }
        nuevaOperacion = true;
    }

    private void calculaResultado() {
        if (operacion.equals("+")) {
            resultado += new Double(pantalla.getText());
        }
        if (operacion.equals("-")) {
            resultado -= new Double(pantalla.getText());
        }

        pantalla.setText("" + resultado);
        operacion = "";
    }
    
    private void cambiarImagenBoton(JButton btn) {
        ImageIcon ii = createImageIcon("/mantis.png", "imagen perfil");
        btn.setIcon(new ImageIcon(getScaledImage(ii.getImage(), 106, 72)));
    }

    public boolean esNumero(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
    
    private void abrirArchivo(JButton btn) {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Imagenes *.png", "png"));
        int returnVal = fc.showOpenDialog(Calculadora.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getName().endsWith(".png")) {
                rutaImg = fc.getSelectedFile().getAbsolutePath();

                if (rutaImg.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "\nNo se ha encontrado la imagen seleccionada", "¡¡¡ADVERTENCIA!!!",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    cambiarImagenBoton(btn);
                }
            }
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
        } else {
        }
    }

    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Fichero no encontrado: " + path);
            return null;
        }
    }
    /**
     * Método que redimensiona una imagen, según los parámetros de anchura y altura.
     * @param srcImg ruta de la imagen.
     * @param w anchura de la imagen.
     * @param h altura de la imagen.
     * @return retorna una imagen escalada según la anchura y altura especificada.
     */
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
