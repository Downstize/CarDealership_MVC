package ru.rutmiit.services;

import ru.rutmiit.dto.AddEmployeeDto;
import ru.rutmiit.dto.ShowDetailedEmployeeInfoDto;
import ru.rutmiit.dto.ShowEmployeeInfoDto;
import ru.rutmiit.models.entities.Employee;
import ru.rutmiit.repositories.CompanyRepository;
import ru.rutmiit.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final CompanyRepository companyRepository;

    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public void addEmployee(AddEmployeeDto employeeDTO) {
        Employee employee = mapper.map(employeeDTO, Employee.class);
        employee.setCompany(companyRepository.findByName(employeeDTO.getCompanyName()).orElse(null));

        employeeRepository.saveAndFlush(employee);
    }

    public List<ShowEmployeeInfoDto> allEmployees() {
        return employeeRepository.findAll().stream().map(employee -> mapper.map(employee, ShowEmployeeInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedEmployeeInfoDto employeeInfo(String employeeFullName) {
        return mapper.map(employeeRepository.findEmployeeByFullName(employeeFullName), ShowDetailedEmployeeInfoDto.class);
    }

    public void fireEmployee(String employeeFullName) {
        employeeRepository.deleteEmployeeByFullName(employeeFullName);
    }
}
