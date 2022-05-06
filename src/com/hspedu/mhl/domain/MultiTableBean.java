package com.hspedu.mhl.domain;

import java.time.LocalDateTime;

/**
 *
 * 这是一个javabean 可以和多张报表进行映射
 */
public class MultiTableBean {

    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer quantity;
    private Double total_cost;
    private Integer diningTableId;
    private LocalDateTime billDate;
    private String payment_status;
    //添加一个来自menu表的列 name
    // 思考：这里的属性名是否一定要和表的列名保持一致？
    // 重要：如果这里你把 private String name 改成 private String name2
    //      并且改了所有的name-> name2 在通过sql的query后， name = xxx
    //      会开始自动进行封装，封装给setName 因为database的 是name，
    //      他会去找JavaBean 的setName 而不是setName2，如果db的属性和domain的不匹配
    //      多半会出问题（就会null 没有去操作），be cautious 需谨慎

    // 解决方法：e.g.
    // SELECT bill.*, name AS name2
    // FROM bill, menu
    // WHERE bill.menuId = menu.id
    // 可解决上方的问题 这样setName2 就会被利用到
    // 答案：可以不一致，但是需要sql做相应的修改，规范需要保持一致（建议）
    private String name;
    //再添加一个来自menu表的列 price 用于查看账单时使用
    private Double price;

    public MultiTableBean() {

    }

    public MultiTableBean(Integer id, String billId, Integer menuId, Integer quantity, Double total_cost, Integer diningTableId, LocalDateTime billDate, String payment_status, String name, Double price) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.total_cost = total_cost;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.payment_status = payment_status;
        this.name = name;
        this.price = price;
    }

    // 同时要给name（来自menu表的name）一个setter 和 getter

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "\t\t" + payment_status +
                "\t\t" + name +
                "\t\t" + price;
    }
}
