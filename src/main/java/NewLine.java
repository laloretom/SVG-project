import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class NewLine extends JDialog {
    public static final String CLASS_NAME = NewLine.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);
    private Properties webColors;
    private Document doc;

    private JPanel controlsPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField xField1;
    private JLabel x1Label;
    private JTextField yField1;
    private JLabel y1Label;
    private JTextField xField2;
    private JLabel x2Label;
    private JTextField yField2;
    private JLabel y2Label;
    private JComboBox<String> ccombo;
    private JLabel colorLabel;
    private JComboBox<String> wcombo;
    private JLabel widthLabel;

    public NewLine(JFrame parent, Document document) {
        super(parent,"New line",true);

        loadColors();
        String colors[] = webColors.stringPropertyNames().toArray(new String[0]);

        doc = document;

        GridLayout layout = new GridLayout(0,2);

        controlsPanel = new JPanel(layout);

        x1Label = new JLabel("X1:");
        x1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(x1Label);
        xField1=new JTextField(4);
        controlsPanel.add(xField1);

        y1Label = new JLabel("Y1:");
        y1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(y1Label);
        yField1=new JTextField(4);
        controlsPanel.add(yField1);

        x2Label = new JLabel("X2:");
        x2Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(x2Label);
        xField2=new JTextField(4);
        controlsPanel.add(xField2);

        y2Label = new JLabel("Y2:");
        y2Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(y2Label);
        yField2=new JTextField(4);
        controlsPanel.add(yField2);

        colorLabel = new JLabel("Color:");
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(colorLabel);
        ccombo = new JComboBox<>(colors);
        controlsPanel.add(ccombo);

        widthLabel = new JLabel("Width:");
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

        String sx1 = xField1.getText();
        String sy1 = yField1.getText() ;
        String sx2 = xField2.getText();
        String sy2 = yField2.getText();
        String color = (String) ccombo.getSelectedItem();
        String width = (String) wcombo.getSelectedItem();

        Element line = doc.createElement("line");
        line.setAttribute("x1",sx1);
        line.setAttribute("y1",sy1);
        line.setAttribute("x2",sx2);
        line.setAttribute("y2",sy2);
        line.setAttribute("stroke",color);
        line.setAttribute("stroke-width",width);
        Element root = doc.getDocumentElement();
        root.appendChild(line);

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
