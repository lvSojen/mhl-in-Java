package com.hspedu.mhl.domain;

import java.time.LocalDateTime;


/**
 *
 * 这是一个javabean 和 dining_table 对应
 * 		id INT PRIMARY KEY AUTO_INCREMENT,
 *         billId VARCHAR(50) NOT NULL DEFAULT '',
 *         menuId INT NOT NULL DEFAULT 0,
 *         quantity INT NOT NULL DEFAULT 0,
 *         total_cost DOUBLE NOT NULL DEFAULT 0,
 *         diningTableId INT NOT NULL DEFAULT 0,
 *         billDate DATETIME NOT NULL,
 *         payment_status VARCHAR(50) NOT NULL DEFAULT ''
 */
public class Bill {

    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer quantity;
    private Double total_cost;
    private Integer diningTableId;
    private LocalDateTime billDate;
    private String payment_status;

    public Bill() {

    }

    public Bill(Integer id, String billId, Integer menuId, Integer quantity, Double total_cost, Integer diningTableId, LocalDateTime billDate, String payment_status) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.total_cost = total_cost;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.payment_status = payment_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + menuId +
                "\t\t" + quantity +
                "\t\t" + total_cost +
                "\t\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + payment_status;
    }
}
