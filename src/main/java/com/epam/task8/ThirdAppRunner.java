package com.epam.task8;

import java.io.File;
import java.util.Scanner;

public class ThirdAppRunner {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = null;
        if (scanner.hasNext()) {
            fileName = scanner.next();
        }
        File file = new File(fileName);
        if (!file.exists() || file.isFile()) {
            System.out.println("Wrong fileName");
            return;
        }

    }
}
