package com.example.demo1;
import java.io.*;

public class HTMLWriter
{
    public static void writeHTML(PageType type, PrintWriter out)
    {
        try {
            String htmlPageHeader = "user";

            switch (type) {
                case type.MAIL:
                    htmlPageHeader = "mail";
                    break;
            }

            String path = new File("src\\main\\webapp\\" + htmlPageHeader + "Page.html").getAbsolutePath();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                out.println(line);
            }
        }
        catch (FileNotFoundException e) {throw new RuntimeException(e);}
        catch (IOException e) {throw new RuntimeException(e);}
    }
}
