package com.epam.task6.experiment.write.object.loop;

import com.epam.task4.dao.Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class WriteObjectLoop {
    public static final String FILE_NAME = "test.storage";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        System.out.println("Enter number of repeats:");
        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
        }

        LinkedHashMap<String, Integer> stor = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            stor.put("" + i, i);
        }
        Storage storage = new Storage(new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                new LinkedHashMap<>(),
                new TreeMap<>());
        LinkedHashMap<String, Integer> myStor = storage.getShoppingCart();

        for (int i = 0; i < number; i++) {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myStor);
            oos.flush();
            oos.close();
            File file = new File(FILE_NAME);
            System.out.println(file.length());
        }
    }
}
