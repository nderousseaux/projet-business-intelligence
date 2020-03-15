package model;

/**
 * La classe Image modèlise une image de sapin. Elle possède plusieurs attributs booléens qui définissent ses caractéristiques.
 * (Possède de la neige, ou pas, possède des guirlandes, ou pas, etc...).
 *  Il y a aussi un autre attribut booléan : _select. True si elle est séléctionné par l'utilisateur.
 */
public class Image {


    //region Attributs
    private static int _nbAttributs = 5;

    private boolean _neige;
    private boolean _guirlandes;
    private boolean _cadeaux;
    private boolean _etoile;
    private boolean _boules;

    private boolean _select = false;
    //endregion

    //region Constructeurs
    /**
     * Constructeur d'une image. On renseigne les attributs. Par défaut _select est faux.
     * @param neige
     * @param guirlandes
     * @param cadeaux
     * @param etoile
     * @param boules
     */
    public Image(boolean neige, boolean guirlandes, boolean cadeaux, boolean etoile, boolean boules){
        _neige = neige;
        _guirlandes = guirlandes;
        _cadeaux = cadeaux;
        _etoile = etoile;
        _boules = boules;
    }

    /**
     * Constructeur d'une image. On renseigne les attributs, y compris _select.
     * @param neige
     * @param guirlandes
     * @param cadeaux
     * @param etoile
     * @param boules
     * @param select
     */
    public Image(boolean neige, boolean guirlandes, boolean cadeaux, boolean etoile, boolean boules, boolean select){
        _neige = neige;
        _guirlandes = guirlandes;
        _cadeaux = cadeaux;
        _etoile = etoile;
        _boules = boules;
        _select = select;
    }
    //endregion

    //region Méthodes de classes
    public static int getNbAttributs() {
        return _nbAttributs;
    }

    public static void setNbAttributs(int _nbAttributs) {
        Image._nbAttributs = _nbAttributs;
    }
    //endregion

    //region Méthodes d'instances
    //region Accesseurs
    public boolean isNeige() {
        return _neige;
    }

    public boolean isGuirlandes() {
        return _guirlandes;
    }

    public boolean isCadeaux() {
        return _cadeaux;
    }

    public boolean isEtoile() {
        return _etoile;
    }

    public boolean isBoules() {
        return _boules;
    }

    public boolean isSelect() {
        return _select;
    }

    public void setSelect(boolean _select) {
        this._select = _select;
    }
    //endregion

    /**
     * Retour d'une description de l'image. L'attribut _select n'est pas renseigné.
     * @return String
     */
    @Override
    public String toString(){
        return "<HTML>Neige : " + _neige  + "<br> Guirlandes : " + _guirlandes + "<br> Cadeaux : " + _cadeaux + "<br> Etoile : " + _etoile + "<br> boules : " + _boules + "<HTML";
    }
    //endregion


}