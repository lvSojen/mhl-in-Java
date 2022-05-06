package com.hspedu.mhl.domain;

/**
 * 这是一个javabean 和 dining_table 对应
 *			id INT auto_increment PRIMARY KEY,
 *             status VARCHAR(20) NOT NULL,
 *             orderer_name VARCHAR(50) NOT NULL DEFAULT '',
 *             orderer_telphone VARCHAR(20) NOT NULL DEFAULT ''
 */
public class DiningTable {

    private Integer id;
    private String status;
    private String orderer_name;
    private String orderer_telphone;

    public DiningTable() {

    }

    public DiningTable(Integer id, String status, String orderer_name, String orderer_telphone) {
        this.id = id;
        this.status = status;
        this.orderer_name = orderer_name;
        this.orderer_telphone = orderer_telphone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderer_name() {
        return orderer_name;
    }

    public void setOrderer_name(String orderer_name) {
        this.orderer_name = orderer_name;
    }

    public String getOrderer_telphone() {
        return orderer_telphone;
    }

    public void setOrderer_telphone(String orderer_telphone) {
        this.orderer_telphone = orderer_telphone;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + status;
    }
}
