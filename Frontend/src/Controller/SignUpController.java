/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class SignUpController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField first_name ;
    @FXML private TextField last_name ;
    @FXML private TextField user_name ;
    @FXML private TextField password ;
    @FXML private TextField email ;
    @FXML private TextField phone ;
    @FXML private TextField address ;
    @FXML private Button save_button ;
    @FXML private Button return_button ;
    
    private final String path = "/View/CustomerHome.fxml" ;
    private final String return_path = "/View/Login.fxml" ;
    private boolean current_path = true;
    
    private Customer new_customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void signup(ActionEvent event) throws IOException, SQLException {
        new_customer = new Customer(user_name.getText(),
                                     password.getText(), 
                                     first_name.getText(), 
                                     last_name.getText(),
                                     email.getText(),
                                     phone.getText(),
                                     address.getText());
        new_customer.insert();
        current_path = true;
        change_scene(event , path) ;
    }
    
    @FXML 
    public void return_login(ActionEvent event) throws IOException{
        current_path = false;
        change_scene(event, return_path);
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
      if(current_path){
          CustomerHomeController controller = loader.getController();
          controller.initData(new_customer, true);
      }
    }
}
