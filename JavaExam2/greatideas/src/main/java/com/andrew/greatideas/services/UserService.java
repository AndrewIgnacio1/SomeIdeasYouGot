package com.andrew.greatideas.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.andrew.greatideas.models.Idea;
import com.andrew.greatideas.models.User;
import com.andrew.greatideas.repositories.IdeaRepository;
import com.andrew.greatideas.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final IdeaRepository ideaRepository;

	public UserService (UserRepository userRepository, IdeaRepository ideaRepository) {
		
		this.userRepository = userRepository;
		this.ideaRepository = ideaRepository;
	}

//	<<---------------Create--------------->>
	
	public User createUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}
	
	public Idea createIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
//	<<---------------Read--------------->>
	
	public Boolean checkIt( User user, String password ) {
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<Idea> findAllIdeas() {
		return ideaRepository.findAll();
	}
	
	public Idea findIdeaById(Long id) {
		return ideaRepository.findById(id).get();
	}
	
	public Idea findIdeaByName(String name) {
		
		Optional<Idea> idea = ideaRepository.findByName( name );
		
		if ( idea.isPresent() ) {
			return idea.get();
		} 
		
		else {
			return null;
		}
	}
	
//	<<---------------Update--------------->>
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public Idea updateIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
//	<<---------------Destroy--------------->>
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public void deleteIdea(Idea idea) {
		ideaRepository.delete(idea);
	}
	
//	<<---------------User Authentication--------------->>	
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			return false;
		}
		
		else {
			
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			
			else {
				return false;
			}
		}
	}
	
}