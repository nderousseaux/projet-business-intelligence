import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import weka.core.*;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import org.w3c.dom.Attr;
import weka.classifiers.bayes.NaiveBayes;

import weka.classifiers.evaluation.EvaluationUtils;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.Instance;
import weka.gui.beans.Classifier;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class Main{

    private static Random rd = new Random();
    private static int nbImages = 20;
    private static JFrame f;

    public static void main(String[] args){

        //On génére le set d'image
        ArrayList<Image> images = generateImages(nbImages);

        System.out.println(images.size());

        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setSize(800, 1000);

        GridLayout grid = new GridLayout(4, 5, 10, 10);
        f.setLayout(grid);

        for(Image image:images){
            JButton b = new JButton(image.toString());
            b.setBackground(Color.RED);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if(b.getBackground() == Color.RED){
                        b.setBackground(Color.GREEN);
                    }
                    else{
                        b.setBackground(Color.RED);
                    }
                    image._select = !image._select;

                    System.out.println(image._select);
                }
            });
            f.add(b);
        }

        JButton b = new JButton("Validate");
            b.setBackground(Color.RED);
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    calculWeka(images);
                }
            });
            f.add(b);


        f.pack();
        f.setVisible(true);

        

        
    }

    public static void calculWeka(ArrayList<Image> images) {

        FastVector fvCadeaux = new FastVector(2);
        fvCadeaux.addElement("t");
        fvCadeaux.addElement("f");
        Attribute attCadeaux = new Attribute("Cadeaux : ", fvCadeaux);

        FastVector fvEtoile = new FastVector(2);
        fvEtoile.addElement("t");
        fvEtoile.addElement("f");
        Attribute attEtoile = new Attribute("Etoile : ", fvEtoile);

        FastVector fvGuirlandes = new FastVector(2);
        fvGuirlandes.addElement("t");
        fvGuirlandes.addElement("f");
        Attribute attGuirlandes = new Attribute("Guirlandes : ", fvGuirlandes);

        FastVector fvNeige = new FastVector(2);
        fvNeige.addElement("t");
        fvNeige.addElement("f");
        Attribute attNeige = new Attribute("Neige : ", fvNeige);

        FastVector fvBoules = new FastVector(2);
        fvBoules.addElement("t");
        fvBoules.addElement("f");
        Attribute attBoules = new Attribute("Boules : ", fvBoules);

        //On définit la classe
        FastVector fvClasse = new FastVector(2);
        fvClasse.addElement("t");
        fvClasse.addElement("f");
        Attribute classe = new Attribute("Decision", fvClasse);

        FastVector fvAttributs = new FastVector(6);
        fvAttributs.addElement(attCadeaux);
        fvAttributs.addElement(attEtoile);
        fvAttributs.addElement(attGuirlandes);
        fvAttributs.addElement(attNeige);
        fvAttributs.addElement(attBoules);
        fvAttributs.addElement(classe);


        Instances isTrainingSet = new Instances("Relation", fvAttributs, images.size());
        isTrainingSet.setClass(classe);

        for (Image image : images) {
            Instance instance = new Instance(6);
            System.out.println(image._cadeaux ? "t" : "f");
            instance.setValue(attCadeaux, image._cadeaux ? "t" : "f");
            instance.setValue(attEtoile, image._etoile ? "t" : "f");
            instance.setValue(attGuirlandes, image._guirlandes ? "t" : "f");
            instance.setValue(attNeige, image._neige ? "t" : "f");
            instance.setValue(attBoules, image._boules ? "t" : "f");
            instance.setValue(classe, image._select ? "t" : "f");
            isTrainingSet.add(instance);
        }



        try {
            J48 tree = new J48();
            String graph = "";
            //tree.setOptions(options);
            tree.buildClassifier(isTrainingSet);
            graph = tree.graph();
            TreeVisualizer tv = new TreeVisualizer(null, graph, new PlaceNode2());
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(800, 1000);
            frame.getContentPane().add(tv, BorderLayout.CENTER);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        public static ArrayList<Image> generateImages(int nombreDImage){
        ArrayList<Image> retour = new ArrayList<Image>();

        ArrayList<Integer> nombres = new ArrayList<Integer>();
        for(int i = 0; i < Math.pow(2,Image._nbAttributs); i++){
            nombres.add(i);
        }
        for(int i = 0; i<nombreDImage; i++){
            Integer nombre = nombres.get(rd.nextInt(nombres.size()));
            nombres.remove(nombre);
            retour.add(new Image(nombre/16%2 == 1, nombre/8%2 == 1, nombre/4%2 == 1, nombre/2%2 == 1, nombre%2 == 1));
        }

        



        return retour;
    }



}