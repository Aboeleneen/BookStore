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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Customer;
import model.User;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CustomerHomeController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button book_button ;
    @FXML private Button edit_button;
    @FXML private Button cart_button;
    @FXML private Button logout_button;
    
    final private String book_path = "/View/Books.fxml" ;
    final private String edit_path = "/View/EditProfile.fxml" ;
    final private String cart_path = "/View/Cart.fxml" ;
    final private String logout_path = "/View/Loading.fxml" ;
    private String current_path ;
    
    private Customer current_user ;
    private boolean is_customer = true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Customer current_user , boolean is_customer){
        this.current_user = current_user;
        this.is_customer = is_customer;
    }
    
    @FXML 
    public void show_books(ActionEvent event) throws IOException{
        current_path = book_path;
        change_scene(event  , book_path) ;
    }
    
    @FXML
    public void edit_profile(ActionEvent event) throws IOException{
        current_path = edit_path;
        change_scene(event  , edit_path) ;
    }
    
    @FXML
    public void show_cart(ActionEvent event) throws IOException{
        current_path = cart_path;
        change_scene(event  , cart_path) ;
    }
    
    @FXML 
    public void logout(ActionEvent event) throws IOException{
        current_path = logout_path;
        change_scene(event , logout_path);
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
        switch (current_path) {
            case book_path:
                BooksController controller = loader.getController();
                controller.initData_customer(current_user , is_customer);
                break;
            case edit_path:
                EditProfileController controller2 = loader.getController();
                controller2.initData_customer(current_user , is_customer);
                break;
            case cart_path:
                CartController controller3 = loader.getController();
        {
            try {
                controller3.initData_customer(current_user , is_customer);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            default:
                break;
        } 
    }
    
}
