package com.epam.task5.filters;

import com.epam.task5.exeptions.DirectoryNotFoundException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/***
 * Class provides method, that starts interacting with readers.
 * Controls communication with readers and program.
 *
 * */
public class FileWalker {

    public void start(List<Filter> incList, FilterContainer filterContainer, ParamsContainer paramsContainer) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String directory = null;

        System.out.println("Enter path to folder:");
        if (scanner.hasNext()) directory = scanner.next();
        checkIfDir(directory);

        List<Filter> list = incList;

        for (Filter filter : list) {
            System.out.println("> искать " + filter.getMessage() + " файла ? (0\\1)");
            todo(filterContainer, scanner, paramsContainer, filter);
        }

        Stream<File> stream = Files.walk(new File(directory).toPath()).map(Path::toFile);
        Stream<File> resultStream = filterContainer.execute(stream);
        resultStream.forEach(file -> System.out.println(file.getName()));
    }


    private void todo(FilterContainer filterContainer, Scanner scanner, ParamsContainer paramsContainer, Filter filterToExecute) {
        if (scanner.hasNextInt() && scanner.nextInt() == 1) {
            List<String> list = filterToExecute.getParams();
            requestAndSafeParams(paramsContainer, scanner, list);
            filterContainer.put(filterToExecute);
        }
    }


    public void requestAndSafeParams(ParamsContainer paramsContainer, Scanner scanner, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Enter " + list.get(i));
            if (scanner.hasNext()) {
                paramsContainer.addParam(list.get(i), scanner.next());
            }
        }
    }

    public void checkIfDir(String file) throws DirectoryNotFoundException {
        if (!new File(file).isDirectory()) {
            throw new DirectoryNotFoundException();
        }
    }

}
