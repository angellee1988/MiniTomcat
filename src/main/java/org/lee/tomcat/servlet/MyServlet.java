package org.lee.tomcat.servlet;

import org.lee.tomcat.request.MyRequest;
import org.lee.tomcat.response.MyResponse;

import java.util.Objects;

public abstract class MyServlet {

    public abstract void doGet(MyRequest request, MyResponse response) ;
    public abstract void doPost(MyRequest request, MyResponse response) ;

    public void service(MyRequest request, MyResponse response) {

        if(Objects.equals(request.getMethod(), "POST")) {
            doPost(request, response);
        } else if(Objects.equals(request.getMethod(), "GET")) {
            doGet(request, response);
        }

    }
}
