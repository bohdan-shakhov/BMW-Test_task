package com.knubisoft.bmwtesttask.repository;

import com.knubisoft.bmwtesttask.db_model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {
}
