/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    public void show_books(ActionEvent event) throws IOException{
        change_scene(event  , book_path) ;
    }
    
    @FXML
    public void edit_profile(ActionEvent event) throws IOException{
        change_scene(event  , edit_path) ;
    }
    
    @FXML
    public void show_cart(ActionEvent event) throws IOException{
        change_scene(event  , cart_path) ;
    }
    
    @FXML 
    public void logout(ActionEvent event) throws IOException{
        change_scene(event , logout_path);
    }
    
}
