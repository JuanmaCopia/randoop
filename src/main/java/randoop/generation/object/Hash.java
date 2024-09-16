package object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Hash {

    private static long id = 0;

    private static String createUniqueObjectID(Object object) {
        return object.getClass().getSimpleName() + id++;
    }

    public static String calculateHash(Object object) {
        id = 0;
        List<String> hashList = new ArrayList<>();
        calculateHash(object, new IdentityHashMap<>(), hashList);
        return String.join("", hashList);
    }

    private static void calculateHash(Object object, Map<Object, String> objToHash, List<String> hashList) {
        if (object == null) {
            hashList.add("null");
        } else if (TypeUtils.isPrimitiveWrapper(object)) {
            hashList.add(getPrimitiveHash(object));
            //hashList.add("Primitive");
        } else if (objToHash.containsKey(object)) {
            // Already hashed
            hashList.add(objToHash.get(object));
        } else {
            // Is a Reference Type object not hashed yet
            String objectID = createUniqueObjectID(object);
            objToHash.put(object, objectID);
            hashList.add(objectID);

            if (object.getClass().isArray()) {
                hashArray(object, objToHash, hashList);
            } else {
                hashObject(object, objToHash, hashList);
            }

        }
    }

    private static void hashArray(Object object, Map<Object, String> objToHash, List<String> hashList) {
        if (TypeUtils.isArrayOfPrimitiveType(object.getClass())) {
            hashList.add(getPrimitiveHash(object));
            //hashList.add("Primitive");
        } else { // is Array of Reference Type
            Object[] array = (Object[]) object;
            for (Object element : array) {
                calculateHash(element, objToHash, hashList);
            }
        }
    }

    private static void hashObject(Object object, Map<Object, String> objToHash, List<String> hashList) {
        List<Field> accessibleFields = ReflectionUtils.getAccessibleFields(object);
        for (Field field : accessibleFields) {
            calculateHash(ReflectionUtils.getFieldValue(object, field), objToHash, hashList);
        }
    }

    private static String getPrimitiveHash(Object object) {
        return object.toString();
        //return object.getClass().getSimpleName();
    }

}
