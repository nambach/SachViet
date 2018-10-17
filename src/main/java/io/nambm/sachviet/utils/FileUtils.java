package io.nambm.sachviet.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static String EMPTY_CONTENT = "";
    public static String LOCATION = "src/main/resources/static/";

    public static String readTextContent(String filePath) {
        StringBuilder result = new StringBuilder(EMPTY_CONTENT);
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append(" ");
            }
            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result.toString();
        }
    }

    public static String getFilePath(String fileName) {
        return LOCATION + fileName;
    }

    public static boolean exportFile(String src, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(src);
            writer.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getString(InputStream stream) {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ignored) {
        }

        return stringBuilder.toString();
    }
}
