package com.andrew.greatideas.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andrew.greatideas.models.Idea;
import com.andrew.greatideas.models.User;
import com.andrew.greatideas.services.UserService;

@Controller
public class Users {
	
	private final UserService userService;
	
	public Users(UserService uS) {
		userService = uS;
	}

	@RequestMapping("/")
	public String loginreg(Model model) {
		model.addAttribute("user", new User());
		return "login_registration";
	}
	
	@PostMapping("/process_registration")
	public String process_registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		
		System.out.println("\n\n<<-------------Processing User Registration------------->>\n\n");
		
		System.out.println("Name: " + user.getName());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: " + user.getPassword());
		
		if ( result.hasErrors() ) {
			return "login_registration";
		}
		
		if ( userService.findByEmail( user.getEmail() ) != null ) {
			model.addAttribute( "exists", "A user with this email already exists." );
			model.addAttribute( "user", new User() );
			return "login_registration";
		}
		
		if ( !user.getPassword().equals( user.getConfirmation() ) ) {
			model.addAttribute( "regError", "Passwords must match.");
			model.addAttribute( "user", new User() );
			return "login_registration";
		}
		
		userService.createUser(user);
		session.setAttribute("name", user.getName());
		session.setAttribute("id", user.getId());
		session.setAttribute("user", userService.findById(user.getId()));
		return "redirect:/dashboard";
	}
	
	@PostMapping("process_login")
	public String process_login(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		
		System.out.println("\n\n<<-------------Processing User Login------------->>\n\n");
		System.out.println("Email: " + user.getEmail());
		System.out.println("Password: "  +user.getPassword());
		
		if (result.hasErrors()) {
			System.out.println("Problem found in login.");
			return "login_registration";
		}
		
		else if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
			System.out.println("Authenticating User");
			
			session.setAttribute("name", userService.findByEmail(user.getEmail()).getName());
			session.setAttribute("id", userService.findByEmail(user.getEmail()).getId());
			
			System.out.println(session.getAttribute("id"));
			System.out.println("Name: " + session.getAttribute("name"));
			return "redirect:/dashboard";
		}
		
		else {
			System.out.println("Email and password does not match.");
			return "login_registration";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		List<Idea> ideas = userService.findAllIdeas();
		model.addAttribute("ideas", ideas);
		
		User user = userService.findById((Long) session.getAttribute("id"));
		model.addAttribute("user", user);
		System.out.println("Displaying user: " + user.getName());
		System.out.println("Displaying user_id: " + user.getId());
		
		return "dashboard";
	}
	
	@GetMapping("/create_idea")
	public String create_idea(HttpSession session, Model model) {
		
		System.out.println("User: " + userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("user", userService.findById((Long) session.getAttribute("id")));
		model.addAttribute("idea", new Idea());
		
		return "create_new_idea";
	}
	
	@PostMapping("/process_new_idea")
	public String process_idea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, Model model) {
		
		if (result.hasErrors()) {
			System.out.println("Problem found in new TV Show.");
			return "create_new_idea";
		}
		
		if (userService.findIdeaByName( idea.getName() ) != null) {
			model.addAttribute("exist", "An idea with this name already exists.");
			model.addAttribute("idea", new Idea());
			return "create_new_idea";
		}
		
		userService.createIdea(idea);
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/show_idea/{id}")
	public String showidea(@PathVariable("id") Long id, Model model, HttpSession session) {
		List<User> users = userService.findIdeaById(id).getUsers();
		model.addAttribute("idea", userService.findIdeaById(id));
		model.addAttribute("users", users);
		model.addAttribute("user", userService.findById((Long) session.getAttribute("id")));
		return "idea_likes_by";
	}
	
	@GetMapping("edit/{id}")
	public String edit_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		
		Idea idea = userService.findIdeaById(id);
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("idea", idea);
		model.addAttribute("user", user);
		return "edit_idea_page";
	}
	
	@PostMapping("/update_idea/{id}")
	public String update_truck(@Valid @ModelAttribute("tvShow") Idea idea, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		
		idea.setId(id);
		
		if(result.hasErrors()) {
			System.out.println("Problem found in update.");
			return "edit_idea_page";
		}
		
		else {
			System.out.println("Updating TV Show information.");
			System.out.println(idea.getName());
			System.out.println(idea.getCreator_name());
			
			userService.updateIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("delete/{id}")
	public String delete_idea(HttpSession session, @PathVariable("id") Long id, Model model) {
		
		Idea idea = userService.findIdeaById(id);
		userService.deleteIdea(idea);
		return "redirect:/dashboard";
	}
	
	@GetMapping("like_idea/{ideaId}/{userId}")
	public String theylikeit( @PathVariable("ideaId") Long ideaId, @PathVariable("userId") Long userId, HttpSession session) {
		Idea idea = userService.findIdeaById(ideaId);
		User user = userService.findById(userId);
		if (!idea.getUsers().contains(user)) {
			List<Idea> ideas = user.getIdeas();
			ideas.add(idea);
			userService.updateUser(user);
		}
		return "redirect:/dashboard";
	}
	
	@GetMapping("unlike_idea/{ideaId}/{userId}")
	public String theyunlikeit( @PathVariable("ideaId") Long ideaId, @PathVariable("userId") Long userId, HttpSession session) {
		Idea idea = userService.findIdeaById(ideaId);
		User user = userService.findById(userId);
		if (idea.getUsers().contains(user)) {
			List<Idea> ideas = user.getIdeas();
			ideas.remove(idea);
			userService.updateUser(user);
		}
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
