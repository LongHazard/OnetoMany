package com.example.spring2.controller;

import com.example.spring2.model.Role;
import com.example.spring2.model.User;
import com.example.spring2.repository.RoleRepository;
import com.example.spring2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody User user){
        Optional<User> user1 = userRepository.findById(user.getIdUser());
        if(user1.isPresent()){
            user1.get().setName(user.getName());
            user1.get().setAddress(user.getAddress());
            user1.get().setMail(user.getMail());
            return ResponseEntity.ok().body(userRepository.save(user1.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){

        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam ("id") int id){
        Optional<User> user = userRepository.findById(id);
        System.out.println(user);
        if(user != null){
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(null);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
