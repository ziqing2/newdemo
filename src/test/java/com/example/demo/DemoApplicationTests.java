package com.example.demo;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldListAllUsers() {
        ResponseEntity<User[]> resp = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users", User[].class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).hasSize(3);
    }

    @Test
    void shouldGetUserById() {
        ResponseEntity<User> resp = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users/1", User.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getName()).isEqualTo("张三");
    }

    @Test
    void shouldCreateAndDeleteUser() {
        User newUser = new User("测试用户", 22, "test@example.com");
        ResponseEntity<User> createResp = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/users", newUser, User.class);
        assertThat(createResp.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Long id = createResp.getBody().getId();
        restTemplate.delete("http://localhost:" + port + "/api/users/" + id);
    }

    @Test
    void shouldReturn404ForMissingUser() {
        ResponseEntity<User> resp = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users/999", User.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
