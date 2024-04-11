package org.example.BusinessRuleEngineCap05;

import org.example.BusinessRuleEngineCap05.interfaces.Action;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {

    public final List<Action> actionsLit = new ArrayList<>();

    public void addAction(final Action action) {
        this.actionsLit.add(action);
    }

    public int count() {
        return this.actionsLit.size();
    }
    public void run() {
       actionsLit.get(0).execute();
    }
}
