package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionExample {
    public static void privateToPublic() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name:" + clazz.getSimpleName());

        Constructor<DemoClass> constructor = clazz.getConstructor(String.class);
        DemoClass object = constructor.newInstance("testVal");

        System.out.println("-- private method execution:");
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(object);

        System.out.println("--- changing private field:");
        Field field = clazz.getDeclaredField("privateField");
        field.setAccessible(true);
        field.set(object, "privateValueChanged");
        System.out.println("changed value:" + field.get(object));
    }

    public static void methodFeatures() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("class name: " + clazz.getSimpleName());

        Method method = clazz.getMethod("toString");

        System.out.println("-- annotations:");
        Annotation[] annotations = method.getAnnotations();
        System.out.println(Arrays.toString(annotations));

        System.out.println("-- methods:");
        for (var methodR : clazz.getDeclaredMethods()) {
            System.out.print(methodR.getName() + " -> params: ");
            System.out.println(Arrays.toString(methodR.getParameterTypes()));
            System.out.println("----------");
        }

        var params = clazz.getMethod("setPrivateField", String.class)
                .getParameterTypes();
        System.out.println(Arrays.toString(params));

        System.out.println("-- modifiers:");
        int modifiers = method.getModifiers();
        System.out.println("modifiers: " + Modifier.toString(modifiers));
        System.out.println("isPublic: " + Modifier.isPublic(modifiers));
        System.out.println("isFinal: " + Modifier.isFinal(modifiers));
        System.out.println("isStatic: " + Modifier.isStatic(modifiers));

        System.out.println("-- execution:");
        var result = method.invoke(new DemoClass("demoVal"));
        System.out.println("result: " + result);
    }

    public static void baseFeatures() throws ClassNotFoundException {
        Class<?> classString = Class.forName("java.lang.String");
        System.out.println("simpleName: " + classString.getSimpleName());
        System.out.println("canonicalName: " + classString.getCanonicalName());

        Class<Integer> classInt = int.class;
        System.out.println("typeName int: " + classInt.getTypeName());

        Class<Integer> classInteger = Integer.class;
        System.out.println("typeName integer: " + classInteger.getTypeName());
    }

    public static void getInfo() throws NoSuchMethodException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name: " + clazz.getSimpleName());

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("-- constructors:");
        System.out.println(Arrays.toString(constructors));

        Method[] methodsPublic = clazz.getMethods();
        System.out.println("-- public methods:");
        Arrays.stream(methodsPublic).forEach(method -> System.out.println(method.getName()));

        Method[] methodsAll = clazz.getDeclaredMethods();
        System.out.println("-- all methods:");
        Arrays.stream(methodsAll).forEach(method -> System.out.println(method.getName()));

        System.out.println("-- public fields:");
        Field[] fieldsPublic = clazz.getFields();
        System.out.println(Arrays.toString(fieldsPublic));

        System.out.println("-- all fields:");
        Field[] fieldsAll = clazz.getDeclaredFields();
        Arrays.stream(fieldsAll).forEach(System.out::println);

        System.out.println("-- annotations:");
        Method annotatedMethod = clazz.getMethod("toString");
        Annotation[] annotations = annotatedMethod.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));
    }

    public static void arrayFeatures() {
        int[] arrayInt = new int[0];

        Class<? extends int[]> clazz = arrayInt.getClass();
        System.out.println("isArray: " + clazz.isArray());
        System.out.println("typeName: " + clazz.getTypeName());

        Class<?> componentType = clazz.getComponentType();
        System.out.println("componentType: " + componentType + "\n");

        System.out.println("-- creating Array:");
        float[] arrayFloat = (float[]) Array.newInstance(float.class, 5);
        System.out.println("length:" + arrayFloat.length);
        System.out.println("created TypeName:" + arrayFloat.getClass().getTypeName());
    }

    public static void createObject() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class<DemoClass> clazz = DemoClass.class;
        System.out.println("Class Name: " + clazz.getSimpleName());

        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("-- constructors:");
        System.out.println(Arrays.toString(constructors));

        System.out.println("-- creating new object:");
        Constructor<DemoClass> constructor = clazz.getConstructor(String.class);
        DemoClass object = constructor.newInstance("testVal");
        System.out.println("value: " + object.getPrivateField());
    }

    public static void additionalFeatures() throws ClassNotFoundException, NoSuchMethodException {
        var primitiveString = String.class.isPrimitive();
        var primitiveInt = int.class.isPrimitive();
        System.out.println("primitiveString:" + primitiveString + ", primitiveInt: " + primitiveInt);

        int[] arr = {1, 2};
        var isArray = arr.getClass().isArray();
        var componentArr = arr.getClass().getComponentType();
        System.out.println("isArray: " + isArray + ",  componentArr: " + componentArr);

        Class<?> string = Class.forName("java.lang.String");
        var isIterableString = Iterable.class.isAssignableFrom(string);

        Class<?> list = Class.forName("java.util.ArrayList");
        var isIterableList = Iterable.class.isAssignableFrom(list);
        System.out.println("isIterableString: " + isIterableString + ", isIterableList: " + isIterableList);

        var hasAnnotation = DemoClass.class.getMethod("toString")
                .isAnnotationPresent(SimpleAnnotation.class);
        System.out.println("hasAnnotation: " + hasAnnotation);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        System.out.println("-------------------------------------------");
        privateToPublic();
        System.out.println("-------------------------------------------");
        methodFeatures();
        System.out.println("-------------------------------------------");
        baseFeatures();
        System.out.println("-------------------------------------------");
        getInfo();
        System.out.println("-------------------------------------------");
        createObject();
        System.out.println("-------------------------------------------");
        arrayFeatures();
        System.out.println("-------------------------------------------");
        additionalFeatures();
        System.out.println("-------------------------------------------");
    }
}
