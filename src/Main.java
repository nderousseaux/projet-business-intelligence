import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;

public class Main{

    private static Random rd = new Random();
    private static int nbImages = 20;

    public static void main(String[] args){

        //On génére le set d'image
        ArrayList<Image> images = generateImages(nbImages);

        System.out.println(images.size());

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setSize(1000, 1000);

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


        f.pack();
        f.setVisible(true);

        

        
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