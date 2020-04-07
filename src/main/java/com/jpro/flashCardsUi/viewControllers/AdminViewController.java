package com.jpro.flashCardsUi.viewControllers;

import com.jfoenix.controls.JFXButton;
import com.jpro.flashCardsUi.client.UserClient;
import com.jpro.flashCardsUi.mapper.UserMapper;
import com.jpro.flashCardsUi.domain.TableViewUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private UserMapper userMapper = new UserMapper();
    private UserClient userClient = new UserClient();
    private ObservableList<TableViewUser> data;

    @FXML
    private TableView usersTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TableColumn id = new TableColumn("ID");
        TableColumn name = new TableColumn("USERNAME");
        TableColumn email = new TableColumn("EMAIL");

        usersTable.getColumns().addAll(id, name, email);
        addButtonToTable();

        data = updateList();

        id.setCellValueFactory(new PropertyValueFactory<TableViewUser, Long>("id"));

        name.setCellValueFactory(new PropertyValueFactory<TableViewUser, String>("name"));

        email.setCellValueFactory(new PropertyValueFactory<TableViewUser, String>("email"));

        usersTable.setItems(data);
    }

    private void addButtonToTable() {
        TableColumn<TableViewUser, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<TableViewUser, Void>, TableCell<TableViewUser, Void>> cellFactory = new Callback<TableColumn<TableViewUser, Void>, TableCell<TableViewUser, Void>>() {
            @Override
            public TableCell<TableViewUser, Void> call(final TableColumn<TableViewUser, Void> param) {
                final TableCell<TableViewUser, Void> cell = new TableCell<TableViewUser, Void>() {

                    private final Button btn = new JFXButton("DELETE");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TableViewUser user = getTableView().getItems().get(getIndex());
                            userClient.deleteUser(user.getId());
                            data = updateList();
                            usersTable.setItems(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        usersTable.getColumns().add(colBtn);
    }

    private ObservableList<TableViewUser> updateList() {
        return FXCollections.observableArrayList(userMapper.mapToMappedUsersList(userClient.getUsers()));
    }

}
