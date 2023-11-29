package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddUserRoleDto;
import ru.rutmiit.models.UserRole;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }


    public void addRole(AddUserRoleDto roleDto) {
        UserRole role = modelMapper.map(roleDto, UserRole.class);
        userRoleRepository.saveAndFlush(role);
    }

    public List<AddUserRoleDto> getAll() {
        return userRoleRepository.findAll().stream().map((s) -> modelMapper.map(s, AddUserRoleDto.class)).collect(Collectors.toList());
    }

//    public void removeUserRole(RoleEnum roleEnum) {
//        userRoleRepository.deleteByUserRole(roleEnum);
//    }

}