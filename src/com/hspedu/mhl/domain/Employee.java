package com.hspedu.mhl.domain;

/**
 *
 * 这是一个javabean 和 employee 对应
 * 			id INT PRIMARY KEY AUTO_INCREMENT,
 *             empId varchar(50) NOT NULL,
 *             pwd CHAR(32) NOT NULL,
 *             name VARCHAR(50) NOT NULL,
 *             job VARCHAR(50) NOT NULL
 */
public class Employee {
    private Integer id;
    private String empId;
    private String pwd;
    private String name;
    private String job;

    public Employee() {

    }

    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
