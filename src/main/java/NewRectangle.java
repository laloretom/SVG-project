import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class NewRectangle extends JDialog {
    public static final String CLASS_NAME = NewCircle.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private Properties webColors;
    private Document doc;

    private JPanel controlsPanel;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JTextField xField;
    private JLabel xLabel;
    private JTextField yField;
    private JLabel yLabel;
    private JTextField wField;
    private JLabel wLabel;
    private JTextField hField;
    private JLabel hLabel;
    private JComboBox<String> ccombo;
    private JLabel colorLabel;
    private JComboBox<String> fccombo;
    private JLabel colorFillLabel;
    private JComboBox<String> wcombo;
    private JLabel widthLabel;

    public NewRectangle(JFrame parent, Document document) {
        super(parent,"New Rectangle",true);

        loadColors();
        String colors[] = webColors.stringPropertyNames().toArray(new String[0]);

        doc = document;

        GridLayout layout = new GridLayout(0,2);

        controlsPanel = new JPanel(layout);

        xLabel = new JLabel("X:");
        xLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(xLabel);
        xField =new JTextField(4);
        controlsPanel.add(xField);


        yLabel = new JLabel("Y:");
        yLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(yLabel);
        yField =new JTextField(4);
        controlsPanel.add(yField);

        wLabel = new JLabel("Width:");
        wLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(wLabel);
        wField =new JTextField(4);
        controlsPanel.add(wField);

        hLabel = new JLabel("Height:");
        hLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(hLabel);
        hField =new JTextField(4);
        controlsPanel.add(hField);

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
        // add your code here
        String sx = xField.getText();
        String sy = yField.getText() ;
        String sw = wField.getText();
        String sh = hField.getText();
        String color = (String) ccombo.getSelectedItem();
        String fillColor = (String) fccombo.getSelectedItem();
        String width = (String) wcombo.getSelectedItem();


        Element rectangle = doc.createElement("rect");
        rectangle.setAttribute("x",sx);
        rectangle.setAttribute("y",sy);
        rectangle.setAttribute("width",sw);
        rectangle.setAttribute("height",sh);
        rectangle.setAttribute("fill",fillColor);
        rectangle.setAttribute("stroke-width",width);
        rectangle.setAttribute("stroke",color);
        Element root = doc.getDocumentElement();
        root.appendChild(rectangle);

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
