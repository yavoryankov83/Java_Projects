package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.StatusValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusValueRepository extends JpaRepository<StatusValue, Integer> {

  @Query(value = "select * from beer_tag.status_values sv", nativeQuery = true)
  List<StatusValue> getAllStatusValues();

  @Query(value = "select * from beer_tag.status_values sv\n" +
          "where sv.status_value = :status_value", nativeQuery = true)
  StatusValue getStatusValueByValue(@Param(value = "status_value") String status_value);

  @Query(value = "select * from beer_tag.status_values sv\n" +
          "where sv.id = :status_value_id", nativeQuery = true)
  StatusValue getStatusValueById(@Param(value = "status_value_id") int status_value_id);

}
