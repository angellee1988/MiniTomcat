package org.lee.tomcat;

import org.lee.tomcat.request.MyRequest;
import org.lee.tomcat.response.MyResponse;
import org.lee.tomcat.servlet.MyServlet;
import org.lee.tomcat.servlet.ServletMapping;
import org.lee.tomcat.servlet.ServletMappingConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Bootstrap {
    private Integer port = 8080;

    private Map<String, String> urlServletMap = new ConcurrentHashMap<>();

    public Bootstrap() {

    }
    public Bootstrap(Integer port) {
        this.port = port;
    }

    /**
     * 启动 MiniTomcat
     */
    public void start() {

        // 初始化url和Servlet
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("mini tomcat server started in port:" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                MyRequest request = new MyRequest(inputStream);
                MyResponse response = new MyResponse(outputStream);

                // 请求分发
                dispatch(request, response);
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化servletMapping
     */
    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    /**
     * 请求分发
     *
     * @param request
     * @param response
     */
    private void dispatch(MyRequest request, MyResponse response) {
        String clazz = urlServletMap.get(request.getUrl());
        // 浏览器默认会有 /favicon.ico 请求
        if(Objects.isNull(clazz)) {
            return;
        }
        try {
            // 反射
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Bootstrap(8080).start();
    }


}
