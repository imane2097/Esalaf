package com.exemple.model;


import java.sql.Date;

public class Credit {

    private Long id_credit ;

    private Double montant ;

    private Date date_echeance;




    public Credit(Long id_credit, Double montant,Date date_echeance) {
        this.id_credit = id_credit;
        this.montant = montant;
        this.date_echeance = date_echeance;
    }

    public Credit() {
    }

    public Long getId_credit() {
        return id_credit;
    }

    public void setId_credit(Long id_credit) {
        this.id_credit = id_credit;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public void setDate_echeance(Date date_echeance){ this.date_echeance = date_echeance; }

    public Date getDate_echeance(){ return  date_echeance;}


    @Override
    public String toString() {
        return "Credit{" +
                "id_credit=" + id_credit+
                ", montant='" + montant + '\'' +
                ", date echeance='" + date_echeance + '\'' +
                '}';
    }
}
