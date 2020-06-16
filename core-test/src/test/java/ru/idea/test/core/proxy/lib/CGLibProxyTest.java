package ru.idea.test.core.proxy.lib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class CGLibProxyTest {

    @Test
    public void should() {
        new SampleChild().method1();

        System.out.println("//------------------------------------------//");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleParent.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if(method.getName().equals("method2")) {
                System.out.println("SampleProxy.method2");
                return null;
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });
        ((SampleParent) enhancer.create()).method1();
    }
}

class SampleParent {

    public void method1() {
        System.out.println("SampleParent.method1");
        method2();
    }

    public void method2() {
        System.out.println("SampleParent.method2");
    }

}

class SampleChild extends SampleParent {

    @Override
    public void method2() {
        System.out.println("SampleChild.method2");
    }

}
