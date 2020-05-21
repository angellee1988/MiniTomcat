package org.lee.tomcat.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 响应
 */
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * http response
     * HTTP/1.1 200 OK
     * Content-Type: text/html
     *
     * <html><body></body></html>
     *
     * @param content
     */
    public void write(String content) throws IOException {

        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n").append("<html><body>")
                .append(content).append("</body></html>");

        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
