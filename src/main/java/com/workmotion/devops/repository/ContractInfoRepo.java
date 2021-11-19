package com.workmotion.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workmotion.devops.model.ContractInfo;

@Repository
public interface ContractInfoRepo extends JpaRepository<ContractInfo, Long> {

}
