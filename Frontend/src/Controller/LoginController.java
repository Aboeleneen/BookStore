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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Customer;
import model.Manager;
import model.UserCredintial;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class LoginController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button button ;
    @FXML private Button return_button ;
    @FXML private Button signup_button;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Text error_field;
    
   
    
    private final String manager_path = "/View/ManagerHome.fxml";
    private final String customer_path = "/View/CustomerHome.fxml" ;
    private final String return_path = "/View/Loading.fxml" ;
    private final String signup_path = "/View/SignUp.fxml" ;
    private String  current_path;
    
    private Customer current_customer ;
    private Manager current_manager;
    private boolean is_customer ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(boolean is_customer){
        this.is_customer = is_customer;
    }
    
    @FXML
    public void login(ActionEvent event) throws IOException{
        UserCredintial model = new UserCredintial();
        if(is_customer){
            current_customer = (Customer) model.login(username.getText() , password.getText());
            current_path = customer_path;
            if(current_customer == null) {
                show_error();
                return ;
            }
        } 
        else{
            current_manager = (Manager) model.login(username.getText() , password.getText());
            current_path = manager_path;
             if(current_manager == null) {
                show_error();
                return ;
            }
        }
        change_scene(event , current_path); 
    }
    
    @FXML
    public void signup(ActionEvent event) throws IOException{
    	current_path = signup_path;
        change_scene(event , signup_path);
    }
    @FXML
    public void return_loading(ActionEvent event) throws IOException{
    	current_path = return_path;
        change_scene(event ,return_path);
    }
    
    
    public void show_error(){
        this.error_field.setText("invalid username or password");
    }
    @Override
    public void init_controller(FXMLLoader loader){
    	 if(current_path.equals(customer_path) || current_path.equals(manager_path)) {
    		 if(is_customer){
                 CustomerHomeController controller = loader.getController();
                 controller.initData(this.current_customer , is_customer);
            }else{
                 ManagerHomeController controller = loader.getController();
                 controller.initData(this.current_manager , is_customer);
            }
    	 }
    }
    
}
