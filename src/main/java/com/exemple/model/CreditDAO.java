package com.exemple.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CreditDAO extends  BaseDAO<Credit> {
    public CreditDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Credit object) throws SQLException {

        String req = "insert into credit (montant ,date_echeance) values ( ? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setDouble(1 ,object.getMontant());
        this.preparedStatement.setDate(2, object.getDate_echeance());


        this.preparedStatement.execute();

    }

    @Override
    public void update(Credit object) throws SQLException {
        String req = "update credit set montant = ?, date_echeance = ? where id_credit= ?;";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setDouble(1 ,object.getMontant());
        this.preparedStatement.setDate(2, object.getDate_echeance());
        this.preparedStatement.setLong(3, object.getId_credit());
        this.preparedStatement.executeUpdate();
    }



    public void delete(Credit object) throws SQLException {
        String req = "DELETE FROM credit WHERE id_credit= ? ";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setLong(1, object.getId_credit());
        this.preparedStatement.execute();
    }

    @Override
    public Credit getOne(Long id, long id_client) throws SQLException {
        return null;
    }


    @Override
    public Credit getOne(Long id_credit) throws SQLException {
        String query = "SELECT * FROM credit WHERE id_credit = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id_credit);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Credit(
                            resultSet.getLong("id_credit"),
                            resultSet.getDouble("montant"),
                            resultSet.getDate("date_echeance")
                    );
                }
            }
        }
        return null;
    }

    // mapping relation --> objet
    @Override
    public List<Credit> getAll() throws SQLException{

        List<Credit> credits = new ArrayList<>();

        String query = "SELECT * FROM credit";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    credits.add(new Credit(
                            resultSet.getLong("id_credit"),
                            resultSet.getDouble("montant"),
                            resultSet.getDate("date_echeance")
                    ));
                }
            }
        }

        return credits;
    }

    public Credit getById(Long id_credit) throws SQLException {
        String query = "select * from credit where id_credit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id_credit);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Credit(
                            resultSet.getLong("id_credit"),
                            resultSet.getDouble("montant"),
                            resultSet.getDate("date_echeance")
                    );
                }
            }
        }
        return null;
    }
}

