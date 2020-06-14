package ru.idea.test.spring.core.component.simple;

import org.springframework.stereotype.Component;
import ru.idea.test.spring.core.component.Delegate;

@Component
public class AnyInjectableStringDelegateBean implements Delegate<String> {


    public final Delegate<String> delegate;

    public AnyInjectableStringDelegateBean(Delegate<String> delegate) {
        this.delegate = delegate;
    }


    @Override
    public void execute(String val) {
        delegate.execute(val);
    }
}
