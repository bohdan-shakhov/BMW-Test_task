package com.knubisoft.bmwtesttask.converter;

import com.knubisoft.bmwtesttask.db_model.Company;
import com.knubisoft.bmwtesttask.dto.CompanyDTO;

public class CompanyConverter {

    public static Company convertCompanyDTOToCompany(final CompanyDTO companyDTO) {
        return Company.builder()
                .bs(companyDTO.getBs())
                .catchPhrase(companyDTO.getCatchPhrase())
                .name(companyDTO.getName())
                .build();
    }

}
