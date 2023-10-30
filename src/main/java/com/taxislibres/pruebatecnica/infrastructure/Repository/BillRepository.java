package com.taxislibres.pruebatecnica.infrastructure.Repository;

import com.taxislibres.pruebatecnica.Domain.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBillsByUser_Id(Long userId);
}
