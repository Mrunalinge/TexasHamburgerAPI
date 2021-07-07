package com.texas.Service;

import java.util.List;

import com.texas.dto.MenuDTO;

public interface MenuService {

	public List<MenuDTO> getAllMenus();
	
	public List<MenuDTO> getAllMenusPagination(Integer pageNo, Integer pageSize);
	
	public MenuDTO getMenuByZip(String zip);
	
	public MenuDTO createMenu(MenuDTO menu);
	
	public MenuDTO updateMenu(MenuDTO menu, String zipCode);
	
	public boolean deleteMenu(String zip);
}
