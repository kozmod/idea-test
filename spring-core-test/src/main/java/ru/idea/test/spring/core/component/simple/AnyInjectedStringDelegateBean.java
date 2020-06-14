package ru.idea.test.spring.core.component.simple;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.idea.test.spring.core.component.Delegate;

@Component
@Lazy
public class AnyInjectedStringDelegateBean implements Delegate<String> {

    @Override
    public void execute(String val) {
        System.err.println(
                String.format("class: %s\nval:%s",this.getClass().toString(), val)
        );
    }
}
