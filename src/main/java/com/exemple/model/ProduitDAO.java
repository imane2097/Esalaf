package com.exemple.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {
    public ProduitDAO() throws SQLException {

        super();
    }

    // mapping objet --> relation
    @Override
    public void save(Produit object) throws SQLException {

        String req = "insert into produit (nom ,description, prix , stock) values (? , ? , ? , ?) ;";


        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2,object.getDescription());
        this.preparedStatement.setDouble(3 , object.getPrix());
        this.preparedStatement.setInt(4 , object.getStock());

        this.preparedStatement.execute();

    }

    @Override
    public void update(Produit object) throws SQLException {

        String req = "update produit set nom = ?, description = ?, prix = ?, stock = ? where id_prod = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setString(2, object.getDescription());
        this.preparedStatement.setDouble(3, object.getPrix());
        this.preparedStatement.setInt(4, object.getStock());
        this.preparedStatement.setLong(5, object.getId_prod());

        this.preparedStatement.executeUpdate();

    }

    public void delete(Produit object) throws SQLException {

        String req = "delete from produit where id_prod = ?;";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1, object.getId_prod());

        this.preparedStatement.executeUpdate();

    }

    @Override
    public Produit getOne(Long id, long id_client) throws SQLException {
        return null;
    }

    @Override
    public Credit getOne(Long id_prod) throws SQLException {
        String query = "SELECT * FROM client WHERE id_prod = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id_prod);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Produit(
                            resultSet.getLong("id_prod"),
                            resultSet.getString("nom"),
                            resultSet.getString("description"),
                            resultSet.getDouble("prix"),
                            resultSet.getInt("stock")
                    );
                }
            }
        }
        return null;
    }


    // mapping relation --> objet
    @Override
    public List<Produit> getAll() throws SQLException{

        List<Produit> mylist = new ArrayList<>();
        String req = " select * from produit" ;


        this.statement = this.connection.createStatement();

        this.resultSet =  this.statement.executeQuery(req);

        while (this.resultSet.next()){

            mylist.add( new Produit(this.resultSet.getLong(1) , this.resultSet.getString(2), this.resultSet.getString(3),
                    this.resultSet.getDouble(4),this.resultSet.getInt(5)));


        }

        return mylist;
    }
}
