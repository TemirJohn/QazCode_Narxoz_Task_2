package org.Temirjohn.Task_3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CashResult {
}

public class Task_3 {
    private final Map<MethodKey, Object> cache = new HashMap<>();

    private record MethodKey(String methodName, List<Object> args) {}

    @CashResult
    public double calcuCirlceArea(double radius) {
        System.out.println("Circle radius: " + radius);
        return Math.PI * Math.pow(radius, 2);
    }

    public void printHello(String name) {
        System.out.println("Hello, " + name);
    }


    public Object execute(String methodName, Object... args) throws Exception {
        Class<?>[] argTypes = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);

        Method method = findMethod(methodName);

        if (method.isAnnotationPresent(CashResult.class)) {
            MethodKey methodKey = new MethodKey(methodName, Arrays.asList(args));

            if (cache.containsKey(methodKey)) {
                System.out.println("[Cache]: " + methodName);
                return cache.get(methodKey);
            }

            Object res = method.invoke(this, args);
            cache.put(methodKey, res);
            return res;
        }
        return method.invoke(this, args);
    }

    public Method findMethod(String methodName) {
        return Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(methodName));
    }

    public static void main(String[] args) throws Exception {
        Task_3 system = new Task_3();
        System.out.println("-- First call --");
        System.out.println("Result: " + system.execute("calcuCirlceArea", 10.0));

        System.out.println("-- First call again --");
        System.out.println("Result: " + system.execute("calcuCirlceArea", 10.0));

        System.out.println("-- Second call --");
        System.out.println("Result: " + system.execute("calcuCirlceArea", 5.0));

        System.out.println("-- call method without annotation --");
        system.execute("printHello", "Temirzhan");
    }
}
