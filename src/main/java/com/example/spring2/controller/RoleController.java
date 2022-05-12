package com.example.spring2.controller;

import com.example.spring2.model.Role;
import com.example.spring2.model.User;
import com.example.spring2.payload.RolePayload;
import com.example.spring2.repository.RoleRepository;
import com.example.spring2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("findAll")
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<Role> create(@RequestBody RolePayload rolePayload){
        Role role = new Role();
        role.setRoleName(rolePayload.getRoleName());
      //  System.out.println("role: "+role);
        for (Integer integer : rolePayload.getUserlist()) {
            Optional<User> userOptional = userRepository.findById(integer);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                // System.out.println("user: "+user);
                role.getUsers().add(user);

            }
        }
        return ResponseEntity.ok().body(roleRepository.save(role));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam("id") int id){
        List<User> list = userRepository.findByidRole(id);
        System.out.println(list);
       for(int i = 0; i < list.size(); i++){
           userRepository.deleteById(id);
       }
       Optional<Role> role = roleRepository.findById(id);
        if(role != null){
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(null);
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
