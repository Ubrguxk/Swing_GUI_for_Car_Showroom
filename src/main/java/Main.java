import Controller.MainGUIController;
import Model.CarShowroomModel;
import View.MainGUIView;


public class Main {
    public static void main(String[] args) {
        CarShowroomModel model = new CarShowroomModel();
        MainGUIView view = new MainGUIView();
        MainGUIController controller = new MainGUIController(model, view);
        view.injectController(controller);
        view.setVisible(true);
    }
}
