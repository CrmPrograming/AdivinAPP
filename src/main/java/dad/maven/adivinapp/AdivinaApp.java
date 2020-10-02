package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinaApp extends Application {
	
	private Button btComprobar;
	private TextField tfNumero;
	private int numAleatorio;
	private int intentos;
	private final int MIN = 0;
	private final int MAX = 100;
	
	private void inicializarPartida() {
		generarNumero();
		intentos = 0;
	}
	
	private void generarNumero() {
		numAleatorio = (int) (Math.random() * (MAX - MIN + 1) + MIN);		
	}
	
	private void mostrarFallo(int modo, int numero) {
		String mensajeModo = "El número a adivinar es " + ((modo == 1)? "menor" : "mayor") + " que " + numero;
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AdivinaApp");
		alert.setHeaderText("¡Has fallado!");
		alert.setContentText(mensajeModo + "\n\nVuelve a intentarlo.");

		alert.showAndWait();
	}
	
	private void mostrarError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("El número introducido no es válido");

		alert.showAndWait();
	}
	
	private void mostrarAcierto() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has ganado!");
		alert.setContentText("Sólo has necesitado " + intentos + " intentos.\n\n" +
							 "Vuelve a jugar y hazlo mejor.");

		alert.showAndWait();
	}
	
	private void onComprobarButtonAction(ActionEvent e) {
		intentos++;
		try {
			int numero = Integer.parseInt(tfNumero.getText());
			if (numero < numAleatorio)
				mostrarFallo(0, numero);
			else if (numero > numAleatorio)
				mostrarFallo(1, numero);
			else {
				mostrarAcierto();
				inicializarPartida();
			}
		} catch (NumberFormatException error) {
			mostrarError();
		}
			
	}
			
	@Override
	public void start(Stage primaryStage) throws Exception {
		inicializarPartida();
		
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
		btComprobar.setOnAction(e -> onComprobarButtonAction(e));
		
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
