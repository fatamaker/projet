package application.models;

import java.util.*;

public class Panier {
    private Map<Produit, Integer> produits = new HashMap<>();

    public void ajouterProduit(Produit p, int quantite) {
        produits.put(p, produits.getOrDefault(p, 0) + quantite);
    }

    public void retirerProduit(Produit p, int quantite) {
        if (produits.containsKey(p)) {
            int qte = produits.get(p) - quantite;
            if (qte <= 0) produits.remove(p);
            else produits.put(p, qte);
        }
    }

    public List<Produit> getProduits() {
        return new ArrayList<>(produits.keySet());
    }

    public int getQuantite(Produit p) {
        return produits.getOrDefault(p, 0);
    }

    public double getSousTotal() {
        return produits.entrySet().stream()
            .mapToDouble(e -> e.getKey().getPrix() * e.getValue())
            .sum();
    }

    public Map<Produit, Integer> getDetails() {
        return produits;
    }

    public void viderPanier() {
        produits.clear();
    }
}
