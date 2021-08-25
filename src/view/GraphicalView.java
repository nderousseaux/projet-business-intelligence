package view;

import controler.Controller;
import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * La classe GraphicalView s'occupe de l'interface graphique de l'application.
 * Possède le controller en attribut pour pouvoir l'utiliser
 */
public class GraphicalView {

    //region Attributs
    private Controller _controller;
    private JFrame f;
    //endregion

    //region Constructeur
    /**
     * Constructeur de graphical view, lance l'interface de séléction des images.
     */
    public GraphicalView(Controller controller) {
        _controller = controller;

        //On génére l'interface graphique.
        this.init();
    }
    //endregion

    //region methodes d'instances
    /**
     * Methode qui génére l'interface graphique.
     */
    private void init() {

        //Configuration de la fenêtre.
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 1000);

        Container contenu = f.getContentPane();
        BorderLayout placeur = new BorderLayout();
        contenu.setLayout(placeur);

        JPanel pan = new JPanel();
        GridLayout maGrille = new GridLayout(4, 5);
        pan.setLayout(maGrille);
        contenu.add(pan, BorderLayout.CENTER);


        //Ajout d'un bouton pour chaque image.
        for (model.Image image : _controller.getImageList()) {

            //On crée le boutton
            BufferedImage imageBuffered = genDisplayImage(image);
            ImageIcon icon = new ImageIcon(imageBuffered);

            //On redimensionne
            ImageIcon iconRedi = new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

            JButton b = new JButton(iconRedi);


            //Il est rouge par défaut.
            b.setBackground(Color.RED);

            //Si il est cliqué, la couleur change et l'état isSelect de l'image correspondante change.
            b.addActionListener(e -> {
                if (b.getBackground() == Color.RED) {
                    b.setBackground(Color.GREEN);
                } else {
                    b.setBackground(Color.RED);
                }

                image.setSelect(!image.isSelect());
            });
            //On ajoute le bouton
            pan.add(b);
        }

        //On ajoute le bouton Valider
        JButton b = new JButton("Valider");
        b.addActionListener(e -> {
            //On ferme la fenêtre et on lance le calcul
            f.dispose();
            afficheArbre(_controller.weka());
        });
        f.add(b, BorderLayout.SOUTH);

        //On afficje le tout.
        f.pack();
        f.setVisible(true);
    }

    /**
     * Méthode qui affiche le graph
     *
     * @param model
     */
    private void afficheArbre(J48 model) {
        try {
            TreeVisualizer tv = new TreeVisualizer(null, model.graph(), new PlaceNode2());
            //On crée la fenêtre
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(800, 1000);
            frame.getContentPane().add(tv, BorderLayout.CENTER);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour générer une images
     * @param image
     * @return
     */
    private BufferedImage genDisplayImage(model.Image image) {
        BufferedImage result = new BufferedImage(
                638, 720, //Size of the image
                BufferedImage.TYPE_INT_ARGB);


        Graphics g = result.getGraphics();

        
        BufferedImage sapin = null;
        try {
            sapin = ImageIO.read(new File("src/view/img/base.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.drawImage(sapin, 0, 0, null);

        //Si il a des boules
        if(image.isNeige()){
            BufferedImage neige = null;
            try {
                neige = ImageIO.read(new File("src/view/img/neige.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.drawImage(neige, 0, 0, null);
        }

        if(image.isGuirlandes()){
            BufferedImage guirlandes = null;
            try {
                guirlandes = ImageIO.read(new File("src/view/img/guirlandes.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.drawImage(guirlandes, 0, 0, null);
        }

        if(image.isBoules()){
            BufferedImage boules = null;
            try {
                boules = ImageIO.read(new File("src/view/img/boules.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.drawImage(boules, 0, 0, null);
        }

        if(image.isCadeaux()){
            BufferedImage cadeaux = null;
            try {
                cadeaux = ImageIO.read(new File("src/view/img/cadeaux.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.drawImage(cadeaux, 0, 0, null);
        }

        if(image.isEtoile()){
            BufferedImage etoile = null;
            try {
                etoile = ImageIO.read(new File("src/view/img/etoile.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            g.drawImage(etoile, 0, 0, null);
        }






        try {
            ImageIO.write(result,"png",new File("base.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    //endregion
}


