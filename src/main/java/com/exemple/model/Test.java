package com.exemple.model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test {

    public static void main(String[] args) {


        try {
            // entity
            Client cli  = new Client(0l,"laadaili","aya","9009990099");

            //Transacation
            ClientDAO clidao = new ClientDAO();

            //Save trasanction
            clidao.save(cli);


            List<Client> mylist =  clidao.getAll();

            for (Client temp :mylist) {

                System.out.println(temp.toString());

            }
            // entity
            Produit prod  = new Produit(0l,"P1","desc1",320,15);

            //Transacation
            ProduitDAO prodao = new ProduitDAO();

            //Save trasanction
            prodao.save(prod);


            List<Produit> produitList =  prodao.getAll();

            for (Produit temp :produitList) {

                System.out.println(temp.toString());

            }
            // entity
            Commandes cmd  = new Commandes(0l,01,01,01,Date.valueOf("21_03_2003"),32.5,"payée");

            //Transacation
            CommandesDAO cmddao = new CommandesDAO();

            //Save trasanction
            cmddao.save(cmd);


            List<Commandes> CommandesList =  cmddao.getAll();

            for (Commandes temp :CommandesList) {

                System.out.println(temp.toString());

            }
            // entity
            Credit crd  = new Credit(0l,324.89, Date.valueOf("21_03_2003"));

            //Transacation
            CreditDAO crddao = new CreditDAO();

            //Save trasanction
            crddao.save(crd);


            List<Credit> Credits =  crddao.getAll();

            for (Credit temp :Credits) {

                System.out.println(temp.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
