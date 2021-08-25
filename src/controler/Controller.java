package controler;

import model.Image;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe controller. Elle manipule le model.
 * Elle possède l'attribut ArrayList _imageList, qui recense toutes les images.
 *
 */
public class Controller {

    //region Attributs
    private int _nbImageGenerate = 20;
    private ArrayList<Image> _imageList = new ArrayList<>();

    private Instances _trainingSet;
    private J48 _model = null;

    private static Random rd = new Random();
    //endregion

    //region Constructeurs
    /**
     * Constructeur de la classe controller.
     * Elle lance la génération aléatoire d'image, et l'enregistre dans l'attribut _imageList.
     * Nombre d'images par défaut : 20
     */
    public Controller(){
        //On lance la génération d'images
        this.initImages(_nbImageGenerate);
    }

    /**
     * Constructeur de la classe controller.
     * Elle lance la génération aléatoire d'image, et l'enregistre dans l'attribut _imageList.
     * Nombre d'images à préciser.
     */
    public Controller(int nbImageGenerate){
        if(nbImageGenerate > Math.pow(2, Image.getNbAttributs())){
            throw new IllegalArgumentException("Le nombre d'images généré est supérieur à 2^" + Image.getNbAttributs() + " !");
        }

        _nbImageGenerate = nbImageGenerate;

        //On lance la génération d'images
        this.initImages(_nbImageGenerate);
    }
    //endregion

    //region Méthodes d'instances
    //region Accesseurs
    public ArrayList<Image> getImageList() {
        return _imageList;
    }
    //endregion

    /**
     * Génération aléatoire des images sans doublons dans l'attribut _imageList.
     * @param nbImages
     */
    private void initImages(int nbImages){

        //On génére une liste de nombre, à partir de laquelle on va détérminer les attributs par décomposition binaire.
        ArrayList<Integer> nombres = new ArrayList<>();
        for(int i = 0; i < Math.pow(2, model.Image.getNbAttributs()); i++){
            nombres.add(i);
        }

        //On génére les images
        for(int i = 0; i<nbImages; i++){
            //On choisi un nombre au hasard.
            Integer nombre = nombres.get(rd.nextInt(nombres.size()));

            //On supprime le nombre pour éviter les doublons.
            nombres.remove(nombre);

            //On génére l'image à partir du nombre.
            _imageList.add(new Image(nombre/16%2 == 1, nombre/8%2 == 1, nombre/4%2 == 1, nombre/2%2 == 1, nombre%2 == 1));
        }
    }

    /**
     * Initialisation et calcul du modèle.
     */
    public J48 weka() {
        //On initialise le model
        initTrainingSet();
        //On fait les calculs
        return calculWeka();
    }

    /**
     * Initialisation du jeu d'entrainement dans l'attribut _trainingSet
     */
    private void initTrainingSet() {
        //On initialise les attributs
        FastVector fvCadeaux = new FastVector(2);
        fvCadeaux.addElement("true");
        fvCadeaux.addElement("false");
        Attribute attCadeaux = new Attribute("Cadeaux : ", fvCadeaux);

        FastVector fvEtoile = new FastVector(2);
        fvEtoile.addElement("true");
        fvEtoile.addElement("false");
        Attribute attEtoile = new Attribute("Etoile : ", fvEtoile);

        FastVector fvGuirlandes = new FastVector(2);
        fvGuirlandes.addElement("true");
        fvGuirlandes.addElement("false");
        Attribute attGuirlandes = new Attribute("Guirlandes : ", fvGuirlandes);

        FastVector fvNeige = new FastVector(2);
        fvNeige.addElement("true");
        fvNeige.addElement("false");
        Attribute attNeige = new Attribute("Neige : ", fvNeige);

        FastVector fvBoules = new FastVector(2);
        fvBoules.addElement("true");
        fvBoules.addElement("false");
        Attribute attBoules = new Attribute("Boules : ", fvBoules);

        //On définit la classe
        FastVector fvClasse = new FastVector(2);
        fvClasse.addElement("true");
        fvClasse.addElement("false");
        Attribute classe = new Attribute("Decision", fvClasse);


        //On met tout les attributs et la classe dans un vecteur.
        FastVector fvAttributs = new FastVector(6);
        fvAttributs.addElement(attCadeaux);
        fvAttributs.addElement(attEtoile);
        fvAttributs.addElement(attGuirlandes);
        fvAttributs.addElement(attNeige);
        fvAttributs.addElement(attBoules);
        fvAttributs.addElement(classe);

        //On définit le training set.
        _trainingSet = new Instances("Relation", fvAttributs, _imageList.size());
        _trainingSet.setClass(classe);

        //On rempli le training set
        for (model.Image image : _imageList) {
            Instance instance = new Instance(6);
            instance.setValue(attCadeaux, image.isCadeaux() ? "true" : "false");
            instance.setValue(attEtoile, image.isEtoile() ? "true" : "false");
            instance.setValue(attGuirlandes, image.isGuirlandes() ? "true" : "false");
            instance.setValue(attNeige, image.isNeige() ? "true" : "false");
            instance.setValue(attBoules, image.isBoules() ? "true" : "false");
            instance.setValue(classe, image.isSelect() ? "true" : "false");
            _trainingSet.add(instance);
        }

    }

    /**
     * Calcul du modèle à partir de _trainingSet. Enregistrement dans _graph
     */
    private J48 calculWeka(){
        try {
            _model = new J48();
            _model.buildClassifier(_trainingSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return _model;
    }
    //endregion

}
