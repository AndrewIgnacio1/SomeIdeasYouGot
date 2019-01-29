package com.andrew.greatideas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.andrew.greatideas.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	
	public List<Idea> findAll();
	
	Optional<Idea> findByName( String name );
	
	Optional<Idea> findById( Long id );
	
}