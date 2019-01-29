package com.andrew.greatideas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

//<<---------------User Model--------------->>

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=255, message="Please input a name greater than 3 characters")
	private String name;
	
	@Email(message="Invalid Email")
	@Size(min=1, max=255, message="Email cannot be blank")
	private String email;
	
	@Size(min=8, max=255, message="Password must be atleast 8 characters")
	private String password;
	
	@Size(min=8, max=255, message="Password must match")
	@Transient
	private String confirmation;
	
	@Column (updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
//	<<---------------Many To Many Relationship--------------->>
	
	@ManyToMany
	@JoinTable(name="user_idea")
	private List<Idea> ideas;
	
//	<<---------------Defining Constructor--------------->>
	
	public User() {

	}

	public User(Long id, String name, String email, String password, String confirmation, Date createdAt, Date updatedAt,
		List<Idea> ideas) {
	
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmation = confirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.ideas = ideas;
	
	}
		
//		<<---------------Getters and Setters--------------->>

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

//	<<---------------Creating/Updating--------------->>
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}