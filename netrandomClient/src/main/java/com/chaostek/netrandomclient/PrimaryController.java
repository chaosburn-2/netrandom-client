package com.chaostek.netrandomclient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Client to consume REST service
 * IT361 Computer Organization and Operating Systems
 * @author Chris Carson
 * @version 1.0, 10/08/22
 * 
 */
public class PrimaryController implements Initializable
{
    
    // Observable list for the grid. Makes adjustments automatic when retrieving
    // and updating with the JSON data.
    private ObservableList<randomRequestProperty> gridList;
    
    //Imports for from the FXML file
    @FXML
    Button cmd1D4, cmd1D6, cmd1D8, cmd1D10, cmd1D12, cmd1D20, cmdRoll;
    
    @FXML
    ComboBox<String> cboDie;
    
    @FXML
    Spinner spnQuantity;
    
    @FXML
    TableView<randomRequestProperty> tblResponses;
    
    @FXML
    TableColumn<randomRequestProperty, String> colRequest, colResponse;
    
    /**
     * Retrieve JSON from server and populate Grid
     *
     */
    public void restGET()
    {
        String inputJSON = "";
        
        try
        {
            // Setup the HTTP request, URI is the path to the API, GET sets up
            // a GET request, and build creates the request
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://"+serverinfo.SERVER_ADDRESS+":"+serverinfo.SERVER_PORT+"/netrandom/api/random"))
                    .GET()
                    .build();
            
            // Create an HTTP client, then send out the request
            HttpClient getClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = getClient.send(getRequest, BodyHandlers.ofString());
            
            // Read the body JSON response in
            inputJSON = getResponse.body();
            
            //System.out.println(inputJSON);
        
        }
        catch (URISyntaxException | IOException | InterruptedException e)
        {
            System.out.println("Error retrieving GET");
        }
        
        // If the JSON is empty, clear the list
        if (inputJSON.contains("[]"))
        {
            gridList.clear();
        }
        else
        {
            // Create an array list without Properties, since GSON didn't like them
            ArrayList<randomRequest> gsonConvert;
            
            // Convert the GSON to the List of objects
            gsonConvert = new Gson().fromJson(inputJSON, new TypeToken<ArrayList<randomRequest>>() {}.getType());
            gridList.clear();
            
            // Replace the ObservableList items with the new GSON data
            for (randomRequest r : gsonConvert)
            {
                gridList.add(new randomRequestProperty(r.getRequestString(), r.getResultString()));
                
            }
        }
    }

    /**
     * Query the server for the results of the requested roll
     *
     * @param rollRequest String Roll request in string format
     */    
    public void restPOST(String rollRequest)
    {
        try
        {
            //System.out.println(rollRequest);
            // Setup the HTTP request, URI is the path to the API, POST sets up
            // a POST request with the Request string in the body, and build
            // creates the request
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://"+serverinfo.SERVER_ADDRESS+":"+serverinfo.SERVER_PORT+"/netrandom/api/random"))
                    .POST(BodyPublishers.ofString(rollRequest))
                    .build();
            
            // Create an HTTP client, then send out the request
            HttpClient getClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = getClient.send(getRequest, BodyHandlers.ofString());

            //System.out.println(getResponse.body());
        }
        catch (URISyntaxException | IOException | InterruptedException e)
        {
            System.out.println("Error retrieving GET");
        }
        
    }
    
    /**
     * Request that the server clear the history data
     *
     */    
    public void restDELETE()
    {
        try
        {
            // Setup the HTTP request, URI is the path to the API, DELETE sets up
            // a DELETE, and build creates the request
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://"+serverinfo.SERVER_ADDRESS+":"+serverinfo.SERVER_PORT+"/netrandom/api/random"))
                    .DELETE()
                    .build();
            
            // Create an HTTP client, then send out the request
            HttpClient getClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = getClient.send(getRequest, BodyHandlers.ofString());

            //System.out.println(getResponse.body());
        }
        catch (URISyntaxException | IOException | InterruptedException e)
        {
            System.out.println("Error doing DELETE");
        }
        
    }
    
    /**
     * Button FXML hook for quick roll
     *
     */
    @FXML
    private void cmdRollD4() throws IOException
    {
        restPOST("1 D 4");
        restGET();
        
    }

    /**
     * Button FXML hook for quick roll
     *
     */    
    @FXML
    private void cmdRollD6() throws IOException
    {
        restPOST("1 D 6");
        restGET();
        
    }
    
    /**
     * Button FXML hook for quick roll
     *
     */    
    @FXML
    private void cmdRollD8() throws IOException
    {
        restPOST("1 D 8");
        restGET();
        
    }
    
    /**
     * Button FXML hook for quick roll
     *
     */    
    @FXML
    private void cmdRollD10() throws IOException
    {
        restPOST("1 D 10");
        restGET();
        
    }
    
    /**
     * Button FXML hook for quick roll
     *
     */
    @FXML
    private void cmdRollD12() throws IOException
    {
        restPOST("1 D 12");
        restGET();
        
    }
    
    /**
     * Button FXML hook for quick roll
     *
     */
    @FXML
    private void cmdRollD20() throws IOException
    {
        restPOST("1 D 20");
        restGET();
        
    }
    
    /**
     * Button FXML hook for user generated roll
     *
     */
    @FXML
    private void cmdRoll() throws IOException
    {
        restPOST(spnQuantity.getValue() + " " + cboDie.getValue());
        restGET();
        
    }
    
    /**
     * Button FXML hook for sending th clear command
     *
     */
    @FXML
    private void cmdClear() throws IOException
    {
        restDELETE();
        restGET();
        
    }
    
    /**
     * Initialize the javafx components loaded via FXML
     *
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        // Instantiate the Observable list used by the Grid
        gridList = FXCollections.observableArrayList();
        
        // Set the cell factories of the columns to read the properties
        colResponse.setCellValueFactory(new PropertyValueFactory<>("resultString"));
        colRequest.setCellValueFactory(new PropertyValueFactory<>("requestString"));
        
        // Initial GET call to populate the list
        restGET();
        
        // Set the initial List items to feed from gridList, since it is being observed
        // only need to update gridList for changes to be added
        tblResponses.setItems(gridList);
        
        // Set the limits for the Spinner, Minimum: 1, Maximum: 20, Step: 1, Initial Value: 1
        IntegerSpinnerValueFactory intFact = new IntegerSpinnerValueFactory(1, 20, 1, 1);
        spnQuantity.setValueFactory(intFact);
        
    }
}
