package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Panier;
import application.models.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProduitDetailController implements Initializable {

    @FXML private ImageView imageView;
    @FXML private Label nomLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label prixLabel;
    @FXML private Label stockLabel;
    @FXML private Label categorieLabel;
    @FXML private Spinner<Integer> quantiteSpinner;

    private Produit produit;
    private Panier monPanier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        quantiteSpinner.setValueFactory(valueFactory);
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
        afficherDetails();
    }

    public void setPanier(Panier panier) {
        this.monPanier = panier;
    }

    private void afficherDetails() {
        if (produit != null) {
            nomLabel.setText(produit.getNom());
            descriptionLabel.setText(produit.getDescription());
            prixLabel.setText(String.format("%.2f TND", produit.getPrix()));
            stockLabel.setText("Stock : " + produit.getStock());
            categorieLabel.setText("Catégorie : " + produit.getCategorie());

            if (produit.getImagePath() != null && !produit.getImagePath().isEmpty()) {
                imageView.setImage(new Image(produit.getImagePath()));
            }
        }
    }

    @FXML
    private void ajouterAuPanier() {
        if (monPanier != null && produit != null) {
            int quantite = quantiteSpinner.getValue();
            if (quantite > produit.getStock()) {
                afficherAlerte("Quantité insuffisante en stock !");
                return;
            }
            monPanier.ajouterProduit(produit, quantite);
            afficherAlerte("Produit ajouté au panier !");
        } else {
            afficherAlerte("Erreur : Panier ou produit non défini.");
        }
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
