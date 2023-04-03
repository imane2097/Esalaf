package com.example.esalaf;
import com.exemple.model.Client;
import com.exemple.model.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.exemple.model.Client;
import com.exemple.model.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;

    @FXML
    private TextField tele;


    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, Long> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;
    @FXML
    private TableColumn<Client, String> col_prenom;

    @FXML
    private TableColumn<Client, String> col_tele;


    @FXML
    protected void onSaveButtonClick(){

        Client cli = new Client(0l , nom.getText(), prenom.getText()  , tele.getText());

        try {
            ClientDAO clidao = new ClientDAO();

            clidao.save(cli);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }
    // To update an existing client:
    @FXML
    protected void onUpdateButtonClick() {
        Client selectedclient = mytab.getSelectionModel().getSelectedItem();
        if(selectedclient != null){
            try {
                ClientDAO clidao = new ClientDAO();
                selectedclient.setNom(nom.getText());
                selectedclient.setPrenom(prenom.getText());
                selectedclient.setTelephone(tele.getText());
                clidao.update(selectedclient);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            UpdateTable();
        }
    }
    // To get a single client:
    @FXML
    public void onGetOneButtonClick() {
        Client selectedClient = mytab.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            nom.setText(selectedClient.getNom());
            prenom.setText(selectedClient.getPrenom());
            tele.setText(selectedClient.getTelephone());
        }

    }

    @FXML
    protected void onDeleteButtonClick() {
        Client selectedClient = mytab.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            try {
                ClientDAO clidao = new ClientDAO();
                clidao.delete(selectedClient);
                UpdateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void onClearInput() {
        nom.setText("");
        prenom.setText("");
        tele.setText("");
    }
    @FXML
    protected void onViewProductsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("produit-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Produits");
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
    @FXML
    protected void onViewCreditsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("credit-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Credits");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Client,Long>("id_client"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));


        mytab.setItems(getDataClients());
    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clidao = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            for(Client ettemp : clidao.getAll())
                listfx.add(ettemp);

       } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    }

