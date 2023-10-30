package com.taxislibres.pruebatecnica.infrastructure.Service;

import com.taxislibres.pruebatecnica.Domain.Models.Bill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.NewBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.ShowDataBill;
import com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill.UpdateBill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    Bill createBill(Long id, NewBill newBill);
    UpdateBill updateBill(Long id, UpdateBill updateBill);
    void deleteBillById(Long id);
    List<ShowDataBill> getAllBills();
    List<ShowDataBill> getBillByUserId(Long userId);
    Bill getBillById(Long id);
}
