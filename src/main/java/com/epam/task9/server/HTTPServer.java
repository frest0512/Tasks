package com.epam.task9.server;

import com.epam.task9.command.base.Command;
import com.epam.task9.command.base.CommandContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer implements Runnable {
    private ServerSocket serverSocket;
    private CommandContainer container;
    private static final String ENCODING = "UTF-8";

    public HTTPServer(CommandContainer container, int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.container = container;
    }

    @Override
    public void run() {
        System.out.println("HTTPServer is run:");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                String url = getURL(socket.getInputStream());
                if (url != null && !url.endsWith(".ico")) {
                    String key = url.split("\\?")[0];
                    Command command = container.getCommand(key);
                    System.out.println("command is " + key + " class = " + command.getClass().getSimpleName());
                    command.execute(Servers.HTTP, url, socket);

                } else {
                    System.out.println("ICON");
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("HTTPServer error " + e);
            }
        }
    }

    private String getURL(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, ENCODING));
        String fullPath = bufferedReader.readLine();
        if (fullPath == null || fullPath.split(" ").length < 1) {
            return fullPath;
        }
        return fullPath.split(" ")[1];
    }
}
