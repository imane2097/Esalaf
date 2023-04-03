package com.example.esalaf;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.exemple.model.Commandes;
import com.exemple.model.CommandesDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
public class CommandeController implements Initializable {
    @FXML
    private TextField id_client;

    @FXML
    private TextField id_prod;

    @FXML
    private TextField id_credit;

    @FXML
    private TextField date_commande;
    @FXML
    private TextField montant_total;
    @FXML
    private TextField statut;
    @FXML
    private TableView<Commandes> mytab;

    @FXML
    private TableColumn<Commandes, Long> col_id;

    @FXML
    private TableColumn<Commandes, Integer> col_id_client;
    @FXML
    private TableColumn<Commandes, Integer> col_id_prod;
    @FXML
    private TableColumn<Commandes, Integer> col_id_credit;
    @FXML
    private TableColumn<Commandes, Date> col_date_commande;

    @FXML
    private TableColumn<Commandes, Double> col_montant_total;

    @FXML
    private TableColumn<Commandes, String> col_statut;


    @FXML
    protected void onSaveButtonClick(){

        Commandes cmd = new Commandes(0L, Integer.parseInt(id_client.getText()) ,Integer.parseInt(id_prod.getText()), Integer.parseInt(id_credit.getText()), Date.valueOf(date_commande.getText()),Double.parseDouble(montant_total.getText()),statut.getText());

        try {
            CommandesDAO cmddao = new CommandesDAO();

            cmddao.save(cmd);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }


    // To update an existing commande:
    @FXML
    protected void onUpdateButtonClick() {
        Commandes selectedcmd = mytab.getSelectionModel().getSelectedItem();
        if(selectedcmd != null){
            try {
                CommandesDAO cmddao = new CommandesDAO();
                selectedcmd.setId_client(Integer.parseInt(id_client.getText()));
                selectedcmd.setId_prod(Integer.parseInt(id_prod.getText()));
                selectedcmd.setId_credit(Integer.parseInt(id_credit.getText()));
                selectedcmd.setDate_commande(Date.valueOf(date_commande.getText()));
                selectedcmd.setMontant_total(Double.parseDouble(montant_total.getText()));
                selectedcmd.setStatut(statut.getText());
                cmddao.update(selectedcmd);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            UpdateTable();
        }
    }
    // To get a single commande:
    @FXML
    public void onGetOneButtonClick() {
        Commandes selectedcmd = mytab.getSelectionModel().getSelectedItem();
        if(selectedcmd != null){
            id_client.setText(String.valueOf(selectedcmd.getId_client()));
            id_prod.setText(String.valueOf(selectedcmd.getId_prod()));
            id_credit.setText(String.valueOf(selectedcmd.getId_credit()));
            date_commande.setText(String.valueOf(selectedcmd.getDate_commande()));
            montant_total.setText(String.valueOf(selectedcmd.getMontant_total()));
            statut.setText(String.valueOf(selectedcmd.getStatut()));
        }

    }
    // To clear the input
    @FXML
    public void onClearInput() {
        id_client.setText("");
        id_prod.setText("");
        id_credit.setText("");
        date_commande.setText("");
        montant_total.setText("");
        statut.setText("");
    }
    // To delete a commande:
    @FXML
    protected void onDeleteButtonClick() {
        Commandes selectedcmd = mytab.getSelectionModel().getSelectedItem();
        if (selectedcmd != null) {
            try {
                CommandesDAO cmddao = new CommandesDAO();
                cmddao.delete(selectedcmd);
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
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_id_client.setCellValueFactory(new PropertyValueFactory<Commandes,Integer>("id_client"));
        col_id_prod.setCellValueFactory(new PropertyValueFactory<Commandes,Integer>("id_prod"));
        col_id_credit.setCellValueFactory(new PropertyValueFactory<Commandes,Integer>("id_credit"));
        col_date_commande.setCellValueFactory(new PropertyValueFactory<Commandes,Date>("date_commande"));
        col_montant_total.setCellValueFactory(new PropertyValueFactory<Commandes,Double>("montant_total"));
        col_statut.setCellValueFactory(new PropertyValueFactory<Commandes,String>("statut"));
        mytab.setItems(getDataCommandes());
    }

    public static ObservableList<Commandes> getDataCommandes(){

        CommandesDAO cmddao = null;

        ObservableList<Commandes> listcmd = FXCollections.observableArrayList();

        try {
            cmddao = new CommandesDAO();
            for(Commandes ettemp : cmddao.getAll())
                listcmd.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listcmd ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }


}
