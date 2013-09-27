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
        
//        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//        		"<client session=\"V0ja3a2B7ZtklPqb\" command=\"register\"> " + 
//        		"<user name=\"Some_username_here\"/> " + 
//        		"<password value=\"password\" />  " + 
//        		"<password confirmation=\"password\" />" + 
//        		"</client>");
        
        
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
        		"            <client session=\"V0ja3a2B7ZtklPqb\" user_id=\"id\" password=\"password\" command=\"bet\">" + 
        		"            <bet type=\"ParityBet\" value=\"36\" coins=\"10\" />" + 
        		"            </client>");
//        
//        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><client session=\"V0ja3a2B7ZtklPqb\" uname=\"username\" password=\"password\" command=\"spin\" />");
//        
//        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//        		"<client session=\"V0ja3a2B7ZtklPqb\" " +
//        		"uname=\"username\" " +
//        		"password=\"password\" " +
//        		"command=\"get stats\" />");
//        
//        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
//        		"        <client session=\"V0ja3a2B7ZtklPqb\" password=\"password\" command=\"bet\">\r\n" + 
//        		"        <bet type=\"number\" number=\"42\" coins=\"42\"/>\r\n" + 
//        		"        </client>");
//        
//        
        
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();        
        
        
        
    }
}
