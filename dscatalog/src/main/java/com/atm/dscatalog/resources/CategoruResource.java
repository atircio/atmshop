package com.atm.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm.dscatalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoruResource  {
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "jghvku"));
		list.add(new Category(2L, "Eletronics"));
		list.add(new Category(2L, "Emanuel de Marão"));
		list.add(new Category(2L, "Atírcio Matias"));
		return ResponseEntity.ok().body(list);
		
	}
	
}
