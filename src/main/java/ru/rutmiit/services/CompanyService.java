package ru.rutmiit.services;

import ru.rutmiit.dto.AddCompanyDto;
import ru.rutmiit.dto.ShowCompanyInfoDto;
import ru.rutmiit.dto.ShowDetailedCompanyInfoDto;
import ru.rutmiit.models.entities.Company;
import ru.rutmiit.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public void addCompany(AddCompanyDto companyDTO) {
        companyRepository.saveAndFlush(mapper.map(companyDTO, Company.class));
    }

    public List<ShowCompanyInfoDto> allCompanies() {
        return companyRepository.findAll().stream().map(company -> mapper.map(company, ShowCompanyInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowDetailedCompanyInfoDto companyDetails(String companyName) {
        return mapper.map(companyRepository.findByName(companyName).orElse(null), ShowDetailedCompanyInfoDto.class);
    }

    public void removeCompany(String companyName) {
        companyRepository.deleteByName(companyName);
    }
}
