package controller;

import application.models.Commande;
import application.models.CommandeM;
import application.models.LigneCommande;
import application.models.Produit;
import application.models.Panier;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page03Controller implements Initializable {

    @FXML private TableView<Produit> tablePanier;
    @FXML private TableColumn<Produit, String> colProduit;
    @FXML private TableColumn<Produit, Double> colPrix;
    @FXML private TableColumn<Produit, Integer> colQuantite;
    @FXML private TableColumn<Produit, Void> colActions;

    @FXML private TextField nomClient, adresse, telephone, email;
    @FXML private Label sousTotalLabel, fraisLivraisonLabel, totalLabel;

    private Panier panier; 
    private final double FRAIS_LIVRAISON = 5.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(panier != null ? panier.getQuantite(p.getValue()) : 0));

        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button plus = new Button("+");
            private final Button moins = new Button("-");

            {
                plus.setOnAction(e -> {
                    Produit p = getTableView().getItems().get(getIndex());
                    panier.ajouterProduit(p, 1);
                    afficherPanier();
                });

                moins.setOnAction(e -> {
                    Produit p = getTableView().getItems().get(getIndex());
                    panier.retirerProduit(p, 1);
                    afficherPanier();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) setGraphic(new HBox(5, moins, plus));
                else setGraphic(null);
            }
        });
    }

    private void afficherPanier() {
        if (panier != null) {
            tablePanier.getItems().setAll(panier.getProduits());
            sousTotalLabel.setText("Sous-total : " + panier.getSousTotal() + " DT");
            fraisLivraisonLabel.setText("Frais livraison : " + FRAIS_LIVRAISON + " DT");
            totalLabel.setText("Total : " + (panier.getSousTotal() + FRAIS_LIVRAISON) + " DT");
        }
    }

    @FXML
    void passerCommande() {
        if (nomClient.getText().isEmpty() || adresse.getText().isEmpty() || telephone.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Veuillez remplir les champs obligatoires.").show();
            return;
        }

        List<LigneCommande> lignes = new ArrayList<>();
        for (Produit p : panier.getProduits()) {
            int quantite = panier.getQuantite(p);
            lignes.add(new LigneCommande(p, quantite));
        }

        double total = panier.getSousTotal() + FRAIS_LIVRAISON;
        Commande commande = new Commande(
                nomClient.getText(),
                adresse.getText(),
                telephone.getText(),
                email.getText(),
                total,
                lignes
        );

        CommandeM commandeM = new CommandeM();
        if (commandeM.ajouterCommande(commande)) {
            new Alert(Alert.AlertType.INFORMATION, "Commande enregistrée avec succès !").show();
            panier.viderPanier();
            afficherPanier();
        } else {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'enregistrement de la commande.").show();
        }
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
        afficherPanier();
    }
}
