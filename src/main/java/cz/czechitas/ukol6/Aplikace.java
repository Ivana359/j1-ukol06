package cz.czechitas.ukol6;


import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Aplikace extends JFrame {

    private JLabel husyLabel;
    private JLabel kraliciLabel;
    private JLabel hlavyLabel;
    private JLabel nohyLabel;

    private JTextField husyField;
    private JTextField kraliciField;
    private JTextField hlavyField;
    private JTextField nohyField;

    private JButton vypocitatButton;

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new Aplikace().start();
    }

    public Aplikace() throws HeadlessException {
        super("Farmářka Iva");
        this.init();
    }

    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(Aplikace.class.getResource("czechitas-icon.png")).getImage());
        setLayout(new MigLayout("wrap 2", "[right]rel[50:120:150,grow,fill]"));
        setMinimumSize(new Dimension(250, 200));

        husyField = new JTextField();
        husyField.setHorizontalAlignment(JTextField.TRAILING);
        husyLabel = new JLabel("Husy");
        husyLabel.setDisplayedMnemonic('H');
        husyLabel.setLabelFor(husyField);
        add(husyLabel);
        add(husyField, "span");

        kraliciField = new JTextField();
        kraliciField.setHorizontalAlignment(JTextField.TRAILING);
        kraliciLabel = new JLabel("Králíci");
        kraliciLabel.setDisplayedMnemonic('K');
        kraliciLabel.setLabelFor(kraliciField);
        add(kraliciLabel);
        add(kraliciField, "span");

        add(createButtonBar(), "span");

        pack();

        getRootPane().setDefaultButton(vypocitatButton);

        vypocitatButton.addActionListener(this::handleVypocitat);

        hlavyField = new JTextField();
        hlavyField.setHorizontalAlignment(JTextField.TRAILING);
        hlavyField.setEditable(false);
        hlavyLabel = new JLabel("Počet hlav");
        hlavyLabel.setLabelFor(hlavyField);
        add(hlavyLabel);
        add(hlavyField, "span");

        nohyField = new JTextField();
        nohyField.setHorizontalAlignment(JTextField.TRAILING);
        nohyField.setEditable(false);
        nohyLabel = new JLabel("Počet nohou");
        nohyLabel.setLabelFor(nohyField);
        add(nohyLabel);
        add(nohyField, "span");

    }

    private JPanel createButtonBar() {
        vypocitatButton = new JButton("Vypočítat");
        vypocitatButton.setMnemonic('V');


        JPanel buttonBar = new JPanel(new MigLayout(null, "[right, grow]"));

        buttonBar.add(vypocitatButton);
        return buttonBar;
    }

    private void handleVypocitat(ActionEvent actionEvent) {
        int pocetKraliku;
        try {
            pocetKraliku = (kraliciField.getText() != null && !kraliciField.getText().isBlank())
                    ? Integer.parseInt(kraliciField.getText()) : 0;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Neplatná hodnota v poli Počet králíků.", "Chyba", JOptionPane.ERROR_MESSAGE);
            pocetKraliku = 0;
        }
        int pocetHus;
        try {
            pocetHus = (husyField.getText() != null && !husyField.getText().isBlank()) ? Integer.parseInt(husyField.getText()) : 0;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Neplatná hodnota v poli Počet hus.", "Chyba", JOptionPane.ERROR_MESSAGE);
            pocetHus = 0;
        }
        int pocetNoh = pocetKraliku * 4 + pocetHus * 2;
        nohyField.setText(String.valueOf(pocetNoh));

        int pocetHlav = pocetKraliku + pocetHus;
        hlavyField.setText(String.valueOf(pocetHlav));

    }
}
