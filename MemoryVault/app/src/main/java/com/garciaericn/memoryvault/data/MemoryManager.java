package com.garciaericn.memoryvault.data;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.HashMap;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class MemoryManager {

    private static final String TAG = "MemoryManager.TAG";
    private static final String FILENAME = "Memories";
    private Context context;

    public HashMap <String, Memory> memories;


    // Initializes memories HashMap if null
    public MemoryManager() {
        Log.i(TAG, "MemoryManager Created");
    }

    public MemoryManager newInstance(Context context) {
        Log.i(TAG, "newInstance entered");

        MemoryManager mgr = new MemoryManager();
        this.context = context;

        if (checkFile(context, FILENAME)) {
            readFromDisk(FILENAME);
        } else {
            if (memories == null) {
                memories = new HashMap<String, Memory>();
            }
        }

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
        writeToDisk(memories);
    }

    // Removes a given memory
    public void removeMemory(Memory memory) {
        Log.i(TAG, "removeMemory entered");
        memories.remove(memory.getMemoryKey());
        writeToDisk(memories);
    }

    private Boolean checkFile (Context context, String fileName) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);
        return file.exists();
    }

    public void writeToDisk(HashMap<String, Memory> memories) {
        Log.i(TAG, "writeToDisk entered");

        File external = context.getExternalFilesDir(null);
        File file = new File(external, FILENAME);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(memories);
                objectOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void readFromDisk(String fileName) {
        Log.i(TAG, "readFromFile entered");

        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            memories = (HashMap<String, Memory>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
