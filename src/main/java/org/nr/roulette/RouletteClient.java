package org.nr.roulette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class RouletteClient {

    public static URL url;
    public static HttpURLConnection httpCon;
    
    private String id = "";
    private String password = "";
    private String username = "";
    private OutputStreamWriter out;
    private Document doc;
    
    
    
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    RouletteClient(String definedUsername, String definedPassword) {
        this.username = definedUsername;
        this.password = definedPassword;
    }

    public void sendRequest(String theRequest) throws IOException, ParserConfigurationException, SAXException
    {
        out.write(theRequest);
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String inputLine;
        String resXml = "";
        
        while ((inputLine = in.readLine()) != null)
            resXml += inputLine;
        in.close();  
        
        System.out.println(resXml);
        // getting user id
        this.doc = RequestFactory.parseXml(resXml);
        this.id = doc.getElementsByTagName("userid").item(0).getTextContent();
    }

    public void connect(String host, String port) throws IOException {
        String baseUrl = "http://localhost:9999/casino/";
//TODO: fix below
        //        if (host != null && !host.isEmpty()) {
//            baseUrl.replaceFirst("localhost", host);
//        }

//        if (port != null & !port.isEmpty()) {
//            baseUrl.replaceFirst("9999", port);
//        }

        url = new URL(baseUrl);
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        out = new OutputStreamWriter(httpCon.getOutputStream());        
    }

    public void disconnect() {
        try {
            httpCon.disconnect();
        } catch (Throwable ignore) {
        }

        httpCon = null;

    }
    
    
    public void register() throws IOException, ParserConfigurationException, SAXException
    {
        String theRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
                "<client command=\"register\"> <user name=\""+ 
                this.username + 
                "\"/> <password value=\"" + 
                this.password + "\" />"
                + "</client>";
        
        sendRequest(theRequest);
        this.id = doc.getElementsByTagName("userid").item(0).getTextContent();
    }
    
    
    public void bet(String betType, int value, int coins) throws IOException, ParserConfigurationException, SAXException
    {
        String theRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
                "<client user_id=\"" + this.id + "\" password=\"" + this.password + "\" command=\"bet\">" +
                "<bet type=\""+ betType + "\" value=\""+ value +"\" coins=\""+ coins +"\" />" +
                "</client>";
        
        sendRequest(theRequest);
        this.id = doc.getElementsByTagName("userid").item(0).getTextContent();
    }    

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
        RouletteClient client = new RouletteClient("Mr. Gambler", "OLOLO000Hx");
        client.connect(null, null);
        client.register();
        System.out.println(client.getId());
        client.disconnect();
        
        for (int i = 0; i < 100; i++) {
            client.connect(null, null);
            client.bet("ColourBet", 23, 10);
            client.disconnect();
            Thread.sleep(5000);
        }
        
        
        
        
                

        
        

        

        // Register
        //innerPart = "<client command=\"register\"> <user name=\"Some_username_here\"/> <password value=\"password\" />"; 

  // Bet
        //innerPart = "<client user_id=\"4222517c-434f-4652-8aaf-8a7aab60e00d\" password=\"password\" command=\"bet\"><bet type=\"StrightBet\" value=\"23\" coins=\"10\" />" ;
  
  
  
        // Spin
        // innerPart =
        // "<client user_id=\"f4fbccca-112a-4b27-ab7b-74caef004d79\" name=\"Some_username_here\" password=\"password\" command=\"spin\" >";

        //
        //
        // out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        // "        <client session=\"V0ja3a2B7ZtklPqb\" password=\"password\" command=\"bet\">"
        // +
        // "        <bet type=\"number\" number=\"42\" coins=\"42\"/>" +
        // "        </client>");

        //String theRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + innerPart + "</client>";
        //System.out.println(theRequest);
        //out.write(theRequest);

        //out.close();
        //BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        //String inputLine;
        //while ((inputLine = in.readLine()) != null)
            //System.out.println(inputLine);
        //in.close();

    }
}
