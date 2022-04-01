import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SVGApplication extends javax.swing.JFrame {

    public static final String CLASS_NAME = SVGApplication.class.getSimpleName();
    private static final Logger LOG = Logger.getLogger(CLASS_NAME);

    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu operMenu;
    private javax.swing.JMenuItem scaleMenuItem;
    private javax.swing.JMenuItem rotateMenuItem;

    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;

    private javax.swing.JMenu shapeMenu;
    private javax.swing.JMenuItem lineMenuItem;
    private javax.swing.JMenuItem circleMenuItem;
    private javax.swing.JMenuItem rectMenuItem;


    public SVGApplication() {
        initComponents();
        this.setSize(800, 600);
    }

    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();

        menuBar = new javax.swing.JMenuBar();

        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();

        shapeMenu = new javax.swing.JMenu();
        lineMenuItem = new javax.swing.JMenuItem();
        circleMenuItem = new javax.swing.JMenuItem();
        rectMenuItem = new javax.swing.JMenuItem();

        scaleMenuItem = new javax.swing.JMenuItem();
        rotateMenuItem = new javax.swing.JMenuItem();
        operMenu = new javax.swing.JMenu();

        operMenu = new javax.swing.JMenu();

        menuBar.add(operMenu);

        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //  File menu ---
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        newMenuItem.setMnemonic('n');
        newMenuItem.setText("New File");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        //  --- End file menu

        //  Edit menu ---
        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);
        //  --- End edit menu

        //  Shape menu ---
        shapeMenu.setText("Shape");
        lineMenuItem.setText("Line");
        lineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineMenuItemMenuItemActionPerformed(evt);
            }
        });
        shapeMenu.add(lineMenuItem);

        circleMenuItem.setText("Circle");
        circleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circleMenuItemMenuItemActionPerformed(evt);
            }
        });
        shapeMenu.add(circleMenuItem);

        rectMenuItem.setText("Rectangle");
        rectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectMenuItemMenuItemActionPerformed(evt);
            }
        });
        shapeMenu.add(rectMenuItem);

        menuBar.add(shapeMenu);
        //  --- End shape menu

        //  Operations menu ---
        operMenu.setText("Operations");
        scaleMenuItem.setText("Scale");
        operMenu.add(scaleMenuItem);
        scaleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleMenuItemMenuItemActionPerformed(evt);
            }
        });

        rotateMenuItem.setText("Rotate");
        operMenu.add(rotateMenuItem);
        rotateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateMenuItemMenuItemActionPerformed(evt);
            }
        });

        menuBar.add(operMenu);
        //  --- End operations menu

        //  Help menu ---
        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);
        //  --- End help menu


        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }


    private void newMenuItemActionPerformed(ActionEvent evt)  {
        String sw = JOptionPane.showInputDialog("Width: ");
        String sh = JOptionPane.showInputDialog("Height: ");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            doc = builder.newDocument();
            Element root = doc.createElement("svg");

            doc.appendChild(root);
            root.setAttribute("width", sw);
            root.setAttribute("height", sh);
            root.setAttribute("xmlns", "http://www.w3.org/2000/svg");;
            doc.getDocumentElement().normalize();

        } catch (ParserConfigurationException e) {
            LOG.severe(e.getMessage());
            doc = null;
        }

            DocumentFrame intFrame = new DocumentFrame("New SVG", doc);
            desktopPane.add(intFrame);
            intFrame.setVisible(true);


    }

    private void lineMenuItemMenuItemActionPerformed(ActionEvent evt) {
        DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

        if( documentFrame == null ) {
            return;
        }

        Document doc = documentFrame.getDocument();

        NewLine dialog = new NewLine(SVGApplication.this, doc);

        dialog.setVisible(true);

        documentFrame.repaint();
    }

    private void circleMenuItemMenuItemActionPerformed(ActionEvent evt) {
        DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

        if( documentFrame == null ) {
            return;
        }

        Document doc = documentFrame.getDocument();

        NewCircle dialog = new NewCircle(SVGApplication.this, doc);

        dialog.setVisible(true);

        documentFrame.repaint();
    }

    private void rectMenuItemMenuItemActionPerformed(ActionEvent evt) {
        DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

        if( documentFrame == null ) {
            return;
        }

        Document doc = documentFrame.getDocument();

        NewRectangle dialog = new NewRectangle(SVGApplication.this, doc);

        dialog.setVisible(true);

        documentFrame.repaint();
    }

    private void saveMenuItemActionPerformed(ActionEvent evt) {
        DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

        if( documentFrame == null ) {
            return;
        }

        Document document = documentFrame.getDocument();

        //String name = desktopPane.getSelectedFrame().getTitle();

        if (document.getDocumentURI() == null){
            final JFileChooser fc = new JFileChooser();

            String userDir = System.getProperty("user.dir");

            fc.setCurrentDirectory(new File(userDir));

            // Abrir dialogo para seleccion de archivo
            fc.setDialogTitle("Seleccionar Imagen SVG");
            fc.setAcceptAllFileFilterUsed(false);

            // Mostrar unicamente archivos SVG
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SVG", "svg");
            fc.addChoosableFileFilter(filter);

            int returnVal = fc.showSaveDialog(this);

            if( returnVal == JFileChooser.APPROVE_OPTION ) {
                // Obtener archivo seleccionado
                File newFile = fc.getSelectedFile();
                if (newFile == null) {
                    return;
                }
                if (!newFile.getName().toLowerCase().endsWith(".svg")) {
                    newFile = new File(newFile.getParentFile(), newFile.getName() + ".svg");
                }

                if( newFile.exists() ) {
                    LOG.info("El archivo ya existe.");

                    int input = JOptionPane.showConfirmDialog(
                            this,
                            "El archivo:\n"
                                    + newFile.getName()
                                    + "\nRemplazar el archivo?",
                            "Archivo ya existe",
                            JOptionPane.YES_NO_OPTION);
                    if( input != JOptionPane.YES_OPTION ) {
                        return;
                    }
                }

                Util.saveDocument(document, newFile.toString());
                LOG.info( newFile.toString() );

                desktopPane.getSelectedFrame().dispose();

                // Con el archivo seleccionado, crear un documento DOM
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                try {
                    dBuilder = dbFactory.newDocumentBuilder();
                } catch (ParserConfigurationException ex) {
                    LOG.severe(ex.getMessage());
                }

                Document doc = null;

                try {
                    doc = dBuilder.parse(newFile);
                    doc.getDocumentElement().normalize();

                } catch (SAXException ex) {
                    LOG.severe(ex.getMessage());
                } catch (IOException ex) {
                    LOG.severe(ex.getMessage());
                }

                DocumentFrame intFrame = new DocumentFrame(newFile.getName(),doc);
                desktopPane.add(intFrame);
                intFrame.setVisible(true);

            }
        }else{
            File file = new File(document.getDocumentURI().replaceAll("file:/",""));
            if(file.exists()){
                LOG.info("El archivo ya existe.");
                int input = JOptionPane.showConfirmDialog(
                        this,
                        "El archivo:\n"
                                + file.getName()
                                + "\nRemplazar el archivo?",
                        "Archivo ya existe",
                        JOptionPane.YES_NO_OPTION);
                if (input != JOptionPane.YES_OPTION) {
                    return;
                }
                Util.saveDocument(document, file.toString());
            }
        }



    }

    private void saveAsMenuItemActionPerformed(ActionEvent evt) {

        DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

        if( documentFrame == null ) {
            return;
        }

        final JFileChooser fc = new JFileChooser();

        String userDir = System.getProperty("user.dir");

        fc.setCurrentDirectory(new File(userDir));

        // Abrir dialogo para seleccion de archivo
        fc.setDialogTitle("Seleccionar Imagen SVG");
        fc.setAcceptAllFileFilterUsed(false);

        // Mostrar unicamente archivos SVG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SVG", "svg");
        fc.addChoosableFileFilter(filter);

        int returnVal = fc.showSaveDialog(this);

        if( returnVal == JFileChooser.APPROVE_OPTION ) {
            // Obtener archivo seleccionado
            File file = fc.getSelectedFile();
            if (file == null) {
                return;
            }
            if (!file.getName().toLowerCase().endsWith(".svg")) {
                file = new File(file.getParentFile(), file.getName() + ".svg");
            }

            if( file.exists() ) {
                LOG.info("El archivo ya existe.");

                int input = JOptionPane.showConfirmDialog(
                        this,
                        "El archivo:\n"
                                + file.getName()
                                + "\nRemplazar el archivo?",
                        "Archivo ya existe",
                        JOptionPane.YES_NO_OPTION);
                if( input != JOptionPane.YES_OPTION ) {
                    return;
                }
            }

            Document document = documentFrame.getDocument();
            Util.saveDocument(document, file.toString());
            LOG.info( file.toString() );

        }
    }

    private void rotateMenuItemMenuItemActionPerformed(ActionEvent evt) {

        Object[] options = {"15", "30", "45", "60","75", "90"};

        String s = (String) JOptionPane.showInputDialog(
                this,
                "Angulo de rotación:",
                "Rotar",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "15");
        if ((s != null) && (s.length() > 0)) {

            double angulo = Double.parseDouble(s);
            DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

            if( documentFrame != null ) {
                System.out.println( documentFrame.getTitle() );

                Util.rotateSVG(documentFrame.getDocument(), angulo);
                documentFrame.repaint();
            }

        }
    }

    private void scaleMenuItemMenuItemActionPerformed(ActionEvent evt) {

        Object[] options = {"25%", "50%", "75%", "100%"};

        String s = (String) JOptionPane.showInputDialog(
                this,
                "Porcentaje a escalar:",
                "Scale",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "25%");

        if ((s != null) && (s.length() > 0)) {
            double scaleFactor = 1.0;
            if (s.equals("25%")) {
                scaleFactor = 1.25;
            }
            if (s.equals("50%")) {
                scaleFactor = 1.50;
            }
            if (s.equals("75%")) {
                scaleFactor = 1.75;
            }
            if (s.equals("100%")) {
                scaleFactor = 2.0;
            }

            DocumentFrame documentFrame =  (DocumentFrame) desktopPane.getSelectedFrame();

           if( documentFrame != null ) {
               System.out.println( documentFrame.getTitle() );

               Util.scaleSVG(documentFrame.getDocument(), scaleFactor);

               Dimension d = documentFrame.getPreferredSize();
               d.height = (int) (d.height * scaleFactor);
               d.width  = (int) (d.width * scaleFactor);

               documentFrame.setSize( d );
               documentFrame.repaint();
           }

        }
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        final JFileChooser fc = new JFileChooser();

        String userDir = System.getProperty("user.dir");

        fc.setCurrentDirectory(new File(userDir));

        // Abrir dialogo para seleccion de archivo
        fc.setDialogTitle("Seleccionar Imagen SVG");
        fc.setAcceptAllFileFilterUsed(false);

        // Mostrar unicamente archivos SVG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SVG", "svg");
        fc.addChoosableFileFilter(filter);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // Obtener archivo seleccionado
            File file = fc.getSelectedFile();
            System.out.println(file);
            //This is where a real application would open the file.

            // Con el archivo seleccionado, crear un documento DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                LOG.severe(ex.getMessage());
            }

            Document doc = null;

            try {
                doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();

            } catch (SAXException ex) {
                LOG.severe(ex.getMessage());
            } catch (IOException ex) {
                LOG.severe(ex.getMessage());
            }


            DocumentFrame intFrame = new DocumentFrame(file.getName(),doc);

            desktopPane.add(intFrame);

            intFrame.setVisible(true);

        } else {

        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            LOG.severe(ex.getMessage());
        } catch (InstantiationException ex) {
            LOG.severe(ex.getMessage());
        } catch (IllegalAccessException ex) {
            LOG.severe(ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            LOG.severe(ex.getMessage());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SVGApplication().setVisible(true);
            }
        });
    }
}
