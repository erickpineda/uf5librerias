package net.erickpineda.sumamental;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Un JTextField que solo acepta numeros enteros.
 */
public class IntegerField extends JTextField {

    private static final long serialVersionUID = 1L;

    public IntegerField() {
        super();
    }

    public IntegerField(int cols) {
        super(cols);
    }

    @Override
    protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }

    static class UpperCaseDocument extends PlainDocument {
        private static final long serialVersionUID = 1L;

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            if (str.startsWith("-") || str.matches("\\d+")) {
                super.insertString(offs, str, a);
            }
        }
    }
}