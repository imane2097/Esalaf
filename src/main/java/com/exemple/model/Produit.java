package com.exemple.model;


// java beans (Entity)
public class Produit extends Credit {

    private Long id_prod ;

    private String nom ;

    private String description;

    private double prix;
    private int stock ;

    public Produit() {
    }

    public Produit(Long id_prod, String nom,String description, double prix , int stock) {
        this.id_prod = id_prod;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock=stock;
    }

    public Produit(long id_prod, String text, String text1, String text2, String text3) {
        super();

    }


    public Long getId_prod() {
        return id_prod;
    }

    public void setId(Long id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description){ this.description = description; }

    public String getDescription(){ return description;}

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix= prix;
    }
    public int getStock(){return stock;}

    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "Produits{" +
                "id_prod=" + id_prod +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix='" + prix+ '\'' +
                ", stock='" + stock+ '\'' +
                '}';
    }
}
