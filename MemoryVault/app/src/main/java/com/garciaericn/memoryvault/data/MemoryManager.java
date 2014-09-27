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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class MemoryManager {

    private static final String TAG = "MemoryManager.TAG";
    private static final String FILENAME = "Memories";
    private static Context mContext;
    private static MemoryManager mgr;
    private static List<Memory> memoriesList;

    public static HashMap <String, Memory> memories;

//    public class testSingleton {
//        private static testSingleton ourInstance = new testSingleton();
//
//        public static testSingleton getInstance() {
//            return ourInstance;
//        }
//
//        private testSingleton() {
//        }
//    }


    // Initializes memories HashMap if null
    public MemoryManager() {
        Log.i(TAG, "MemoryManager Created");
    }

    public static MemoryManager newInstance(Context context) {
        Log.i(TAG, "newInstance entered");

        if (mgr == null) {
            mgr = new MemoryManager();
        }

        mContext = context;

        if (checkFile(context, FILENAME)) {
            readFromDisk(FILENAME);
        } else {
            if (memories == null) {
                memories = new HashMap<String, Memory>();
            }
        }

        return mgr;
    }

    public HashMap<String, Memory> getMemoryMap() {
        return memories;
    }

    public Memory getMemory(String key) {
        readFromDisk(FILENAME);
        return memories.get(key);
    }

    // Adds given memory
    public static void addMemory(Memory memory) {
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

    private static Boolean checkFile(Context context, String fileName) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, fileName);
        return file.exists();
    }

    public static void writeToDisk(HashMap<String, Memory> memories) {
        Log.i(TAG, "writeToDisk entered");

        File external = mContext.getExternalFilesDir(null);
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

    private static void readFromDisk(String fileName) {
        Log.i(TAG, "readFromFile entered");

        if (checkFile(mContext, FILENAME)) {
            File external = mContext.getExternalFilesDir(null);
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

    public static List<Memory> getMemories(Context context) {
        mContext = context;
        // Read from disk to get most updated info
        readFromDisk(FILENAME);
//        if (memoriesList == null) {
            memoriesList = new ArrayList<Memory>(memories.values());
//        }
//        memoriesList = (List<Memory>) memories.values();
        // Create list of values for return
        return memoriesList;
    }
}
