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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Customer;
import model.Manager;

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
    
    private Customer current_customer;
    private Manager current_manager;
    private boolean is_customer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void pay(ActionEvent event) throws IOException{
        if(is_customer){
           // current_customer.addCreditCard();
        }
        change_scene(event , pay_path);
    }
    
    @FXML
    public void return_cart(ActionEvent event) throws IOException{
        change_scene(event , return_path);
    }

    public void initData_customer(Customer current_customer, boolean is_customer) {
        this.current_customer = current_customer;
        this.is_customer = is_customer;
    }

    public void initData_manager(Manager current_manager, boolean is_customer) {
        this.current_manager = current_manager;
        this.is_customer = is_customer;
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
        
    }
    
}
