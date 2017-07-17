package powermock.examples.spring;

import org.springframework.stereotype.Component;
import sun.misc.Contended;

/**
 * Created by 七百 on 17-7-14.
 */
@Component
public class UnderTestClass {
    static {
        System.loadLibrary("evil.dll");
    }

    private final String message = "test";

    private String content;

    public String getMessage() {
        return message;
    }

    public String  callPrivateContent(){
        return getContent();
    }

    private String getContent(){
        content = "private";
        return content;
    }
}
