package ru.idea.test.core;

public class Main {

    static void m(Object o) {
        System.out.println("one");
    }

    static void m(String[] o) {
        System.out.println("two");
    }

    static <T> T g() {
        return null;
    }

    public static void main(String[] args) {
        m(g());
    }

    public synchronized void doX(){
        System.out.println("aaaa");
    }

    public void doY(){
        synchronized (this){
            System.out.println("aaaa");
        }
    }
}
