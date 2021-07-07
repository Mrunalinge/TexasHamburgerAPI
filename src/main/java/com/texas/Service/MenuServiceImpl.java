package com.texas.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.texas.controller.HamburgerApiController;
import com.texas.dto.DishDTO;
import com.texas.dto.LocationsDTO;
import com.texas.dto.MenuDTO;
import com.texas.entity.Dish;
import com.texas.entity.Locations;
import com.texas.entity.Menu;
import com.texas.exception.MenuServiceExceptionHandler;
import com.texas.repository.DishesRepo;
import com.texas.repository.LocationRepo;
import com.texas.repository.MenuRepo;

@Service
public class MenuServiceImpl implements MenuService {
	
	public static final Logger log = LoggerFactory.getLogger(HamburgerApiController.class);
	
	@Autowired
	MenuRepo menuRepository;
	@Autowired
	DishesRepo dishRepo;
	@Autowired
	LocationRepo locRepo;
	

	@Override
	public List<MenuDTO> getAllMenus() {
		log.info("Menu Service Implementtion::get all menus");
		List<Menu> menus;
		List<MenuDTO> dtoList = new ArrayList<MenuDTO>();
		try {
		  menus=  menuRepository.findAll();
	      menus.forEach(menu-> {
	    	MenuDTO dtoObj = new MenuDTO();
	    	BeanUtils.copyProperties(menu, dtoObj);
	    	dtoList.add(dtoObj);
	    });
		}catch (Exception e) {
			 log.error("Error while fetching the menus");
			 throw new MenuServiceExceptionHandler("Error while fetching menus",e);
		}
		return dtoList;
	}

	@Override
	public MenuDTO getMenuByZip(String zip) {
		
		log.info("Menu Service Implementtion::get menu by zip code");
		MenuDTO menuDTO = new MenuDTO();
		try {
		
			Locations loc = locRepo.findLocationsByzipCode(zip);
			if(loc == null) {
				throw new  NullPointerException();
			}
			Menu menu = menuRepository.findMenuByLocationsID(loc.getId());
		
			List<Dish> dish = menu.getDish();
			List<DishDTO> dishDTO = new ArrayList<>();
		
			dish.forEach(dto->{
				DishDTO dishDto = new DishDTO();
				BeanUtils.copyProperties(dto, dishDto);
				//dishDto= mapDishToDTO(dto);
				dishDTO.add(dishDto);
			});
             menuDTO.setDish(dishDTO); menuDTO.setId(menu.getId());
			}catch(NullPointerException e) {
				log.error("There is no location with the given zipcode");
				throw new MenuServiceExceptionHandler("There is no location with the given zipcode", e);
			} catch (Exception e) {
				log.error("There is no menu for the given zipcode");
				throw new MenuServiceExceptionHandler("The menu could not be found for the given zipcode", e);
			}
		return menuDTO;
	}

	@Override
	@Transactional
	public MenuDTO createMenu(MenuDTO menuDto) {
		log.info("Menu Service Implementtion::Create menu");
		
	   Menu menu = new Menu();
	   MenuDTO menuDTOFinal=new MenuDTO();
	   try {
		  
		   menu = mapDTOToEntity(menuDto);  
		  
	       Menu menuFinal= menuRepository.save(menu);
		 
	       menuDTOFinal = mapMenuToDTO(menuFinal);
	      
	   }catch (Exception e) {
		   log.error("Error while creating menu");
		   throw new MenuServiceExceptionHandler("Failed to create new Menu", e);
	}		
		return menuDTOFinal;
	}

	@Override
	public MenuDTO updateMenu(MenuDTO menuDto, String zipCode) {
		log.info("Menu Service Implementtion::Update menu");	
		 MenuDTO menuDTO =null;
		 try {
		//find menu with given zipcode
		    Locations chkLoc = locRepo.findLocationsByzipCode(zipCode);
		    if(chkLoc==null) {
	    	//throw new NullPointerException();
	       }else {
		    Menu menuToUpdate = menuRepository.findMenuByLocationsID(chkLoc.getId());    
		    //get the current menu 
		    List<Dish>  dishEntity= menuToUpdate.getDish();
		    //get the menu to update
		    List<DishDTO> dishList = menuDto.getDish();
		    //generate a map with orignal dishes names and their indexes
		    Map<String, Integer> map = generateMap(dishEntity);
		  //chk if the dish already exists in the map or orignal entity. If it does updtae the entity. Else add new dish to given enity and update
		    for(DishDTO dto: dishList) {
		    	Dish tempDish = new Dish();
		    	BeanUtils.copyProperties(dto, tempDish);
		    	// tempDish= mapDTODish(dto);
		    	if(map.containsKey(tempDish.getDishName())) {
		    		int index = map.get(tempDish.getDishName());
		    		dishEntity.remove(index);
		    		dishEntity.add(tempDish);
		    	}else {
		    		dishEntity.add(tempDish);
		    	}  	
		    }	    
			   menuToUpdate.setDish(dishEntity);
			   Menu menuUpdated = menuRepository.save(menuToUpdate);   
			   menuDTO=  mapMenuToDTO(menuUpdated);
	       }
		 }catch (NullPointerException e) {
             log.error("Location/Menu does not exist");
             throw new MenuServiceExceptionHandler("Menu with given zip code does not exist",e);
		}catch (Exception e) {
			  log.error("Location/Menu does not exist");
	          throw new MenuServiceExceptionHandler("Menu with given zip code does not exist",e);
		}
		
		return menuDTO;
	}
	
