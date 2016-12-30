package com.epam.task9;

import java.io.*;
import java.net.Socket;

public class Client {
    private String host;
    private int port;
    private static final String ENCODING = "UTF-8";

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void makeRequest() {
        new Thread(() -> {
            while (true) {
                try (Socket client = new Socket(host, port)) {
                    OutputStream outToServer = client.getOutputStream();
                    InputStream inFromServer = client.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    DataOutputStream out = new DataOutputStream(outToServer);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inFromServer, ENCODING));

                    System.out.println("\nPrint request:");
                    String line = br.readLine();
                    out.writeBytes(line + '\n');
                    out.flush();
                    System.out.println(in.readLine());
                } catch (IOException e) {
                    System.err.println("Client error " + e);
                }

            }
        }).start();
    }
}
