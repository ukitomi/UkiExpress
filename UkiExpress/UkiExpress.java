import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Start the application
 * @author QiQi Yuki Ou
 */
public class UkiExpress extends Application {
	public static void main(String[] args) {
		FileLoader.getFile(args[0]);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UkiExpressGUI app = new UkiExpressGUI(new UkiExpressPresenter());
		app.start(primaryStage);
	}

}