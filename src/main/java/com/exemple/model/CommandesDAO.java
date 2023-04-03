package com.exemple.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandesDAO extends BaseDAO<Commandes> {
    public CommandesDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Commandes object) throws SQLException {

        String req = "insert into commandes (id_client, id_prod , id_credit, date_commande , montant_total , statut ) values (? , ? , ? , ?,?,?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setInt(1 , object.getId_client());
        this.preparedStatement.setInt(2 , object.getId_prod());
        this.preparedStatement.setInt(3 , object.getId_credit());
        this.preparedStatement.setString(4, String.valueOf(object.getDate_commande()));
        this.preparedStatement.setDouble(5 , object.getMontant_total());
        this.preparedStatement.setString(6 , object.getStatut());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Commandes object) throws SQLException {

        String req = "update commandes set id_client = ?,id_prod = ?,id_credit = ?, date_commande = ?, montant_total = ?, statut = ?  where id = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , object.getId_client());
        this.preparedStatement.setInt(2 , object.getId_prod());
        this.preparedStatement.setInt(3 , object.getId_credit());
        this.preparedStatement.setString(4, String.valueOf(object.getDate_commande()));
        this.preparedStatement.setDouble(5 , object.getMontant_total());
        this.preparedStatement.setString(6 , object.getStatut());
        this.preparedStatement.setLong(7, object.getId());

        this.preparedStatement.executeUpdate();

    }

    public void delete(Commandes object) throws SQLException {

        String req = "delete from commandes where id = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1, object.getId());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public Commandes getOne(Long id, long id_client) throws SQLException {
        return null;
    }

    @Override
    public Credit getOne(Long id) throws SQLException {
        String query = "SELECT * FROM commandes WHERE id= ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Credit(
                    );

                }
            }
        }
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Commandes> getAll() throws SQLException{

        List<Commandes> listCommandes = new ArrayList<>();
        String req = " select * from commandes" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            listCommandes.add( new Commandes(this.resultSet.getLong(1) , this.resultSet.getInt(2), this.resultSet.getInt(3),this.resultSet.getInt(4),
                    this.resultSet.getDate(5),this.resultSet.getDouble(6),this.resultSet.getString(7)));


        }

        return listCommandes;
    }
}
