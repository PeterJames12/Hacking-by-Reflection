package hacking;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Hacker {

    public static void main(String[] args) {

        try {
            hackVariable();
            hackMethod();
            hackMassive();
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void hackVariable() throws NoSuchFieldException, IllegalAccessException {

        Victim victim = new Victim("David");
        Field person = victim.getClass().getDeclaredField("person");
        person.setAccessible(true);
        String name = (String) person.get(victim);
        System.out.println(name);

        person.set(victim, "Peter");
        name = (String) person.get(victim);
        System.out.println(name);
    }

    private static void hackMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Victim victim = new Victim("Peter");
        Method printMe = victim.getClass().getDeclaredMethod("printMe");
        printMe.setAccessible(true);
        Object invoke = printMe.invoke(victim);

        final String fullName;
        if (invoke instanceof String) {
            fullName = (String) invoke;
        } else {
            fullName = "unknown";
        }
        System.out.println(fullName);
    }

    private static void hackMassive() throws NoSuchFieldException, IllegalAccessException {

        Victim victim = new Victim("Peter");
        Field massive = Victim.class.getDeclaredField("mas");
        Class<?> type = massive.getType();
        Class<?> componentType = type.getComponentType();

        Object arr = Array.newInstance(componentType, 5); // if i don't know length of mas?

        System.out.println("Before " + Arrays.toString((int[]) arr));
        Array.set(arr, 0, 23);
        Array.set(arr, 1, 28);
        Array.set(arr, 2, 5);
        Array.set(arr, 3, 12);
        Array.set(arr, 4, 17);
        System.out.println("After " + Arrays.toString((int[]) arr));

        System.out.println("Before " + Arrays.toString(victim.getMas()));
        massive.setAccessible(true);
        massive.set(victim, arr);
//        System.out.println(victim.mas); // how use it without any methods why print mas ???
        System.out.println("After" + Arrays.toString(victim.getMas()));
    }
}
