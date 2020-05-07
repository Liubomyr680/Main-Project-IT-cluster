package forest.detector.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    private HelloService helloService = new HelloService();

    @Test
    public void returnGreetingMessage() {
        Assertions.assertEquals("hello heroku from home page!", helloService.getGreetingMessage());
    }
}
