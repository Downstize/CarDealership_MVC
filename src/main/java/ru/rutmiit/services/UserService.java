package ru.rutmiit.services;

import org.modelmapper.ModelMapper;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddUserDto;
import ru.rutmiit.dto.ShowDetailedUserInfoDto;
import ru.rutmiit.dto.ShowUserInfoDto;
import ru.rutmiit.models.User;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }


    public void addUser(AddUserDto user) {
        User u = modelMapper.map(user, User.class);
        u.setRole(userRoleRepository.findByRoleEnum(user.getRole()).orElse(null));
        userRepository.saveAndFlush(u);
    }

    public List<ShowUserInfoDto> getAll() {
        return userRepository.findAll().stream().map((user) -> modelMapper.map(user, ShowUserInfoDto.class)).collect(Collectors.toList());
    }


    public Optional<AddUserDto> findUser(String id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), AddUserDto.class));
    }

    public ShowDetailedUserInfoDto userDetails(String userName) {
        return modelMapper.map(userRepository.findByUserName(userName).orElse(null), ShowDetailedUserInfoDto.class);
    }

    public void removeUser(String userName) {
        userRepository.deleteByUserName(userName);
    }


    public AddUserDto update(AddUserDto users) {
        Optional<User> existingUser = userRepository.findByUserName(users.getUserName());
        if (existingUser.isPresent()) {
            return modelMapper.map(userRepository.save(modelMapper.map(users, User.class)), AddUserDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update");
        }
    }
}