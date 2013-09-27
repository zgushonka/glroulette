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
import casino.InDoor;

@Path("/")
public class WsAdapter {
    /**
     * The method performs requests routing and corresponding responses
     * generation
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
                //RegisterResponse regResp = (RegisterResponse) casino.RequestProcessorStub.processRegisterRequest((RegisterRequest) request);
                RegisterResponse regResp = (RegisterResponse) InDoor.processRegisterRequest((RegisterRequest) request);
                response = ResponseXmlGenerator.generate(regResp); 
            }
            else if (request  instanceof BetRequest)
            {
                //BetResponse betResp = (BetResponse) casino.RequestProcessorStub.processBetRequest((BetRequest) request);
                BetResponse betResp = (BetResponse) InDoor.processBetRequest((BetRequest) request);                
                response = ResponseXmlGenerator.generate(betResp); 
//            else if (request  instanceof GetStatsRequest)
//                casino.InDoor.takeObject((GetStatsRequest) request);
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
