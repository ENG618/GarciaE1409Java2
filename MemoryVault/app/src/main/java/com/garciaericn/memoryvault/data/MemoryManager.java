package com.garciaericn.memoryvault.data;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.HashMap;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class MemoryManager {

    private static final String TAG = "MemoryManager.TAG";
    private Context context;

    public HashMap <String, Memory> memories;


    // Initializes memories HashMap if null
    public MemoryManager() {
        Log.i(TAG, "MemoryManager Created");
    }

    public MemoryManager newInstance(Context context) {
        Log.i(TAG, "newInstance entered");

        MemoryManager mgr = new MemoryManager();
        if (memories == null) {
            memories = new HashMap<String, Memory>();
        }
        this.context = context;
        return mgr;
    }

    public HashMap<String, Memory> getMemories() {
        return memories;
    }

    public Memory getMemory(String key) {
        return memories.get(key);
    }

    // Adds given memory
    public void addMemory(Memory memory) {
        Log.i(TAG, "addMemory entered");
        memories.put(memory.getMemoryKey(), memory);

    }

    // Removes a given memory
    public void removeMemory(Context context, Memory memory) {
        Log.i(TAG, "removeMemory entered");
        memories.remove(memory.getMemoryKey());
//        writeToDisk(context, memory);
    }

    private Boolean checkFile (Context context, String fileName) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);
        return file.exists();
    }

    public void writeToDisk(Context context, HashMap<String, Memory> memories) {
        Log.i(TAG, "writeToDisk entered");
        // TODO: Add save code
//        // Store data in "protected" directory
//        File external = context.getExternalFilesDir(null);
//        File file = new File(external, fileName);
//
//        try {
//            //Create new output stream
//            FileOutputStream fos = new FileOutputStream(file);
//            // Convert string to byte and write to stream
//            fos.write(data.getBytes());
//            // Close the stream to sve the file
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private String readFromDisk(String fileName) {
        Log.i(TAG, "readFromFile entered");
        // TODO: Read data from saved file

//        String savedWeatherString = null;
//
//        File external = getExternalFilesDir(null);
//        File file = new File(external, fileName);
//
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            // Create stream readers
//            InputStreamReader inReader = new InputStreamReader(fis);
//            BufferedReader reader = new BufferedReader(inReader);
//
//            // Read data and pass to StringBuffer
//            StringBuilder buffer = new StringBuilder();
//            String text;
//            // Make sure a line of text is available to be read
//            while ((text = reader.readLine()) != null) {
//                buffer.append(text);
//            }
//            Log.i(TAG, "String from file: " + buffer.toString());
//
//            savedWeatherString = buffer.toString();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return savedWeatherString;
        return null;
    }
}
