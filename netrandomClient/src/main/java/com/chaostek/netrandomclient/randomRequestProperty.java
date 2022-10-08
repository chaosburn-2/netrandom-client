package com.chaostek.netrandomclient;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class to hold request values and generated number
 * For use in an ObservableList, so data needs to be properties
 * 
 * @author Chris Carson
 */
public class randomRequestProperty
{
    public SimpleStringProperty requestString;
    public SimpleStringProperty resultString;

    randomRequestProperty()
    {
        
    }
    
    randomRequestProperty(String requestString, String resultString)
    {
        this.requestString = new SimpleStringProperty(requestString);
        this.resultString = new SimpleStringProperty(resultString);
        
    }
    
    public String getRequestString() { return requestString.get(); }

    public void setRequestString(String requestString) { this.requestString.set(requestString); }

    public String getResultString() { return resultString.get(); }

    public void setResultString(String resultString) { this.resultString.set(resultString); }
    
}
