package forest.detector.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    private HelloService helloService = new HelloService();
    @Disabled
    @Test
    public void returnGreetingMessage() {
        Assertions.assertEquals("hello heroku from home page!", helloService.getGreetingMessage());
    }
}
