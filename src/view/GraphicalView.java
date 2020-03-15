package view;

import controler.Controller;
import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;

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
            JButton b = new JButton(image.toString());

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
    //endregion
}


