package org.example;

import org.example.BusinessRuleEngineCap05.BusinessRuleEngine;
import org.example.BusinessRuleEngineCap05.interfaces.Action;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially()
    {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        Assert.assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddRulesInitially()
    {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
        businessRuleEngine.addAction(()-> {});

        Assert.assertEquals(1, businessRuleEngine.count());
    }

    @Test
    public void shouldExecuteOneAction()
    {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        Action mockAction = Mockito.mock(Action.class);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        Mockito.verify(mockAction).execute();
    }
}
