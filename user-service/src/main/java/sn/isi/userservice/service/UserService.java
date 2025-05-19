package sn.isi.userservice.service;

import sn.isi.userservice.model.Users;
import sn.isi.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public List<Users> findAll() { return repo.findAll(); }
    public Users findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Users save(Users user) { return repo.save(user); }
    public void delete(Long id) { repo.deleteById(id); }
}