package com.example.service.impl;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.messages.ErrorMessages;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user),UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream()
                .map(user -> modelMapper
                        .map(user,UserDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return  modelMapper.map(user.get(), UserDTO.class);
        }else {
            throw  new UserNotFoundException("Kullanıcı Bulunamadı ");
        }
    }

    @Override
    public UserDTO updateUser(Long id, User user) {
        Optional<User> user1 = userRepository.findById(id);

        if (user1.isPresent()){
            user1.get().setFirstName(user.getFirstName());
            user1.get().setLastName(user.getLastName());
            user1.get().setUpdatedAt(user.getUpdatedAt());
            user1.get().setUpdatedBy(user.getUpdatedBy());
            return modelMapper.map(userRepository.save(user1.get()), UserDTO.class);
        }else {
            throw new UserNotFoundException("Böyle Bir kullanıcı yoktur .");
        }

    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }else {
            throw new UserNotFoundException("kullanıcı bulunamadı");
        }
    }

    @Override
    public Page<User> pagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return  userRepository.findAll(pageable);
    }

    @Override
    public Page<User> pagination(Pageable pageable) {
        return  userRepository.findAll(pageable);
    }
}
