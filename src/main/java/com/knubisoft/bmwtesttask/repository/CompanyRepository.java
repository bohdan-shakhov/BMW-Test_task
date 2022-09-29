package com.knubisoft.bmwtesttask.repository;

import com.knubisoft.bmwtesttask.db_model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
