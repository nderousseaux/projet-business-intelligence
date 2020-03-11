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
        return "Neige : " + _neige  + "\n Guirlandes : " + _guirlandes + "\n Cadeaux : " + _cadeaux + "\n Etoile : " + _etoile + "\n boules : " + _boules;
    }


}