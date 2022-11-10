package com.atm.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atm.dscatalog.entities.Category;
import com.atm.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired/*Essa annotation garante q a variavel seja uma dependencia de CategoryRepository*/
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)/*Garante que este metodo ganhe um dos atributos do ACID(Faz tudo ou não faz nada) ou garante q faça uma transação*/
	public List<Category> findAll(){
		return repository.findAll();
	}
}
