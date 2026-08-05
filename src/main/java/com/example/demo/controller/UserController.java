package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 REST 接口
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** GET /api/users —— 获取所有用户 */
    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userService.findAll());
    }

    /** GET /api/users/search?minAge=25 —— 按年龄筛选 */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByAge(@RequestParam("minAge") int minAge) {
        return ResponseEntity.ok(userService.findByAgeGreaterThan(minAge));
    }

    /** GET /api/users/{id} —— 根据 ID 获取用户 */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /** POST /api/users —— 创建用户 */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** PUT /api/users/{id} —— 更新用户 */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean ok = userService.update(user);
        if (!ok) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    /** DELETE /api/users/{id} —— 删除用户 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = userService.deleteById(id);
        if (!ok) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
