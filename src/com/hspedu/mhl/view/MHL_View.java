package com.hspedu.mhl.view;
import com.hspedu.mhl.domain.*;
import com.hspedu.mhl.service.BillService;
import com.hspedu.mhl.service.DiningTableService;
import com.hspedu.mhl.service.EmployeeService;
import com.hspedu.mhl.service.MenuService;
import com.hspedu.mhl.utils.Utility;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

public class MHL_View {

    // 控制是否退出菜单
    private boolean loop = true;
    // 接收用户的选择
    private String key ="";
    // 定义EmployeeService 属性
    private EmployeeService employeeService = new EmployeeService();
    // 定义DiningTableService 属性
    private DiningTableService diningTableService = new DiningTableService();
    // 定义MenuService 属性
    private MenuService menuService = new MenuService();
    // 定义BillService 属性
    private BillService billService = new BillService();
    public static void main(String[] args) {
        new MHL_View().mainMenu();

    }

    // 显示结账
    public void payBill() {
        System.out.println("====结账服务====");
        System.out.print("请选择要结账的餐桌编号(-1退出): ");
        int payTableId = Utility.readInt();
        if(payTableId == -1){
            System.out.println("====取消结账====");
            return;
        }
        // 验证餐桌是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(payTableId);
        if(diningTable == null) {
            System.out.println("====结账的餐桌不存在====");
            return;
        }
        if(billService.hasPayBillByDiningTableId(payTableId)) {
            System.out.println("====该餐桌没有未结账账单====");
            return;
        }
        System.out.print("结账的方式(现金/支付宝/微信) 回车表示退出: ");
        String payMode = Utility.readString(20);
        if("".equals(payMode)){
            System.out.println("====取消结账====");
            return;
        }

        System.out.print("确认是否结账(Y/N): ");
        char payConfirm = Utility.readConfirmSelection();
        if (payConfirm == 'Y') { // 确认付款

            // 调用我们写的方法
            if(billService.payBill(payTableId, payMode)) {
                System.out.println("====完成结账====");
            } else {
                System.out.println("====结账失败====");
            }

        } else {
            System.out.println("====取消结账====");
        }



    }
    // 显示账单
    public void listBill() {
//        List<Bill> allBills = billService.showBillList();
//        System.out.println("编号\t\t菜品号\t菜品量\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t状态");
//        for (Bill bill_element : allBills) {
//            System.out.println(bill_element);
//        }
//        System.out.println("====显示完毕===");
        List<MultiTableBean> allBills = billService.showBillList2();
        System.out.println("编号\t\t菜品号\t菜品量\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t状态\t\t菜品\t\t\t单价");
        for (MultiTableBean bill_element : allBills) {
            System.out.println(bill_element);
        }
        System.out.println("====显示完毕===");

    }
    // 完成点餐:
    public void orderMenu() {
        System.out.println("====点餐服务====");
        System.out.print("请输入点餐的桌号(-1退出): ");
        int orderDiningTableId = Utility.readInt();
        if(orderDiningTableId == -1){
            System.out.println("====取消点餐====");
            return;
        }
        System.out.print("请输入点餐的菜品号(-1退出): ");
        int orderMenuId = Utility.readInt();
        if(orderMenuId == -1){
            System.out.println("====取消点餐====");
            return;
        }
        System.out.print("请输入点餐的菜品量(-1退出): ");
        int orderQuantity = Utility.readInt();
        if(orderQuantity == -1){
            System.out.println("====取消点餐====");
            return;
        }

        // 验证餐桌号是否存在
        DiningTable diningTableById = diningTableService.getDiningTableById(orderDiningTableId);
        if(diningTableById == null) {
            System.out.println("====餐桌号不存在====");
            return;
        }
        // 验证菜品号是否正确
        Menu menuItem = menuService.getMenuItem(orderMenuId);
        if(menuItem == null) {
            System.out.println("====菜品号不存在====");
            return;
        }

        // 点餐
        if(billService.orderMenu(orderMenuId, orderQuantity, orderDiningTableId)) {
            System.out.println("====点餐成功====");
        } else {
            System.out.println("====点餐失败====");
        }



    }
    // 菜品方法:
    // 显示所有菜品
    public void listMenu() {
        System.out.println("=============显示所有菜品============");
        List<Menu> currentMenu = menuService.showMenuList();
        System.out.println("菜品编号:\t\t菜名:\t\t类别:\t\t价格:");
        for (Menu menu_element : currentMenu) {
            System.out.println(menu_element.getId()+"\t\t\t\t"
                    +menu_element.getName()+"\t\t"
                    +menu_element.getType()+"\t\t\t"
                    +menu_element.getPrice());
        }
        System.out.println("===============显示完毕==============");
        System.out.println();
    }

