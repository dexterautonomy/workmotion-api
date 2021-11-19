package com.workmotion.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmotion.devops.model.ContractInfo;

public interface ContractInfoRepo extends JpaRepository<ContractInfo, Long> {

}
