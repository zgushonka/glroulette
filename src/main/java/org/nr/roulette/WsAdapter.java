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
import talk.RegisterRequest;
import talk.Request;

@Path("/")
public class WsAdapter {
    /**
     * The method performs requests routing and corresponding responses
     * generation
     * 
     * 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String put(String xmlRequest) throws UnsupportedEncodingException {
        String response = "";
        try {
            Request request = RequestFactory.createRequest(xmlRequest);
            if (request  instanceof RegisterRequest)
            { 
                casino.InDoor.processRegisterRequest((RegisterRequest) request); 
            }
            else if (request  instanceof BetRequest)
                casino.InDoor.processBetRequest((BetRequest) request);
//            else if (request  instanceof GetStatsRequest)
//                casino.InDoor.takeObject((GetStatsRequest) request);
            
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
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>" +
                "<body><p style ='font-weigh:bold; color:red'>Rules</p>";
        res += "<ol><li>Первое правило рулетки: Никогда не говорить о рулетке всуе</li>";
        res += "<li>Второе правило рулетки: </li>";
        res += "<li>Третье правило рулетки: </li>";
        res += "</ol></body></html>";
        
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
