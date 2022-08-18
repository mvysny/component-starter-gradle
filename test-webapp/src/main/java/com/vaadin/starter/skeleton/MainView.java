package com.vaadin.starter.skeleton;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.mycomponent.MyComponent;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Span("My Component:"));
        add(new MyComponent());
    }
}
