import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class NewCircle extends JDialog {
    public static final String CLASS_NAME = NewCircle.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private Properties webColors;
    private Document doc;

    private JPanel controlsPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField cxField;
    private JLabel cxLabel;
    private JTextField cyField;
    private JLabel cyLabel;
    private JTextField rField;
    private JLabel rLabel;
    private JComboBox<String> ccombo;
    private JLabel colorLabel;
    private JComboBox<String> fccombo;
    private JLabel colorFillLabel;
    private JComboBox<String> wcombo;
    private JLabel widthLabel;

    public NewCircle(JFrame parent, Document document) {
        super(parent,"New circle",true);

        loadColors();
        String colors[] = webColors.stringPropertyNames().toArray(new String[0]);

        doc = document;

        GridLayout layout = new GridLayout(0,2);

        controlsPanel = new JPanel(layout);

        cxLabel = new JLabel("X:");
        cxLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(cxLabel);
        cxField =new JTextField(4);
        controlsPanel.add(cxField);

        cyLabel = new JLabel("Y:");
        cyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(cyLabel);
        cyField =new JTextField(4);
        controlsPanel.add(cyField);

        rLabel = new JLabel("R:");
        rLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(rLabel);
        rField =new JTextField(4);
        controlsPanel.add(rField);

        colorLabel = new JLabel("stroke Color:");
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(colorLabel);
        ccombo = new JComboBox<>(colors);
        controlsPanel.add(ccombo);

        colorFillLabel = new JLabel("Fill Color:");
        colorFillLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(colorFillLabel);
        fccombo = new JComboBox<>(colors);
        controlsPanel.add(fccombo);

        widthLabel = new JLabel("Line Width:");
        widthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(widthLabel);
        String ws[] = {"1", "2", "3", "4", "5", "6","7","8"};
        wcombo = new JComboBox<>(ws);
        controlsPanel.add(wcombo);

        buttonOK = new JButton("Ok");
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        controlsPanel.add(buttonOK);

        buttonCancel= new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        controlsPanel.add(buttonCancel);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        this.setContentPane(controlsPanel);

        setSize(200,400);
        setResizable(false);
        pack();

    }

    private void onOK() {
        String scx = cxField.getText();
        String scy = cyField.getText() ;
        String sr = rField.getText();
        String color = (String) ccombo.getSelectedItem();
        String fillColor = (String) fccombo.getSelectedItem();
        String width = (String) wcombo.getSelectedItem();

        Element circle = doc.createElement("circle");
        circle.setAttribute("cx",scx);
        circle.setAttribute("cy",scy);
        circle.setAttribute("r",sr);
        circle.setAttribute("fill",fillColor);
        circle.setAttribute("stroke-width",width);
        circle.setAttribute("stroke",color);
        Element root = doc.getDocumentElement();
        root.appendChild(circle);

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void loadColors() {
        try {

            String userDir = System.getProperty("user.dir");
            FileReader reader = new FileReader(userDir + "/colors.properties");

            webColors = new Properties();
            webColors.load(reader);

        } catch (FileNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }
    }
}
