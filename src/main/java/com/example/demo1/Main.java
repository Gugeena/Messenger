package com.example.demo1;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("temp");

        String contextPath = "/messanger";
        String docBase = new File("src/main/webapp").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, docBase);

        Tomcat.addServlet(context, "MessageServlet", new MessegeServlet());
        Tomcat.addServlet(context, "UserServlet", new UserServlet());
        Tomcat.addServlet(context, "default", "org.apache.catalina.servlets.DefaultServlet");

        context.addServletMappingDecoded("/message", "MessageServlet");
        context.addServletMappingDecoded("/user", "UserServlet");
        context.addServletMappingDecoded("/", "default");

        try
        {
            tomcat.start();
            tomcat.getConnector();
            tomcat.getServer().await();
        }
        catch (LifecycleException e) {
            throw new RuntimeException(e);
        }

    }
}
