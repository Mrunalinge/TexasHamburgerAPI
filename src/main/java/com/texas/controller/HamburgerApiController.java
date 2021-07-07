package com.texas.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texas.Service.LocationService;
import com.texas.Service.MenuService;
import com.texas.dto.LocationsDTO;
import com.texas.dto.MenuDTO;
import com.texas.response.Response;
import com.texas.response.ResponseMetaData;
import com.texas.response.StatusMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/menu")
@Api(tags = {"menu"})
@SwaggerDefinition(tags = {@Tag(name="menu", description = "Menu Endpoint for Texas Hamburger API")})
public class HamburgerApiController {
	
	public static final Logger log = LoggerFactory.getLogger(HamburgerApiController.class);
	
	@Autowired
	MenuService menuService;	
	@Autowired
	LocationService locService;
	
	

	@GetMapping(value="/menus")
	@ApiOperation(value="Find all the menus")
	public Response<List<MenuDTO>> getAllMenus(){
 	log.info("Controller::Geeting Menus");	
	 List<MenuDTO> listDTO=	menuService.getAllMenus();
			 
	 return Response.<List<MenuDTO>>builder().responseMeta(ResponseMetaData.builder()
			 .statusCode(200).message(StatusMessage.SUCCESS.name()).build())
			 .data(listDTO).build();
	}
	
	@GetMapping(value="/menusPaging", params= {"pageNo", "pageSize"})  //,"sortBy" @RequestParam(defaultValue = "") String sortBy
	@ApiOperation(value="Get all menus with pagination")
	public Response<List<MenuDTO>> getAllMenusPaging(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "3") Integer pageSize){
		log.info("Controller::Getting Menu By Paginating");
		
		List<MenuDTO> menuList = new ArrayList<MenuDTO>();
		menuList = menuService.getAllMenusPagination(pageNo,pageSize);		
		return Response.<List<MenuDTO>>builder().responseMeta(ResponseMetaData.builder().statusCode(200)
				           .message(StatusMessage.SUCCESS.name()).build())
				           .data(menuList).build();
		   
			}
	
	@GetMapping(value="/menu/{zip}")
	@ApiOperation(value="Get Menu for a particular location using zipcode")
	public Response<MenuDTO> getMenu(@PathVariable String zip){
		log.info("Controller::Getting Menu For zipcode "+zip);
		MenuDTO menuDto=menuService.getMenuByZip(zip);	
		return Response.<MenuDTO>builder().responseMeta(ResponseMetaData.builder()
				.statusCode(200).message(StatusMessage.SUCCESS.name()).build())
		        .data(menuDto).build();
		
		
	}
	@PostMapping(value="/createMenu", consumes = "application/json")
	@ApiOperation(value="Create a new menu")
	public Response<MenuDTO> createMenu(@RequestBody MenuDTO dto){
		log.info("Controller::Creating Menu" +dto);
		MenuDTO menuDTO = menuService.createMenu(dto);
		return Response.<MenuDTO>builder().responseMeta(ResponseMetaData.builder().statusCode(200)
				.message(StatusMessage.SUCCESS.name()).build()).data(menuDTO).build();
	}
	
	@PutMapping(value="/updateMenu/{zipCode}")
	@ApiOperation(value="Update menu")
	public Response<MenuDTO> updateMenu(@RequestBody MenuDTO menu, @PathVariable String zipCode){
		log.info("Controller::Updating Menu");
		MenuDTO menuDTO= menuService.updateMenu(menu, zipCode);		
		return Response.<MenuDTO>builder().responseMeta(ResponseMetaData.builder().statusCode(200)
				.message(StatusMessage.SUCCESS.name()).build())
				.data(menuDTO)
				.build();
	}
	
	@DeleteMapping(value="/deleteMenu/{zipCode}")
	@ApiOperation(value="Delete menu at a particular location by giving zipcode of that place")
	public Response<Boolean> deleteMenu(@PathVariable String zipCode){
		log.info("Controller::Deleting Menu");
		boolean menuDto = menuService.deleteMenu(zipCode);		
		return Response.<Boolean>builder().responseMeta(ResponseMetaData.builder().statusCode(200).message(StatusMessage.SUCCESS.name())
				.build()).data(menuDto).build();
	}
	
	
	
	@PostMapping(value="/addLoc")
	@ApiOperation(value="Add new location")
	public Response<LocationsDTO> addLocation(@RequestBody LocationsDTO location){	
		log.info("Controller::Adding Location");	
		LocationsDTO locDTO = locService.addLocation(location);
		return Response.<LocationsDTO>builder().responseMeta(ResponseMetaData.builder().statusCode(200)
				.message(StatusMessage.SUCCESS.name()).build())
				.data(locDTO).build();
		
	}
	
	
//	@PostMapping(value="/createMenu")
//	public Response<MenuDTO> createMenu(@RequestBody MenuDTO menuDto){
//		System.out.println("in controller "+menuDto);
//		MenuDTO menudt= menuService.createMenu(menuDto);
//		return Response.<MenuDTO>builder().responseMeta(ResponseMetaData.builder().statusCode(200)
//				.message(StatusMessage.SUCCESS.name()).build())
//				.data(menudt)
//				.build();
//	}
	

}
