package object;

import java.util.HashMap;

public class Store {

    //HashMap<Integer, Object> hashToObj = new HashMap<>();
    HashMap<String, Object> hashToObj = new HashMap<>();

    public boolean add(Object object) {
        String hash = Hash.calculateHash(object);
        //Integer hashCode = hash.hashCode();
        if (hashToObj.containsKey(hash))
            return false;
        hashToObj.put(hash, object);
        System.out.println("Added object with hash: " + hash);
        return true;
    }
}
