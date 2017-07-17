package org.powermock.examples.spring.mockito;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;
import powermock.examples.spring.FinalClass;
import powermock.examples.spring.MyBean;
import powermock.examples.spring.PrivateMethod;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FinalClass.class, PrivateMethod.class, MyBean.class})
public class SpringInjectFinalClassExampleTest extends TestBase{

    @Mock
    private FinalClass finalClass;

    @InjectMocks
    private MyBean myBean = new MyBean();

    @Test
    public void testInjectFinalClass() throws Exception {
        final String value = "What's up?";
        MyBean myBean = PowerMockito.spy(new MyBean());//创建spy
        when(finalClass.sayHello()).thenReturn(value);
        PowerMockito.when(myBean,"isExist").thenReturn(Boolean.TRUE);

        assertEquals(value, myBean.sayHello());

    }

    @Test
    public void testPrivateMethod() throws Exception {
        MyBean test = PowerMockito.spy(new MyBean());//创建spy
        ReflectionTestUtils.setField(test, "finalClass", finalClass);
        PowerMockito.when(finalClass.sayHello()).thenCallRealMethod();


        PowerMockito.when(test,"isExist").thenReturn(Boolean.FALSE);
        Assert.assertEquals(null, test.sayHello());
        //改变私有方法的返回值后，断定调用私有方法的方法

        PowerMockito.when(test,"isExist").thenReturn(Boolean.TRUE);
        Assert.assertEquals("Hello, man!", test.sayHello());


        //mock
        final String value = "What's up?";
        when(finalClass.sayHello()).thenReturn(value);
        PowerMockito.when(test,"isExist").thenReturn(Boolean.TRUE);
        Assert.assertEquals(value, test.sayHello());

        verify(test, times(3)).sayHello();
    }



}
