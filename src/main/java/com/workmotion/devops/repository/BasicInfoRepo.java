package com.workmotion.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmotion.devops.model.BasicInfo;

public interface BasicInfoRepo extends JpaRepository<BasicInfo, Long>{

}
