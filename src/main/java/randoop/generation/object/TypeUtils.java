package randoop.generation.object;

import java.util.HashSet;
import java.util.Set;

public class TypeUtils {

    private static final Set<Class<?>> BOXED_PRIMITIVES = new HashSet<>();

    static {
        BOXED_PRIMITIVES.add(Boolean.class);
        BOXED_PRIMITIVES.add(Byte.class);
        BOXED_PRIMITIVES.add(Character.class);
        BOXED_PRIMITIVES.add(Short.class);
        BOXED_PRIMITIVES.add(Integer.class);
        BOXED_PRIMITIVES.add(Long.class);
        BOXED_PRIMITIVES.add(Float.class);
        BOXED_PRIMITIVES.add(Double.class);
        BOXED_PRIMITIVES.add(String.class);
    }

    public static boolean isPrimitiveWrapper(Object object) {
        return isPrimitiveWrapper(object.getClass());
        
    }

    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        return BOXED_PRIMITIVES.contains(clazz);
    }

    public static boolean isPrimitiveOrBoxedPrimitive(Class<?> clazz) {
        return clazz.isPrimitive() || isPrimitiveWrapper(clazz);
    }

    public static boolean isArrayOfPrimitiveType(Object object) {
        return isArrayOfPrimitiveType(object.getClass());
    }

    public static boolean isArrayOfPrimitiveType(Class<?> clazz) {
        if (!clazz.isArray())
            return false;

        Class<?> componentType = clazz.getComponentType();
        return TypeUtils.isPrimitiveOrBoxedPrimitive(componentType);
    }

}
