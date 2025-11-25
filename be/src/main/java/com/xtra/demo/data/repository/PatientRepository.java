package com.xtra.demo.data.repository;

import com.xtra.demo.data.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<PatientEntity, String> {

    @Query(nativeQuery = true, value = """
    SELECT exists (
    select pid from patients where phone = :phone)
    """)
    boolean phoneNumberExist(@Param("phone") String phone);

}
