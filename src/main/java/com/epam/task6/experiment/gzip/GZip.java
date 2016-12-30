package com.epam.task6.experiment.gzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZip {
    private static String OUTPUT_FILE = "file.gzip";
    private static String INPUT_FILE = "shopping.order";

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {

            GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(OUTPUT_FILE));

            FileInputStream in = new FileInputStream(INPUT_FILE);

            int len;
            while ((len = in.read(buffer)) > 0) {
                gzos.write(buffer, 0, len);
            }

            in.close();
            gzos.finish();
            gzos.close();

            System.out.println("Saved");
            File sfile = new File(INPUT_FILE);
            File ofile = new File(OUTPUT_FILE);
            System.out.println("Source size = " + sfile.length());
            System.out.println("Output size = " + ofile.length());

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
