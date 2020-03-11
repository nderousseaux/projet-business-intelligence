import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static Random rd = new Random();

    public static int Main(String[] args){

        //On génére le set d'image
        ArrayList<Image> images = generateImages(20);

        return 0;
    }

    public static ArrayList<Image> generateImages(int nombreDImage){
        ArrayList<Image> retour = new ArrayList<Image>();
        for(int i = 0; i<nombreDImage; i++){
            retour.add(new Image(rd.nextBoolean(), rd.nextBoolean(), rd.nextBoolean(), rd.nextBoolean(), rd.nextBoolean()));
        }
        return retour;
    }



}