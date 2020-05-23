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
import model.Manager;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EditProfileController extends MainController implements Initializable {

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
    @FXML private Button return_button;
    
    private final String customer_path = "/View/CustomerHome.fxml" ;
    private final String manager_path = "/View/ManagetHome.fxml" ;
    
    private Customer current_customer;
    private Manager current_manager;
    private boolean is_customer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void initData_customer(Customer current_user, boolean is_customer) {
        this.current_customer = current_user;
        this.is_customer = is_customer;
        this.first_name.setText(current_user.getFirstName());
        this.last_name.setText(current_user.getLastName());
        this.user_name.setText(current_user.getUserName());
        this.address.setText(current_user.getShippingAddress());
        this.email.setText(current_user.getEmail());
        this.password.setText(current_user.getPassword());
        this.phone.setText(current_user.getPhoneNumber());
    }
    
    public void initData_Manager(Manager current_user, boolean is_customer) {
        this.current_manager = current_user;
        this.is_customer = is_customer;
        this.first_name.setText(current_user.getFirstName());
        this.last_name.setText(current_user.getLastName());
        this.user_name.setText(current_user.getUserName());
        this.address.setText(current_user.getShippingAddress());
        this.email.setText(current_user.getEmail());
        this.password.setText(current_user.getPassword());
        this.phone.setText(current_user.getPhoneNumber());
    }
    
    @FXML
    public void save(ActionEvent event) throws IOException, SQLException{
        if(is_customer){
            current_customer.setEmail(email.getText());
            current_customer.setFirstName(first_name.getText());
            current_customer.setLastName(last_name.getText());
            current_customer.setPassword(password.getText());
            current_customer.setPhoneNumber(phone.getText());
            current_customer.setShippingAddress(address.getText());
            current_customer.setUserName(user_name.getText());
            current_customer.update();
            change_scene(event , customer_path);
        }else{
            current_manager.setEmail(email.getText());
            current_manager.setFirstName(first_name.getText());
            current_manager.setLastName(last_name.getText());
            current_manager.setPassword(password.getText());
            current_manager.setPhoneNumber(phone.getText());
            current_manager.setShippingAddress(address.getText());
            current_manager.setUserName(user_name.getText());
            current_manager.update();
            change_scene(event , manager_path);
        }
       
    }
    
    @FXML
    public void return_home(ActionEvent event) throws IOException{
       if(is_customer)
            change_scene(event , customer_path) ;
       else
            change_scene(event , manager_path) ;
    }
    
    @Override
    public void init_controller(FXMLLoader loader){
        if(is_customer){
             CustomerHomeController controller = loader.getController();
             controller.initData(current_customer,is_customer);
        }else{
             ManagerHomeController controller = loader.getController();
             controller.initData(current_manager,is_customer);
        }
       
    }

   
    
}
