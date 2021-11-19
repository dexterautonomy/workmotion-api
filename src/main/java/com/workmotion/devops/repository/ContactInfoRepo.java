package com.workmotion.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workmotion.devops.model.ContactInfo;

public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {

}
