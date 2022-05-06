package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.DiningTableDAO;
import com.hspedu.mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {

    // 定义一个DiningTableDAO 属性
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    // 返回所有餐桌的信息
    public List<DiningTable> showTableList() {

        return diningTableDAO.queryMultiple("SELECT id, status FROM dining_table ORDER BY status=?", DiningTable.class,"active");
    }

    // 根据id, 查询对应的DiningTable 对象
    // 如果返回null ,表示id编号对应的餐桌不存在
    public DiningTable getDiningTableById(int id) {

        // 小技巧,把sql语句放在mysql去测试一下
        return diningTableDAO.querySingle("SELECT * FROM dining_table WHERE id = ?",DiningTable.class,id);
    }

    // 如果餐桌可以预定,调用方法,对其状态进行更新(包括预定人的名字和电话)
    public boolean orderDiningTable(int id, String orderer_name, String orderer_telphone) {

        int update = diningTableDAO.update("UPDATE dining_table SET status='Active'," +
                        "orderer_name=?,orderer_telphone=? WHERE id=?",
                orderer_name, orderer_telphone, id);
        return update >0;
    }

    // 需要提供一个更新餐桌状态的方法
    public boolean updateDiningTableStatus(int id, String status){

        int update = diningTableDAO.update("UPDATE dining_table SET status = ? WHERE id = ?", status, id);
        return update > 0;
    }

    // 提供方法，将指定餐桌设置为控制状态
    public boolean updateDiningTableToFree(int id, String status){

        int update1 = diningTableDAO.update("UPDATE dining_table SET status = ?,orderer_name='',orderer_telphone='' WHERE id = ?", status, id);

        return update1 > 0;
    }

}
