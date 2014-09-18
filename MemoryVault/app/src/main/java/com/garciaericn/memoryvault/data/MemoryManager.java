package com.garciaericn.memoryvault.data;

import java.util.HashMap;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 9/17/14.
 */
public class MemoryManager {

    public HashMap <Long, Memory> memories;

    // Initializes memories HashMap if null
    public MemoryManager() {
        if (memories == null) {
            memories = new HashMap<Long, Memory>();
        }
    }

    // Adds given memeory
    public void addMemory(Memory memory) {
        memories.put(memory.getMemoryKey(), memory);
    }

    // Removes a given memory
    public void removeMemory(Memory memory) {
        memories.remove(memory.getMemoryKey());
    }

    public void saveToDisk() {
        // TODO: Add save code
    }
}
