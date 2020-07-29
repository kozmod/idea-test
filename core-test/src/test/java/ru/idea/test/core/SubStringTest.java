package ru.idea.test.core;

import org.junit.Assert;
import org.junit.Test;

public class SubStringTest {

    @Test
    public void shouldGetSubString() {
        var oldTag = "aaa/xxxx/v1";
        int lastSlash = oldTag.lastIndexOf("/");
        var res = oldTag.substring(0, lastSlash + 1);
        System.out.println(res);
        Assert.assertEquals("aaa/xxxx/", res);
    }
}
