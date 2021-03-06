package com.epam.task9.server;

import com.epam.task9.command.base.Command;
import com.epam.task9.command.base.CommandContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket serverSocket;
    private CommandContainer container;
    private static final String ENCODING = "UTF-8";

    public TCPServer(CommandContainer container, int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.container = container;
    }

    public void startServer() {
        System.out.println("TCPServer is run:");
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    BufferedReader res = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
                    String fullRequest = res.readLine();
                    String key = fullRequest.split("=")[0];
                    Command command = container.getCommand(key);
                    command.execute(Servers.TCP, fullRequest, socket);
                } catch (IOException e) {
                    System.err.println("TCPServer error " + e);
                }
            }

        });
        thread.start();
    }
}
