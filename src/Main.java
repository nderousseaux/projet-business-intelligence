import controler.Controller;
import view.GraphicalView;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(20);
        new GraphicalView(controller);
    }
}