package sn.isi.userservice.controller;
import sn.isi.userservice.model.Users;
import sn.isi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public List<Users> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Users get(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Users create(@RequestBody Users user) { return service.save(user); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

