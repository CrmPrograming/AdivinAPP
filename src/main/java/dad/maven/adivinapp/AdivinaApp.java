package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinaApp extends Application {
	
	private Button btComprobar;
	private TextField tfNumero;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		tfNumero = new TextField();
		tfNumero.setPrefColumnCount(3);
		tfNumero.setPromptText("Indique el número a adivinar");
		tfNumero.setMaxWidth(100);
		tfNumero.setAlignment(Pos.CENTER);
		
		Label lbIntroduccion = new Label();
		lbIntroduccion.setText("Introduce un número del 1 al 100");
		
		btComprobar = new Button();
		btComprobar.setText("Comprobar");
		btComprobar.setDefaultButton(true);
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(lbIntroduccion, tfNumero, btComprobar);
		
		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
