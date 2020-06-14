package ru.idea.test.spring.core.component.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component("nonSingleTool")
public class NoSingleToolFactory implements FactoryBean<Tool> {

    private long factoryId = 1;
    private long toolId = 0;

    @Override
    public Tool getObject() throws Exception {
        toolId++;
        return new Tool(toolId);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public String toString() {
        return "ToolFactory{" +
                "factoryId=" + factoryId +
                ", toolId=" + toolId +
                '}';
    }
}
