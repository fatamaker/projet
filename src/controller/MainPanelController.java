package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.models.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPanelController implements Initializable {

    private List<Button> menus;
    private final Panier panier = new Panier(); 
    public static BorderPane staticBorderPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnHome, btnCart, btnOrders;

    @FXML
    private AreaChart<?, ?> chartPurchase;

    @FXML
    private AreaChart<?, ?> chartSale;

    @FXML
    private LineChart<?, ?> chartReceipt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menus = Arrays.asList(btnHome, btnCart, btnOrders);
        highlightButton(btnHome);
        staticBorderPane = borderPane;
    }

    /**
     * Met à jour le style du bouton actif
     */
    private void highlightButton(Button clickedButton) {
        for (Button button : menus) {
            if (button != null) {
                button.setStyle(button == clickedButton
                        ? "-fx-text-fill: #f0f0f0; -fx-background-color: #2b2a26;"
                        : "-fx-text-fill: #f0f0f0; -fx-background-color: #404040;");
            }
        }
    }

    /**
     * Charge dynamiquement une vue FXML dans le BorderPane central
     */
    private void loadFXML(String fileName) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/" + fileName + ".fxml"));
            borderPane.setCenter(parent);
        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clear() {
        borderPane.setCenter(null);
    }

    @FXML
    private void close() {
        try {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("User Login");
            newStage.getIcons().add(new Image("/asset/icon.png"));
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Méthodes liées aux boutons
    @FXML
    private void loadHomeView(ActionEvent e) {
        loadFXML("HomeView");
        highlightButton(btnHome);
    }

    @FXML
    private void loadPage01View(ActionEvent e) {
        loadFXML("Page01View");
        highlightButton(btnCart);
    }

    @FXML
    private void loadPage02View(ActionEvent e) {
        loadFXML("Page02View");
        highlightButton(btnOrders);
    }

    @FXML
    private void loadPage03View(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Page03View.fxml"));
            Parent parent = loader.load();
            Page03Controller controller = loader.getController();
            controller.setPanier(panier); 
            borderPane.setCenter(parent);
            highlightButton(btnCart);
        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadPage04View(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Page04View.fxml"));
            Parent parent = loader.load();
            Page04Controller controller = loader.getController();
            controller.setPanier(panier); 
            borderPane.setCenter(parent);
            highlightButton(btnCart);
        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadPage05View(ActionEvent e) {
        loadFXML("Page05View");
    }

    @FXML
    private void loadPage06View(ActionEvent e) {
        loadFXML("Page06View");
    }

    @FXML
    private void loadPage07View(ActionEvent e) {
        loadFXML("Page07View");
    }

    @FXML
    private void loadPage08View(ActionEvent e) {
        loadFXML("Page08View");
    }

    @FXML
    private void loadPage09View(ActionEvent e) {
        loadFXML("Page09View");
    }

    @FXML
    private void loadPage10View(ActionEvent e) {
        loadFXML("Page10View");
    }
}
