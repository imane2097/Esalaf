package com.exemple.model;


// java beans (Entity)
public class Client {

        private Long id_client ;

        private String nom ;
    private String prenom;
        private String telephone ;

    public Client(String nom, String prenom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Client(long id_client, String nom, String prenom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }
/*
    public Client(Long id_client, String nom,String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
    }
*/
    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom){ this.prenom = prenom; }

    public String getPrenom(){ return prenom;}
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
