package mobify.app.security.infrastructure.controller;

import mobify.app.security.SecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PublicControllerTest {
    // https://stackoverflow.com/a/51161572/6846888
    @Autowired
    private WebTestClient webClient;

    @Test
    public void homeTest() {

        long startTime = System.currentTimeMillis();

        this.webClient.get().uri("/").exchange().expectStatus().isOk();

        long endTime = System.currentTimeMillis();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}
