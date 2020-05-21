package org.lee.tomcat.servlet;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("helloworld", "/hello", "org.lee.tomcat.HelloServlet"));
    }
}
