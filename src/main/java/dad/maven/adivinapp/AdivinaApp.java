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
	
	/**
	 * M�todo encargado de inicializar la partida a valores por defecto
	 * y generar un nuevo n�mero aleatorio.
	 */
	private void inicializarPartida() {
		generarNumero();
		intentos = 0;
	}
	
	/**
	 * M�todo encargado de generar un n�mero aleatorio entre el m�nimo
	 * y el m�ximo especificados en los atributos de la clase.
	 */
	private void generarNumero() {
		numAleatorio = (int) (Math.random() * (MAX - MIN + 1) + MIN);		
	}
	
	/**
	 * M�todo encargado de mostrar la ventana de fallo. Seg�n
	 * qu� modo se le especifique indicar�:
	 * 
	 * - 1 si el n�mero a adivinar es menor
	 * - 0 si el n�mero a adivinar es mayor
	 * 
	 * @param modo Entero con el tipo de fallo cometido
	 * @param numero Entero con el n�mero dado por el usuario
	 */
	private void mostrarFallo(int modo, int numero) {
		String mensajeModo = "El n�mero a adivinar es " + ((modo == 1)? "menor" : "mayor") + " que " + numero;
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("AdivinaApp");
		alert.setHeaderText("�Has fallado!");
		alert.setContentText(mensajeModo + "\n\nVuelve a intentarlo.");

		alert.showAndWait();
	}
	
	/**
	 * M�todo encargado de mostrar la ventana de error.
	 */
	private void mostrarError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("El n�mero introducido no es v�lido");

		alert.showAndWait();
	}
	
	/**
	 * M�todo encargado de mostrar la ventana de acierto.
	 */
	private void mostrarAcierto() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("�Has ganado!");
		alert.setContentText("S�lo has necesitado " + intentos + " intentos.\n\n" +
							 "Vuelve a jugar y hazlo mejor.");

		alert.showAndWait();
	}
	
	/**
	 * M�todo encargado de comprobar si el n�mero indicado
	 * es el que debe adivinar o es otro.
	 * 
	 * @param e Evento asociado al hacer click en el bot�n
	 */
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

	/**
	 * Sobreescritura del m�todo start de la clase Application con la
	 * inicializaci�n de la interfaz gr�fica de la aplicaci�n.
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		inicializarPartida();
		
		tfNumero = new TextField();
		tfNumero.setPrefColumnCount(3);
		tfNumero.setPromptText("Indique el n�mero a adivinar");
		tfNumero.setMaxWidth(100);
		tfNumero.setAlignment(Pos.CENTER);
		
		Label lbIntroduccion = new Label();
		lbIntroduccion.setText("Introduce un n�mero del 1 al 100");
		
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
	
	/**
	 * M�todo principal encargado de lanzar la ventana de la aplicaci�n.
	 * 
	 * @param args Array con los par�metros de entrada
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
