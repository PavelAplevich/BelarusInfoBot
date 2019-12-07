package logics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetToken {
    final private static File file = new File("/Users/dariaapril/Desktop/НЕ УДАЛЯТЬ! .txt");

    public static String getBotToken() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(fileInputStream);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public static String getWeatherToken() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(fileInputStream);
        while (!scanner.nextLine().equals("Weather")) {
            scanner.nextLine();
        }
        return scanner.nextLine();
    }
}
