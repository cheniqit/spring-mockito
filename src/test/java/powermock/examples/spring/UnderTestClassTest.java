package powermock.examples.spring;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.examples.spring.mockito.TestBase;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by 七百 on 17-7-14.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UnderTestClass.class)
@SuppressStaticInitializationFor("powermock.examples.spring.UnderTestClass")//阻止静态代码块运行
public class UnderTestClassTest extends TestBase{
    @Test
    public void callPrivateContent() throws Exception {
        UnderTestClass test = PowerMockito.spy(new UnderTestClass());//创建spy

        //断定调用私有方法的方法
        PowerMockito.when(test.callPrivateContent()).thenCallRealMethod();
        Assert.assertEquals("private", test.callPrivateContent());

        //改变私有方法的返回值后，断定调用私有方法的方法
        PowerMockito.when(test,"getContent").thenReturn("test2");
        Assert.assertEquals("test2", test.callPrivateContent());

        //公共方法的断定
        PowerMockito.when(test.getMessage()).thenReturn("test1");
        Assert.assertEquals("test1", test.getMessage());
    }

}