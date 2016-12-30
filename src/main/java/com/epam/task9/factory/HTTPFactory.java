package com.epam.task9.factory;

import com.epam.task1.entity.Transport;
import com.epam.task4.services.ProductService;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class HTTPFactory extends AbstractFactory {

    private ProductService service;
    private static final String ENCODING = "UTF-8";

    public HTTPFactory(ProductService service) {
        this.service = service;
    }

    @Override
    public Runnable getCountThread(String string, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                JSONObject jsonObject = new JSONObject();
                int size = service.getCount();
                jsonObject.put("count", size);
                res.append(jsonObject.toString());
                for (int i = 0; i < 50; i++) {
                    System.out.println(i + " === " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                System.err.println("HTTP getCountThread error " + e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public Runnable getProductInfoThread(String string, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                JSONObject jsonObject = new JSONObject();
                String value = string.split("=")[1];
                Transport transport = service.getTransport(value);
                if (transport != null) {
                    jsonObject.put("name", value);
                    jsonObject.put("price", transport.getPrice());
                    res.append(jsonObject.toString());
                } else {
                    res.append("Nothing found");
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + " === " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                System.err.println("HTTP getProductInfoThread error " + e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public Runnable getError(String s, Socket socket) {
        return () -> {
            try (OutputStream out = socket.getOutputStream(); Writer res = new BufferedWriter(new OutputStreamWriter(out, ENCODING))) {
                res.append("Error");
            } catch (IOException e) {
                System.err.println("HTTP getError error " + e);
            }
        };
    }

}
