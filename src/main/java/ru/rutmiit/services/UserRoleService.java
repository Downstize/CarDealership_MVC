package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddUserRoleDto;
import ru.rutmiit.dto.ShowUserRoleInfoDto;
import ru.rutmiit.models.Enum.RoleEnum;
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

    public List<ShowUserRoleInfoDto> getAll() {
        return userRoleRepository.findAll().stream().map((userRole) -> modelMapper.map(userRole, ShowUserRoleInfoDto.class)).collect(Collectors.toList());
    }


    public void removeUserRole(RoleEnum roleEnum) {
        userRoleRepository.deleteByRole(roleEnum);
    }

}