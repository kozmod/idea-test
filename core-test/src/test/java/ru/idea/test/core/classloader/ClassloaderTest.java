package ru.idea.test.core.classloader;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class ClassloaderTest {
    @Test
    public void shouldGetClassloader_From() {
        ClassLoader appClassloader = TestClass.class.getClassLoader();
        ClassLoader bootstrapClassloader = String.class.getClassLoader();
        assertEquals("jdk.internal.loader.ClassLoaders$AppClassLoader", appClassloader.getClass().getName());
        assertNull(bootstrapClassloader);
    }
}
