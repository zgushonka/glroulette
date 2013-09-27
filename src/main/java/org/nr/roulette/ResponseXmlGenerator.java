package org.nr.roulette;

import talk.BetResponse;
import talk.RegisterResponse;

public class ResponseXmlGenerator {

    public static String generate(RegisterResponse regResp) {
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><server session=\"\" command=\"register\"  status=\"";
        if (regResp.getAnswer().equals("OK"))
            { response += "OK\"><user id=\"" + regResp.getUserid() + "\" />"; }
        else
            { response += "Fail\"><reason>" + regResp.getAnswer() + "</reason>"; }
        response += "</server>";
        return response;
    }

    public static String generate(BetResponse betResp) {
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><server session=\"\" command=\"bet\"  status=\"";
        if (betResp.getAnswer().equals("OK"))
        { 
            response += "OK\"><bet stake=\"" + betResp.getStake() 
                    + "\" number=\"" + betResp.getNumber() 
                    + "\"  type=\"" + betResp.getBetType() + "\" />";
        }
    else
        { 
            response += "error\"><bet stake=\"" + betResp.getStake() 
                    + "\" number=\"" + betResp.getNumber() 
                    + "\"  type=\"" + betResp.getBetType() + "\" />";
            response += "<error>" + betResp.getAnswer() + "</error>";
        }
        response += "</server>";
        return response;
    }

}
