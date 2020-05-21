package org.lee.tomcat;

import org.lee.tomcat.request.MyRequest;
import org.lee.tomcat.response.MyResponse;
import org.lee.tomcat.servlet.MyServlet;

import java.io.IOException;

public class HelloServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("[Get]hello world");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("[Post]hello world");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
