package ru.idea.test.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class JvmProxyTest {


    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void simple_proxy() {
        Map<String, String> proxyInstance = (Map) Proxy.newProxyInstance(
                JvmProxyTest.class.getClassLoader(),
                new Class[]{Map.class},
                new DynamicInvocationHandler());

        proxyInstance.put("hello", "world");
        var h = proxyInstance.get("hello"); //exception
        System.err.println(h);

    }

    static class DynamicInvocationHandler implements InvocationHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LOGGER.info("Invoked method: " + method.getName());
            return "42";
        }
    }

    public static class TimingDynamicInvocationHandler implements InvocationHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(TimingDynamicInvocationHandler.class);

        private final Map<String, Method> methods = new HashMap<>();

        private final Object target;

        public TimingDynamicInvocationHandler(Object target) {
            this.target = target;

            for (Method method : target.getClass().getDeclaredMethods()) {
                this.methods.put(method.getName(), method);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            long start = System.nanoTime();
            Object result = methods.get(method.getName()).invoke(target, args);
            long elapsed = System.nanoTime() - start;

            LOGGER.info(String.format("Executing %s finished in %s ns", method.getName(), elapsed));

            return result;
        }
    }



    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void proxy_with_time() {
        Map<String, String> mapProxyInstance = (Map) Proxy.newProxyInstance(
                JvmProxyTest.class.getClassLoader(), new Class[]{Map.class},
                new TimingDynamicInvocationHandler(new HashMap<>()));

        mapProxyInstance.put("hello", "world");

        CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
                JvmProxyTest.class.getClassLoader(),
                new Class[]{CharSequence.class},
                new TimingDynamicInvocationHandler("Hello World"));

        csProxyInstance.length();
    }
}
