package org.nr.roulette;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;



public class RouletteClient {

    public static void main(String[] args) throws IOException
    {
        URL url = new URL("http://localhost:9999/casino/");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(
            httpCon.getOutputStream());
        

        String innerPart = "";
        
//Register
//innerPart = "<client command=\"register\"> <user name=\"Some_username_here\"/> <password value=\"password\" />"; 
//Bet         
innerPart = "<client user_id=\"de35c8dd-317e-49de-acc0-3e8248a40690\" password=\"password\" command=\"bet\"><bet type=\"StrightBet\" value=\"36\" coins=\"10\" />";
//Spin
//innerPart = "<client user_id=\"5ad4ec71-78c1-4644-bf17-6eba319e46d8\" name=\"Some_username_here\" password=\"password\" command=\"spin\" >";        

//        
//        
//        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
//        		"        <client session=\"V0ja3a2B7ZtklPqb\" password=\"password\" command=\"bet\">" + 
//        		"        <bet type=\"number\" number=\"42\" coins=\"42\"/>" + 
//        		"        </client>");

        String theRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+innerPart + "</client>";
        System.out.println(theRequest);
        out.write(theRequest);
                
        
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();        
        
        
        
    }
}
