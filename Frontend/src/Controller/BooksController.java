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
public class BooksController extends MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Button science_button ;
    @FXML private Button art_button ;
    @FXML private Button religion_button ;
    @FXML private Button history_button ;
    @FXML private Button geography_button ;
    @FXML private Button return_button ;
    @FXML private Button search_button ;
    @FXML private TextField search_bar ;
    
    
    final private String return_path = "/View/CustomerHome.fxml" ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void get_science_book(ActionEvent event){
        get_books_category("science");
    }
    
     @FXML
    public void get_art_book(ActionEvent event){
        get_books_category("art");
    }
    
     @FXML
    public void get_religion_book(ActionEvent event){
        get_books_category("religion");
    }
    
     @FXML
    public void get_history_book(ActionEvent event){
        get_books_category("history");
    }
    
     @FXML
    public void get_geography_book(ActionEvent event){
        get_books_category("geography");
    }
    
     @FXML
    public void return_home(ActionEvent event) throws IOException{
        change_scene(event , return_path) ;
    }
    
    @FXML
    public void search(ActionEvent event) {
        
    }
    
    public void get_books_category(String category){
        
    }
    
}
