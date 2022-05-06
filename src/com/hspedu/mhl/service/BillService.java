package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.BillDAO;
import com.hspedu.mhl.dao.MultiTableDAO;
import com.hspedu.mhl.domain.Bill;
import com.hspedu.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 *
 * 处理和账单相关的业务逻辑
 */
public class BillService {

    // 定义BillDAO属性
    private BillDAO billDAO = new BillDAO();
    // 调用MenuService 服务
    private MenuService menuService = new MenuService();
    // 调用DiningTableService 服务
    private DiningTableService diningTableService = new DiningTableService();

    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    // 思考
    // 编写点餐的方法
    //1. 生成账单
    //2. 需要更新对应餐桌的状态
    //3. 如果成功返回true, 否则返回false
    public boolean orderMenu(int menuId, int quantity, int diningTableId) {
        //生成一个账单,UUID
        String billID = UUID.randomUUID().toString();

        // 将账单生成到bill表
        int update = billDAO.update("INSERT INTO bill VALUES(null,?,?,?,?,?,NOW(),'未结账')",
                billID,
                menuId,
                quantity,
                menuService.getMenuItem(menuId).getPrice() * quantity,
                diningTableId);
        if(update <=0) {
            return false;
        }

        // 需要更新对应餐桌的状态
        return diningTableService.updateDiningTableStatus(diningTableId, "就餐中");
    }

    // 返回所有的账单, 提供给View使用
    public List<Bill> showBillList() {
        return billDAO.queryMultiple("SELECT * FROM bill",Bill.class);
    }

    // 返回所有的账单并带有菜品, 提供给View使用
    public List<MultiTableBean> showBillList2() {
        return multiTableDAO.queryMultiple("SELECT bill.*, menu.name, menu.price " +
                "FROM bill, menu " +
                "WHERE bill.menuId = menu.id", MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {

        Bill bill = billDAO.querySingle("SELECT * FROM bill WHERE diningTableId =? AND payment_status='未结账' LIMIT 0,1", Bill.class, diningTableId);
        return bill == null;
    }

    // 完成结账 [如果餐桌存在，并且该餐桌有未结账的账单
    public boolean payBill(int diningTableId, String payMode ) {

        // 如果这里要使用事务的话，需要用ThreadLocal来解决,框架中比如mybatis 提供了事务支持
        //  事务有rollback，如果一行不行全部否定
        //1. 修改bill表
        int update = billDAO.update("UPDATE bill SET payment_status = ? WHERE diningTableId = ? AND payment_status='未结账'", payMode, diningTableId);
        if(update <=0) {
            return false;
        }
        //2. 修改diningTable表
        // 不要在这里操作，而应该调用DiningTableService方法 ,完成更新，体现各司其职

        if(!(diningTableService.updateDiningTableToFree(diningTableId,"idle"))) {
            return false;
        }
        return true;
    }



}
