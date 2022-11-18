package com.atm.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atm.dscatalog.dto.CategoryDTO;
import com.atm.dscatalog.entities.Category;
import com.atm.dscatalog.repositories.CategoryRepository;
import com.atm.dscatalog.services.exceptions.DataBaseException;
import com.atm.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired /*
				 * Essa annotation garante q a variavel seja uma dependencia de
				 * CategoryRepository
				 */
	private CategoryRepository repository;

	@Transactional(readOnly = true) /*
									 * Garante que este metodo ganhe um dos atributos do ACID(Faz tudo ou não faz
									 * nada) ou garante q faça uma transação
									 */
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();

		List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDto;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {

		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(CategoryDTO dto, Long id) {
		Optional<Category> obj = repository.findById(id);
		try {
			Category entity = obj.orElseThrow();
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + e);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
			
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID not found: "+id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
		
	}

}
