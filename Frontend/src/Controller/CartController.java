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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CartController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button return_button;
    @FXML private Button checkout_button ;
    @FXML private TextField price_field;
    
    final private String return_path = "/View/CustomerHome.fxml" ;
    final private String checkout_path = "/View/Checkout.fxml" ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void checkout(ActionEvent event) throws IOException {
        change_scene(event , checkout_path) ;
    }
    
    @FXML
    public void return_home(ActionEvent event) throws IOException {
        change_scene(event , return_path) ;
    }
    
}