	@Override
	public boolean deleteMenu(String zip) {
		
		log.info("Menu Service Implementtion::Delete menu by zip code");
		Locations loc =null;
		try {
	    loc = locRepo.findLocationsByzipCode(zip);
	    if(loc == null) {
	    	throw new NullPointerException();
	    }

	   	Menu menu = menuRepository.findMenuByLocationsID(loc.getId());
	   	if(menu!=null) {
	     	 menuRepository.delete(menu);
	   	}
	   	else {
	   		throw new NullPointerException();
	   	}
		}catch(NullPointerException e) {
			log.error("Location/Menu does not exist");
	        throw new MenuServiceExceptionHandler("Menu with given zip code does not exist",e);
		}
		 catch (Exception e) {
			 log.error("Location/Menu does not exist");
		     throw new MenuServiceExceptionHandler("Menu with given zip code does not exist",e);
		}
		return true;
	}
	
	@Override
	public List<MenuDTO> getAllMenusPagination(Integer pageNo, Integer pageSize) {
		log.info("Menu Service Implemention::Paging menu");
		List<MenuDTO> menuDTO = new ArrayList<>();
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<Menu> page = menuRepository.findAll(paging);
			
			if(!page.hasContent()) {
				throw new Exception();
			}else {
				List<Menu> menu = page.toList();
				
				for(Menu tmp: menu) {
					MenuDTO tempDto = mapMenuToDTO(tmp);
					menuDTO.add(tempDto);
				}
			}
		}catch (Exception e) {
			log.error("Error while getting data from Paginating");
			throw new MenuServiceExceptionHandler("Could not fetch all the menus with Paging",e);
		}
		return menuDTO;
	}
	
	
	public Map<String, Integer> generateMap(List<Dish> dishes){
		Map<String, Integer> result = new HashMap<>();
		int index=0;
		for(Dish dish: dishes) {
			result.put(dish.getDishName(), index);
			index++;
		}
		
		return result;
	}

	public MenuDTO mapMenuToDTO(Menu menuFinal) {
		
		LocationsDTO dto= new LocationsDTO();
	    BeanUtils.copyProperties(menuFinal.getLocations(), dto);
	    //dto= mapLocationToDTO(menuFinal.getLocations());
	    List<DishDTO> dishDtos   = new ArrayList<DishDTO>();
	    List<Dish> dsh = menuFinal.getDish();
	      dsh.forEach(dis->{
	    	  DishDTO ddto = new DishDTO();
	    	  BeanUtils.copyProperties(dis, ddto);
	    	  //ddto= mapDishToDTO(dis);
	    	  dishDtos.add(ddto);
	      });
	      	      
	       MenuDTO menuDTOFinal = new MenuDTO();
	       
	       menuDTOFinal.setDish(dishDtos);menuDTOFinal.setLocation(dto);menuDTOFinal.setId(menuFinal.getId());
		
		return menuDTOFinal;
	}
	
	public Menu mapDTOToEntity(MenuDTO menuDto) {
		Menu menu = new Menu();
	    BeanUtils.copyProperties(menuDto, menu);
		List<DishDTO> dishDtos = menuDto.getDish();
	
		List<Dish> dishEn = new ArrayList<>();
		   
		   for(DishDTO dddto: dishDtos) {
			   Dish dishes = new Dish();
			   BeanUtils.copyProperties(dddto, dishes);
			  // dishes= mapDTODish(dddto);
			   dishes.setMenu(menu);
			   dishEn.add(dishes);
		   }
		   menu.setDish(dishEn);
		   LocationsDTO locDTO = menuDto.getLocation();
		   Locations loc = new Locations();
	       Locations chkLoc = locRepo.findLocationsByzipCode(locDTO.getZipCode());
	       if(chkLoc!=null) {
	    	   menu.setLocations(chkLoc);
	    	   chkLoc.setMenu(menu);
	       }else {
	    	   BeanUtils.copyProperties(locDTO, loc);
	    	 //  loc= mapLocationDTOEntity(locDTO);
	    	   menu.setLocations(loc);
	    	   loc.setMenu(menu);
	    	   locRepo.save(loc);
	       }
		
		return menu;
	}

	public LocationsDTO mapLocationToDTO(Locations location) {
		
		LocationsDTO locate = new LocationsDTO();
		locate.setCity(location.getCity()); locate.setZipCode(location.getZipCode()); locate.setEndTime(location.getEndTime());
		locate.setStartTime(location.getStartTime()); locate.setStreetAddress(location.getStreetAddress());
		return locate;
	}
	
	public Locations mapLocationDTOEntity(LocationsDTO location) {
		Locations locate = new Locations();
		locate.setCity(location.getCity()); locate.setZipCode(location.getZipCode()); locate.setEndTime(location.getEndTime()); locate.
		setStartTime(location.getStartTime()); locate.setStreetAddress(location.getStreetAddress());
		return locate;
		
	}
	
	public DishDTO mapDishToDTO(Dish dish) {
		DishDTO dishDto = new DishDTO();
		
		dishDto.setDishName(dish.getDishName());dishDto.setIngredients(dish.getIngredients()); dishDto.setQuantity(dish.getQuantity());
		dishDto.setPrice(dish.getPrice());
		dishDto.setStatus(dish.getStatus());
		
		if(dish.getId()!=0)
			dishDto.setId(dish.getId());
		
		return dishDto;
	}
	
	public Dish mapDTODish(DishDTO dishDto) {
		Dish dish = new Dish();
		dish.setDishName(dishDto.getDishName()); dish.setIngredients(dishDto.getIngredients()); 
		dish.setQuantity(dishDto.getQuantity()); dish.setPrice(dishDto.getPrice());
		dish.setStatus(dishDto.getStatus());
		
		return dish;
		

	}
	
}