    // 方法:
    // 显示所有餐桌状态
    public void listIdleTable() {
        System.out.println("=============显示餐桌状态============");
        List<DiningTable> currentAvailable= diningTableService.showTableList();
        System.out.println("== Table ID:\t\tTable Status: ==");
        for (DiningTable table : currentAvailable) {
            System.out.println("== "+table.getId()+"\t\t\t\t"+table.getStatus()+"\t\t  ==");
        }
        System.out.println("===============显示完毕==============");
        System.out.println();
    }

    // 完成订座
    public void orderDiningTable() {
        System.out.println("========预定餐桌========");
        System.out.print("请选择要预定的餐桌编号(-1退出): ");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("=====取消预定餐桌=====");
            return;
        }
        // 该方法得到的是 Y 或者 N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') { // 预定

            // 根据OrderId 返回 对应DiningTable对象， 如果为null，说明该对象不存在
            DiningTable diningTableById = diningTableService.getDiningTableById(orderId);
            if (diningTableById == null) { //如果搜索的 diningTable为空 查无此桌
                System.out.println("====你预定的餐桌不存在====");
                return;
            }
            // 仍需要判断该桌的状态 状态为“idle”
            if (!("idle".equals(diningTableById.getStatus()))){ // 我们去反，说明当前这个餐桌不是“空”状态
                System.out.println("====该餐桌已被预定或就餐中====");
                return;
            }
            // 到这里 说明该桌状态为‘空’，可以进行预定
            System.out.print("预定人的名字: ");
            String ordererName = Utility.readString(50);
            System.out.print("预定人的电话: ");
            String ordererTelphone = Utility.readString(50);

            // 更新餐桌状态
            if (diningTableService.orderDiningTable(orderId, ordererName, ordererTelphone)){
                System.out.println("=====预定成功=====");
            } else {
                System.out.println("=====预定失败=====");
            }
        } else {
            System.out.println("=====取消预定餐桌=====");
            return;
        }

    }

    // 显示主菜单
    public void mainMenu() {

        while (loop){

            System.out.println("=============满汉楼=============");
            System.out.println("==\t\t 1 登录满汉楼\t\t\t ==" );
            System.out.println("==\t\t 1 退出满汉楼\t\t\t ==" );
            System.out.println("===============================");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key) {
                case "1" :
                    System.out.print("请输入员工号: ");
                    String empId = Utility.readString(50);
                    System.out.print("请输入密 码: ");
                    String pwd = Utility.readString(50);
                    // 到数据库去判断
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if(employee != null){
                        System.out.println("[登录成功] 欢迎回来!"+employee.getName());
                        // 显示二级菜单, 这里二级菜单
                        while (loop){
                            System.out.println("=============满汉楼(二级菜单)=============");
                            System.out.println("==\t\t\t 1 显示餐桌状态 \t\t\t ==");
                            System.out.println("==\t\t\t 2 预定餐桌 \t\t\t\t ==");
                            System.out.println("==\t\t\t 3 显示所有菜品 \t\t\t ==");
                            System.out.println("==\t\t\t 4 点餐服务 \t\t\t\t ==");
                            System.out.println("==\t\t\t 5 查看账单 \t\t\t\t ==");
                            System.out.println("==\t\t\t 6 结账 \t\t\t\t\t ==");
                            System.out.println("==\t\t\t 9 退出满汉楼 \t\t\t ==");
                            System.out.println("=======================================");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1" :
                                    listIdleTable();
                                    break;
                                case "2" :
                                    orderDiningTable();
                                    break;
                                case "3" :
                                    listMenu();
                                    break;
                                case "4" :
                                    orderMenu();
                                    break;
                                case "5" :
                                    listBill();
                                    break;
                                case "6" :
                                    payBill();
                                    break;
                                case "9" :
                                    System.out.println("退出满汉楼");
                                    loop = false;
                                    break;
                                default :
                                    System.out.println("你的输入有误,请重新输入");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Warning: [登录失败]");
                    }
                    break;
                case "2" :
                    System.out.println("你退出了满汉楼系统");
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误,请重新输入.");
            }
        }
    }

}
