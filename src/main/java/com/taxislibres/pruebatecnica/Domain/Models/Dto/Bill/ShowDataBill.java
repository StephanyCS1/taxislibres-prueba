package com.taxislibres.pruebatecnica.Domain.Models.Dto.Bill;

import com.taxislibres.pruebatecnica.Domain.Models.Bill;
import com.taxislibres.pruebatecnica.Domain.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShowDataBill {
    private Long id;
    private Double totalAmount;
    private String description;
    private User user;

    public ShowDataBill(Bill bill) {
        this.id = bill.getId();
        this.totalAmount = bill.getTotalAmount();
        this.description = bill.getDescription();
        this.user = bill.getUser();
    }

    public ShowDataBill(Long id, User user, Double totalAmount, String description) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.description = description;
        this.user = user;
    }
}
