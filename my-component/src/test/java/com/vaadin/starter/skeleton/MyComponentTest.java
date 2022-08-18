package com.vaadin.starter.skeleton;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.vaadin.flow.component.UI;
import com.vaadin.starter.mycomponent.MyComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyComponentTest {
    @BeforeEach
    public void setupVaadin() {
        MockVaadin.setup();
    }

    @AfterEach
    public void teardownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void smokeTest() {
        // Smoke test is a quick test to check that the basic machinery is in place and works.
        // The analogy would be to turn on an electric device (e.g. a coffee maker)
        // then turn it off immediately without even checking whether it actually works or not,
        // and watch whether there is any smoke. If yes, the coffee maker is
        // probably burning from a short-circuit and any further tests are pointless.
        UI.getCurrent().add(new MyComponent());
    }
}
