package com.example.controller;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private  final  UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser (@RequestBody UserDTO user){
        UserDTO resultUser = userService.createUser(user);
        return  ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getUsers (){
        List<UserDTO> userDTOS = userService.getUsers();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDTO> getUser (@PathVariable("id") Long id){
        UserDTO dto = userService.getUser(id);
        return ResponseEntity.ok(dto);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDTO> updateUser (@PathVariable("id") Long id ,@RequestBody User user){
        UserDTO resultUser = userService.updateUser(id,user);
        return ResponseEntity.ok(resultUser);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
       Boolean status =  userService.deleteUser(id);
       return  ResponseEntity.ok(status);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<User>> pagination (@RequestParam int currentPage ,
                                                  @RequestParam int pageSize){
        return ResponseEntity.ok(
                userService.pagination(currentPage,pageSize));

    }


}


