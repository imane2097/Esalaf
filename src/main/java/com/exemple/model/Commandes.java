package com.exemple.model;

import java.sql.Date;

// java beans (Entity)
public class Commandes {

    private Long id ;

    private int id_client ;
    private int id_prod;
    private int id_credit;
    private Date date_commande;

    private Double montant_total ;

    private String statut;



    public Commandes() {
    }

    public Commandes(Long id, int id_client, int id_prod, int  id_credit ,Date date_commande, Double montant_total , String statut ) {
        this.id = id;
        this.id_client = id_client;
        this.id_prod = id_prod;
        this.id_credit= id_credit;
        this.date_commande = date_commande;
        this.montant_total = montant_total;
        this.statut = statut ;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_prod(int id_prod) { this.id_prod = id_prod;}

    public int getId_prod(){ return id_prod; }
    public void setId_credit(int id_credit) { this.id_credit = id_credit;}

    public int getId_credit(){ return id_credit; }
    public void setDate_commande(Date dateCommande){ this.date_commande = dateCommande; }

    public Date getDate_commande(){ return date_commande;}

    public void setMontant_total(Double montant){ this.montant_total = montant; }

    public Double getMontant_total(){ return montant_total;}

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut(){return statut;}


    @Override
    public String toString() {
        return "Commandes{" +
                "id=" + id+
                ", id_client='" + id_client + '\'' +
                ", id_prod='" + id_prod + '\'' +
                ", id_credit='" + id_credit + '\'' +
                ", date_commande='" + date_commande + '\'' +
                ", montant_total='" + montant_total + '\'' +
                ", statut='" + statut + '\'' +

                '}';
    }
}