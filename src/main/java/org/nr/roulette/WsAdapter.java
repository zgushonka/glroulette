package org.nr.roulette;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;

import org.nr.roulette.exceptions.ValidationException;
import org.xml.sax.SAXException;

import talk.BetRequest;
import talk.BetResponse;
import talk.RegisterRequest;
import talk.RegisterResponse;
import talk.Request;
import talk.Response;
import talk.SpinRequest;
import talk.SpinResponse;
import casino.InDoor;

@Path("/")
public class WsAdapter {
    /**
     * The method performs requests routing and corresponding responses
     * generation
     */
//      This is a manual XML generation first approach. Replaced with marshalling approach
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.APPLICATION_XML)
//    public String put(String xmlRequest) throws UnsupportedEncodingException {
//        String response = "";
//        try {
//            Request request = RequestFactory.createRequest(xmlRequest);
//            if (request  instanceof RegisterRequest)
//            { 
//                RegisterResponse regResp = (RegisterResponse) InDoor.processRegisterRequest((RegisterRequest) request);
//                response = ResponseXmlGenerator.generate(regResp); 
//            }
//            else if (request  instanceof BetRequest)
//            {
//                BetResponse betResp = (BetResponse) InDoor.processBetRequest((BetRequest) request);                
//                response = ResponseXmlGenerator.generate(betResp); 
//            }
//            
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ValidationException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response put(String xmlRequest) throws UnsupportedEncodingException {
        Response response = null;
        try {
            Request request = RequestFactory.createRequest(xmlRequest);
            if (request  instanceof RegisterRequest)
            { 
                RegisterResponse regResp = (RegisterResponse) InDoor.processRegisterRequest((RegisterRequest) request);
                response = regResp; 
            }
            else if (request  instanceof BetRequest)
            {
                BetResponse betResp = (BetResponse) InDoor.processBetRequest((BetRequest) request);                
                response = betResp; 
            }
            else if (request  instanceof SpinRequest)
            {
                SpinResponse spinResp = (SpinResponse) InDoor.processSpinRequest((SpinRequest) request);                
                response = spinResp; 
            }            
            
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return response;
    }    
    
    
    @GET
    @Path("/rules")
    @Produces("text/html")
    public String getRules()
    {
        // TODO: Add generation of a human-readable HTML page with rules here 
        String res = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><title>GL Roulette Rules</title>" +
                "<body><p style ='font-weigh:bold; color:red'><a href=\"https://docs.google.com/a/globallogic.com/document/d/1TYo6RZ7VZYn5hso7gq3dfmX0eTnAt5VvgkPdjaErDsA/edit?usp=drive_web\">Roulette Roules</a></p>";
        res += "</body></html>";
        
        return res;
    }    
    
    
    @GET
    @Path("/stats")
    @Produces("text/html")
    public String getStats()
    {
        // TODO: Add generation of a human-readable HTML page with rules here 
        String res = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>" +
                "<body><table border=\"1\" bordercolor=\"#006600\" style=\"background-color:#FFFFCC\" width=\"100%\" cellpadding=\"3\" cellspacing=\"3\">\n" + 
                "    <tr>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "    </tr>\n" + 
                "    <tr>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "    </tr>\n" + 
                "    <tr>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "    </tr>\n" + 
                "    <tr>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "    </tr>\n" + 
                "    <tr>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "        <td>Table Cell</td>\n" + 
                "    </tr>\n" + 
                "</table>\n" + 
                "\n" + 
                "" +
                "</body></html>";
        
        return res;
    }    
    
}
