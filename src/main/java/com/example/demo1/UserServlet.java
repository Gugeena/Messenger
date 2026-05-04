package com.example.demo1;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserServlet extends HttpServlet
{
    ObjectMapper objMap = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        HTMLWriter.writeHTML(PageType.USER, res.getWriter());
        res.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        User user = objMap.readValue(req.getInputStream(), User.class);

        try
        {
            DBService.getDBService().registerUser(user.getUser(), user.getPassword());
            res.setStatus(HttpServletResponse.SC_OK);
        }
        catch(UserAlreadyFoundException e)
        {
            System.out.println(e);
            res.sendError(403);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
