package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.Menu;

import java.util.List;

/**
 *
 * 完成对menu 表的各种操作(通过调用MenuDAO)
 */
public class MenuService {

    // 定义MenuDAO 属性
    private MenuDAO menuDAO = new MenuDAO();

    // 返回所有的菜品， 返回给界面使用
    public List<Menu> showMenuList() {
        return menuDAO.queryMultiple("SELECT * FROM menu",Menu.class);
    }

    public Menu getMenuItem(int id) {
        return menuDAO.querySingle("SELECT * FROM menu WHERE id = ?", Menu.class, id);
    }

    //public Boolean orderFromMenu()

}
