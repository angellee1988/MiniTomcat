package org.lee.tomcat.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求
 */
public class MyRequest {
    private String url;
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * http协议
     * GET /user/info HTTP/1.1
     * ......
     * Host:xxxx
     * Connection: Keep-alive
     *
     * @param inputStream
     * @throws IOException
     */
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] requestBytes = new byte[2048];
        int length = 0;
        if ((length = inputStream.read(requestBytes)) > 0) {
            httpRequest = new String(requestBytes, 0, length);
        }

        String httpHeader = httpRequest.split("\n")[0];
        method = httpHeader.split("\\s")[0];
        url = httpHeader.split("\\s")[1];
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
