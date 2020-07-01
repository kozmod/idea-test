package ru.idea.test.core.object;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class CloneableTest {

    private static final int ONE = 1;
    private static final int TWO = 2;

    private static final String A = "A";
    private static final String B = "B";

    @SuppressWarnings("SimplifiableJUnitAssertion")
    @Test
    public void shouldClone_shallow_copy() throws CloneNotSupportedException {
        List<String> strings = new ArrayList<>();
        strings.add(A);

        var source = new ShallowCopyClone().setVal(ONE).setStrings(strings);
        var clone = source.clone();
        source.setVal(TWO);
        source.getStrings().add(B);

        assertTrue(source != clone);
        assertFalse(source.getClass() != clone.getClass());
        assertEquals(TWO, source.getVal());
        assertEquals(ONE, ((ShallowCopyClone) clone).getVal());

        assertFalse(source.getStrings() != ((ShallowCopyClone) clone).getStrings());
        assertThat(((ShallowCopyClone) clone).getStrings(), hasSize(2));
        assertThat(((ShallowCopyClone) clone).getStrings(), hasItems(A, B));
    }

    @SuppressWarnings("SimplifiableJUnitAssertion")
    @Test
    public void shouldClone_deep_copy() {
        List<String> strings = new ArrayList<>();
        strings.add(A);

        var source = new DeepCopyClone().setVal(ONE).setStrings(strings);
        var clone = source.clone();
        source.setVal(TWO);
        source.getStrings().add(B);

        assertTrue(source != clone);
        assertFalse(source.getClass() != clone.getClass());
        assertEquals(TWO, source.getVal());
        assertEquals(ONE, ((DeepCopyClone) clone).getVal());

        assertTrue(source.getStrings() != ((DeepCopyClone) clone).getStrings());

        assertThat(source.getStrings(), hasSize(2));
        assertThat(source.getStrings(), hasItems(A, B));

        assertThat(((DeepCopyClone) clone).getStrings(), hasSize(1));
        assertThat(((DeepCopyClone) clone).getStrings(), hasItems(A));
    }

    @Test
    @Ignore
    public void shouldCompileErrorWhenCloneMethodNotOverride() throws CloneNotSupportedException {
        var source = new NotOverrideClone();
//        source.clone(); // COMPILE ERROR
    }

    @Test(expected = CloneNotSupportedException.class)
    public void shouldThrowExceptionWhenCloneableNotImplemented() throws CloneNotSupportedException {
        var source = new ExceptionClone();
        source.clone();
    }

    static class ShallowCopyClone implements Cloneable {

        private int val;
        private List<String> strings;

        public int getVal() {
            return val;
        }

        public ShallowCopyClone setVal(int val) {
            this.val = val;
            return this;
        }

        public List<String> getStrings() {
            return strings;
        }

        public ShallowCopyClone setStrings(List<String> strings) {
            this.strings = strings;
            return this;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class DeepCopyClone implements Cloneable {

        private int val;
        private List<String> strings;

        public int getVal() {
            return val;
        }

        public DeepCopyClone setVal(int val) {
            this.val = val;
            return this;
        }

        public List<String> getStrings() {
            return strings;
        }

        public DeepCopyClone setStrings(List<String> strings) {
            this.strings = strings;
            return this;
        }

        @Override
        protected Object clone() {
            return new DeepCopyClone()
                    .setVal(val)
                    .setStrings(new ArrayList<>(strings));
        }
    }

    static class NotOverrideClone implements Cloneable {
    }

    static class ExceptionClone {

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
