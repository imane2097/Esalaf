package com.example.esalaf;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class ProduitController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private TextField prix;

    @FXML
    private TextField stock;

    @FXML
    private TableView<Produit> mytab;

    @FXML
    private TableColumn<Produit, Long> col_id;

    @FXML
    private TableColumn<Produit, String> col_nom;

    @FXML
    private TableColumn<Produit, String> col_description;

    @FXML
    private TableColumn<Produit, Double> col_prix;

    @FXML
    private TableColumn<Produit, Integer> col_stock;


    @FXML
    protected void onSaveButtonClick(){

        Produit prod = new Produit(0L, nom.getText() ,description.getText(), Double.parseDouble(prix.getText()), Integer.parseInt(stock.getText()));

        try {
            ProduitDAO proddao = new ProduitDAO();

            proddao.save(prod);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }


    // To update an existing product:
    @FXML
    protected void onUpdateButtonClick() {
        Produit selectedproduct = mytab.getSelectionModel().getSelectedItem();
        if(selectedproduct != null){
            try {
                ProduitDAO proddao = new ProduitDAO();
                selectedproduct.setNom(nom.getText());
                selectedproduct.setDescription(description.getText());
                selectedproduct.setPrix(Double.parseDouble(prix.getText()));
                selectedproduct.setStock(Integer.parseInt(stock.getText()));
                proddao.update(selectedproduct);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            UpdateTable();
        }
    }
    // To get a single product:
    @FXML
    public void onGetOneButtonClick() {
        Produit selectedProduct = mytab.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            nom.setText(selectedProduct.getNom());
            description.setText(selectedProduct.getDescription());
            prix.setText(String.valueOf(selectedProduct.getPrix()));
            stock.setText(String.valueOf(selectedProduct.getStock()));
        }

    }
    // To clear the input
    @FXML
    public void onClearInput() {
        nom.setText("");
        description.setText("");
        prix.setText("");
        stock.setText("");
    }
    // To delete a product:
    @FXML
    protected void onDeleteButtonClick() {
        Produit selectedProduct = mytab.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                ProduitDAO proddao = new ProduitDAO();
                proddao.delete(selectedProduct);
                UpdateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void onViewCreditsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("credit-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crédits");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onViewClientsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Clients");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onViewCommandesButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commande-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Commandes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
        col_stock.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("stock"));
        mytab.setItems(getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO proddao = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            proddao = new ProduitDAO();
            for(Produit ettemp : proddao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }


}