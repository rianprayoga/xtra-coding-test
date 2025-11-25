package com.xtra.demo.data.repository;

import com.xtra.demo.data.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {

    @Query(nativeQuery = true, value = """
    SELECT exists (
        select pid from patients where phone = :phone and active = true)
    """)
    boolean phoneNumberExist(@Param("phone") String phone);

    @Query(nativeQuery = true, value = """
        select * from patients where pid = :id and active = true
    """)
    Optional<PatientEntity> findByIdAndActive(@Param("id") UUID id);

    @Query(nativeQuery = true, value = """
        update patients set active = false
        where pid = :id
    """)
    @Modifying
    void inactivePatient(@Param("id") UUID id);
}
