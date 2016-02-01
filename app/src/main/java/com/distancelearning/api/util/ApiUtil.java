package com.distancelearning.api.util;

import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by v.odahovskiy on 01.02.2016.
 */
public class ApiUtil {


    @Nullable
    public static Map<String, String> explodeQueryString(@Nullable String queryString) {
        if (queryString == null) {
            return null;
        }
        String[] keyValuePairs = queryString.split("&");
        HashMap<String, String> parameters = new HashMap<>(keyValuePairs.length);

        for (String keyValueString : keyValuePairs) {
            String[] keyValueArray = keyValueString.split("=");
            parameters.put(keyValueArray[0], keyValueArray[1]);
        }
        return parameters;
    }

    /**
     * Reads content of file, and returns result as string
     *
     * @param filename path to file
     * @return Contents of file
     * @throws IOException
     */
    public static String fileToString(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder builder = new StringBuilder();
        String line;

        // For every line in the file, append it to the string builder
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();

        return builder.toString();
    }

    /**
     * Saves passed string to file
     *
     * @param filename      path to file
     * @param stringToWrite string to save
     */
    public static void stringToFile(String filename, String stringToWrite) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(stringToWrite);
            writer.flush();
            writer.close();
        } catch (Exception ignored) {
        }
    }

}
