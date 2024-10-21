package randoop.generation.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {

    public static Object getFieldValue(Object owner, Field field) {
        Object currentValue = null;
        try {
            field.setAccessible(true);
            currentValue = field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return currentValue;
    }

    public static List<Field> getAccessibleFields(Object obj) {
        List<Field> allFields = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (canGetFieldValue(obj, field, clazz))
                    allFields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return allFields;
    }

    public static boolean canGetFieldValue(Object object, Field field, Class<?> clazz) {
        try {
            field.setAccessible(true);
            field.get(object);
            return true;
        } catch (Exception e) {
            //System.out.println("WARNING: Could not access field: " + field.getName() + " of class: " + clazz.getName());
            return false;
        }
    }

}
