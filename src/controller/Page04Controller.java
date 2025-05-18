package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Panier;
import application.models.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Page04Controller implements Initializable {
    
    @FXML private ImageView imageProduit;
    @FXML private Label nomProduit;
    @FXML private Label prixProduit;
    @FXML private Spinner<Integer> quantiteSpinner;
    @FXML private TextArea descriptionProduit;

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
        nomProduit.setText(produit.getNom());
        descriptionProduit.setText(produit.getDescription());
        prixProduit.setText(String.valueOf(produit.getPrix()));

        if (produit.getImagePath() != null) {
            imageProduit.setImage(new Image(produit.getImagePath()));
        }
    }

    @FXML
    private void ajouterAuPanier() {
        if (monPanier != null && produit != null) {
        	int quantite = quantiteSpinner.getValue();
        	monPanier.ajouterProduit(produit, quantite);
            System.out.println("Produit ajouté au panier");
        } else {
            System.out.println("Erreur : Panier ou produit null");
        }
    }
}
