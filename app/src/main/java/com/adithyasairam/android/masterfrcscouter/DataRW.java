package com.adithyasairam.android.masterfrcscouter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Adi on 7/21/2015.
 */
public class DataRW {
    private static HashMap<String, String> map = new HashMap<String, String>();

    public static boolean writeAStringAsFile(String path, String data, String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
        try {
            map.put(key, path);
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getStringFromFile(String key) {
        if (map.containsKey(key)) {
            try {
                String path = map.get(key);
                InputStream in = new FileInputStream(new File(path));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                return out.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
