package org.nr.roulette;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.nr.roulette.exceptions.ValidationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import talk.BetRequest;
import talk.RegisterRequest;
import talk.Request;
import talk.SpinRequest;

public class RequestFactory {
    
    private final static String tableType  = "normal";
    
    public static Document parseRequestXml(String xml) throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
            doc.getDocumentElement().normalize();            
        } catch (Exception e) {
        }

        return doc;
    }

    private static String getRequestType(Document doc) {
        return doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("command").getTextContent();
    }

    private static Request createSpinRequest(Document doc) {
        String name = null;
        String newPassword = null;
        String id = null;
        SpinRequest req = null;
        
        //<client name="username" user_id="someid" password="password" command="spin" />
        name = doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("name").getTextContent();
        newPassword = doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("password").getTextContent();
        id = doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("user_id").getTextContent();
        
        try {
            req = new SpinRequest(id, name, newPassword);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return req;
    }    
    
    
    private static RegisterRequest createRegisterRequest(Document doc) {
        String name = null;
        String newPassword = null;
        RegisterRequest req = null;
        try {
            /*      
            <client command="register">
            <user name="Some_username_here" />
            <password value="password" />
            </client>
            */
            name = doc.getElementsByTagName("user").item(0).getAttributes().getNamedItem("name").getTextContent();
            newPassword = doc.getElementsByTagName("password").item(0).getAttributes().getNamedItem("value").getTextContent();
            req = new RegisterRequest(name, newPassword);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return req;
    }

/*	private static GetStatsRequest createGetStatsRequest(Document doc) {
        RegisterRequest req = null;
        return req;
    }*/
    
    private static BetRequest createBetRequest(Document doc) {
        String id = null;
        String password = null;
        String betType = null;
        String value = null;
        String coins = null;
        
        
        BetRequest req = null;
        try {
            /*      
            <?xml version="1.0" encoding="UTF-8"?>
            <client session="V0ja3a2B7ZtklPqb" user_id="id" password="password" command="bet">
            <bet type="ParityBet" value="36" coins="10" />
            </client>
            */
            id = doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("user_id").getTextContent();
            password = doc.getElementsByTagName("client").item(0).getAttributes().getNamedItem("password").getTextContent();
            betType = doc.getElementsByTagName("bet").item(0).getAttributes().getNamedItem("type").getTextContent();
            value = doc.getElementsByTagName("bet").item(0).getAttributes().getNamedItem("value").getTextContent();
            coins = doc.getElementsByTagName("bet").item(0).getAttributes().getNamedItem("coins").getTextContent();
            
            req = new BetRequest(id, password, tableType, betType, Integer.valueOf(coins), Integer.valueOf(value));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return req;
    }

    public static Request createRequest(String xmlRequest) throws ParserConfigurationException, SAXException, IOException, ValidationException {
         
        Document doc = RequestFactory.parseRequestXml(xmlRequest);
        if (doc == null) {
            throw new SAXException("Non valid request XML");
        }

        Request req = null;
        String rqType = getRequestType(doc);

        if (rqType.equals("register")) {
            req = createRegisterRequest(doc);
        } else if (rqType.equals("bet")) {
            req = createBetRequest(doc);
        } 
        else if (rqType.equals("spin")) {
            System.out.println("Creating spin request");
            req = createSpinRequest(doc);
        } 
        else {
            throw new ValidationException("The request type is not one of {register, bet, spin}");

        }

        return req;
    }




}