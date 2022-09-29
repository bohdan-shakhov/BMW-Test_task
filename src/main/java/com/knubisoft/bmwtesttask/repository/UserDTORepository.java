package com.knubisoft.bmwtesttask.repository;

import com.knubisoft.bmwtesttask.db_model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Long> {
}
