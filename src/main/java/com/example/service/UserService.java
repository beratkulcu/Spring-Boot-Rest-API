package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user); //public abstract yazmamıza gerek yok zaten default olarak oyledir.

    List<UserDTO> getUsers();

    UserDTO getUser(Long id);

    UserDTO updateUser(Long id, User user);

    Boolean deleteUser(Long id);

    Page<User> pagination (int currentPage , int pageSize);

    Page<User> pagination (Pageable pageable);
}
