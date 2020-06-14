package ru.idea.test.spring.core.component.factorybean;

public class Tool {

    private final long id;

    public Tool(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tool tool = (Tool) o;

        return id == tool.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                '}';
    }
}
