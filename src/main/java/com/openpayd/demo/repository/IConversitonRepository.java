package com.openpayd.demo.repository;


import com.openpayd.demo.model.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface IConversitonRepository extends PagingAndSortingRepository<Conversion, String> {

    @Query("SELECT c FROM Conversion c WHERE c.transactionId = :transactionId ")
    Optional<Conversion> findByTransactionId(@Param("transactionId") String transactionId);

    @Query("SELECT c FROM Conversion c WHERE c.conversionTime between :startDate AND :endDate")
    Page<Conversion> listByDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

}


