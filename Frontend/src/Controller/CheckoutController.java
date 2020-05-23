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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CheckoutController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button pay_button;
    @FXML private Button return_button;
    @FXML private Text error_field;
    
    private final String return_path = "/View/Cart.fxml" ;
    private final String pay_path = "/View/CustomerHome.fxml" ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void pay(ActionEvent event) throws IOException{
        change_scene(event , pay_path);
    }
    
    @FXML
    public void return_cart(ActionEvent event) throws IOException{
        change_scene(event , return_path);
    }
    
}
