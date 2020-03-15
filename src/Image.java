public class Image {

    public boolean _neige;
    public boolean _guirlandes;
    public boolean _cadeaux;
    public boolean _etoile;
    public boolean _boules;

    public boolean _select = false;

    public static int _nbAttributs = 5;

    public Image(boolean neige, boolean guirlandes, boolean cadeaux, boolean etoile, boolean boules){
        _neige = neige;
        _guirlandes = guirlandes;
        _cadeaux = cadeaux;
        _etoile = etoile;
        _boules = boules;
    }


    public String toString(){
        return "<HTML>Neige : " + _neige  + "<br> Guirlandes : " + _guirlandes + "<br> Cadeaux : " + _cadeaux + "<br> Etoile : " + _etoile + "<br> boules : " + _boules + "<HTML";
    }


}