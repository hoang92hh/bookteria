package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.InvalidatedToken;
import com.devteria.identityservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {

}
