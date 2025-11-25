package com.xtra.demo.data.repository;

import com.xtra.demo.data.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    @Query(nativeQuery = true, value = """
    SELECT exists (
    select pid from patients where phone = :phone)
    """)
    boolean phoneNumberExist(@Param("phone") String phone);

}
