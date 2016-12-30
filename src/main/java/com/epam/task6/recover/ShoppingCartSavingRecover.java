package com.epam.task6.recover;

import com.epam.task4.dao.Storage;

import java.io.*;
import java.util.LinkedHashMap;

public class ShoppingCartSavingRecover {
    private static final String FILE_NAME = "shopping.order";

    public void save(Storage storage) throws IOException {
        LinkedHashMap<String, Integer> stor = storage.getShoppingCart();
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(stor);
        oos.flush();
        oos.close();
    }

    public LinkedHashMap<String, Integer> load(Storage storage) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        ObjectInputStream oin = new ObjectInputStream(fis);
        LinkedHashMap<String, Integer> ts = (LinkedHashMap<String, Integer>) oin.readObject();
        storage.setShoppingCart(ts);
        return ts;
    }
}
