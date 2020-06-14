package ru.idea.test.spring.core.component.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component("singleTool")
public class SingleToolFactory implements FactoryBean<Tool> {

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
        return true;
    }

    @Override
    public String toString() {
        return "ToolFactory{" +
                "factoryId=" + factoryId +
                ", toolId=" + toolId +
                '}';
    }
}
