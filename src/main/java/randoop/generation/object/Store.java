package randoop.generation.object;

import java.util.HashMap;

public class Store {

    HashMap<String, Object> hashToObj = new HashMap<>();

    public boolean add(Object object) {
        String hash = Hash.calculateHash(object);
        if (hashToObj.containsKey(hash))
            return false;
        hashToObj.put(hash, object);
        System.out.println("Added object with hash: " + hash);
        return true;
    }
}
