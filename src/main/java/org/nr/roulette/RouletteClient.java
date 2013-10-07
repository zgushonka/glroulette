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
//innerPart = "<client command=\"register\"> <user name=\"Some_username_here\"/> <password value=\"password\" />"; /*
//Bet         
innerPart = "<client user_id=\"989f7f40-d870-4caf-804d-3b27e026bebe\" password=\"password\" command=\"bet\"><bet type=\"StrightBet\" value=\"1\" coins=\"10\" />";
/* */
//Spin
//innerPart = "<client user_id=\"81ae9dcd-92c1-42a6-9436-108125348698\" name=\"Some_username_here\" password=\"password\" command=\"spin\" >";        

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
