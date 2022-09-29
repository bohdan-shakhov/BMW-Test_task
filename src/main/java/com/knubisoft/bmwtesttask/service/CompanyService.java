package com.knubisoft.bmwtesttask.service;

import com.knubisoft.bmwtesttask.db_model.Address;
import com.knubisoft.bmwtesttask.db_model.Company;
import com.knubisoft.bmwtesttask.dto.CompanyDTO;
import com.knubisoft.bmwtesttask.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    protected void insertCompanyToDatabase(final CompanyDTO companyDTO) {
        Company company = convertCompanyDTOToCompany(companyDTO);
        companyRepository.save(company);
    }

    protected Company convertCompanyDTOToCompany(final CompanyDTO companyDTO) {
        return Company.builder()
                .bs(companyDTO.getBs())
                .catchPhrase(companyDTO.getCatchPhrase())
                .name(companyDTO.getName())
                .build();
    }
}
