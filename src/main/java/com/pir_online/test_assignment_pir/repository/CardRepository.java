package com.pir_online.test_assignment_pir.repository;

import com.pir_online.test_assignment_pir.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying
    @Query(value = "DELETE FROM card WHERE id = :cardId", nativeQuery = true)
    void deleteByIdNative(@Param("cardId") Long cardId);
}
