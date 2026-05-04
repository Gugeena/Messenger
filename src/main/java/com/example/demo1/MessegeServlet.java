package com.example.demo1;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MessegeServlet extends HttpServlet
{
    ObjectMapper objMap = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            String username = req.getParameter("username");
            if(username == null)
            {
                res.setContentType("text/html");
                HTMLWriter.writeHTML(PageType.MAIL, res.getWriter());
                res.setStatus(HttpServletResponse.SC_OK);
                return;
            }

            String password = req.getParameter("password");

            User user = new User(username,password);
            List<String> messages = DBService.getDBService().getMessages(user.getUser(), user.getPassword());
            String jsonResponse = objMap.writeValueAsString(messages);
            res.setContentType("application/json");
            res.getWriter().write(jsonResponse);
            res.setStatus(HttpServletResponse.SC_OK);
        }
        catch (DatabindException e) {throw new RuntimeException(e);}
        catch (StreamWriteException e) {throw new RuntimeException(e);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            MMJsonToMail mmJsonToMail = objMap.readValue(req.getInputStream(), MMJsonToMail.class);
            String message = mmJsonToMail.getMessage();
            String username = mmJsonToMail.getUsername();
            DBService.getDBService().sendMessage(new Mails(username, message, DBService.getDBService().getUser(username)));
            res.setStatus(HttpServletResponse.SC_OK);
        }
        catch (DatabindException e) {throw new RuntimeException(e);}
        catch (StreamReadException e) {throw new RuntimeException(e);}
        catch (IOException e) {throw new RuntimeException(e);}
    }
}
