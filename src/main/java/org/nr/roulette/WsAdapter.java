package org.nr.roulette;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import talk.BetRequest;
import talk.BetResponse;
import talk.ExceptionResponse;
import talk.RegisterRequest;
import talk.RegisterResponse;
import talk.Request;
import talk.Response;
import talk.SpinRequest;
import talk.SpinResponse;
import casino.Croupie;
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
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response = new ExceptionResponse("", "", e.getClass().getName(), e.getMessage());
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
        return Croupie.newInstance().getStats();
    }    
    
    @GET
    @Path("/statsxml")
    @Produces("text/xml")
    public String getStatsXml()
    {
        return Croupie.newInstance().getStatsXml();
    }    
    
}
