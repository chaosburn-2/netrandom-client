package com.chaostek.netrandomclient;

/**
 * Class to hold request values and generated number
 * For use with the GSON converted data
 * 
 * @author Chris Carson
 */
public class randomRequest
{
    public String requestString;
    public String resultString;

    randomRequest()
    {
        
    }
    
    randomRequest(String requestString, String resultString)
    {
        this.requestString = requestString;
        this.resultString = resultString;
        
    }
    
    public String getRequestString() { return requestString; }

    public void setRequestString(String requestString) { this.requestString = requestString; }

    public String getResultString() { return resultString; }

    public void setResultString(String resultString) { this.resultString = resultString; }
    
}
