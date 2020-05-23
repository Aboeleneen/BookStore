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
    @FXML private Text error_field;
    
    private boolean is_customer ;
    private final String customer_path = "/View/ManagerHome.fxml" ;
    private final String return_path = "/View/Loading.fxml" ;
    private final String signup_path = "/View/SignUp.fxml" ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(boolean is_customer){
        this.is_customer = is_customer;
    }
    
    @FXML
    public void login(ActionEvent event) throws IOException{
        change_scene(event , customer_path);
    }
    
    @FXML
    public void signup(ActionEvent event) throws IOException{
        change_scene(event , signup_path);
    }
    @FXML
    public void return_loading(ActionEvent event) throws IOException{
        change_scene(event ,return_path);
    }
    
    
    public void show_error(){
        
    }
    @Override
    public void init_controller(FXMLLoader loader){
        /* if(is_customer){
             CustomerHomeController controller = loader.getController();
             //controller.initData(this.selectedBrand);
        }else{
            
        }*/
    }
    
}
