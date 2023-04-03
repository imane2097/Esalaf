package com.example.esalaf;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.exemple.model.Credit;
import com.exemple.model.CreditDAO;
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
public class CreditController implements Initializable {
    @FXML
    private TextField montant;

    @FXML
    private TextField date_echeance;

    @FXML
    private TableView<Credit> mytab;

    @FXML
    private TableColumn<Credit, Long> col_id_credit;

    @FXML
    private TableColumn<Credit, Double> col_montant;

    @FXML
    private TableColumn<Credit, Date> col_date_echeance;

    @FXML
    protected void onSaveButtonClick(){

        Credit crd = new Credit(0l,Double.parseDouble(montant.getText()), Date.valueOf(date_echeance.getText()));

        try {
            CreditDAO crddao = new CreditDAO();

            crddao.save(crd);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }


    // To update an existing product:
    @FXML
    protected void onUpdateButtonClick() {
        Credit selectedCredit = mytab.getSelectionModel().getSelectedItem();
        if (selectedCredit != null) {
            try {
                CreditDAO creditDAO = new CreditDAO();
                selectedCredit.setMontant(Double.parseDouble(montant.getText()));
                selectedCredit.setDate_echeance(Date.valueOf(date_echeance.getText()));
                creditDAO.update(selectedCredit);
                UpdateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // To get a single product:
    @FXML
    public void onGetOneButtonClick() {
        Credit selectedcredit = mytab.getSelectionModel().getSelectedItem();
        if (selectedcredit != null) {
            montant.setText(String.valueOf(selectedcredit.getMontant()));
            date_echeance.setText(String.valueOf(selectedcredit.getDate_echeance()));
        }

    }
    // To clear the input
    @FXML
    public void onClearInput() {
        montant.setText("");
        date_echeance.setText("");
    }
    // To delete a Credit:
    @FXML
    protected void onDeleteButtonClick() {
        Credit selectedcredit = mytab.getSelectionModel().getSelectedItem();
        if (selectedcredit != null) {
            try {
                CreditDAO crddao = new CreditDAO();
                crddao.delete(selectedcredit);
                UpdateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void onViewProductsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("produit-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Produit");
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
        col_id_credit.setCellValueFactory(new PropertyValueFactory<>("id_credit"));
        col_montant.setCellValueFactory(new PropertyValueFactory<Credit,Double>("montant"));
        col_date_echeance.setCellValueFactory(new PropertyValueFactory<Credit, Date>("date_echeance"));
        mytab.setItems(getDataCredits());
    }

    public static ObservableList<Credit> getDataCredits(){

        CreditDAO crddao = null;

        ObservableList<Credit> listcredit = FXCollections.observableArrayList();

        try {
            crddao = new CreditDAO();
            for(Credit ettemp : crddao.getAll())
                listcredit.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listcredit ;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
    }


}
