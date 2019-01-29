package com.andrew.greatideas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

//<<---------------User Model--------------->>

@Entity
@Table(name="ideas")
public class Idea {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=255, message="Please input a name greater than 3 characters")
	private String name;
	
	private Long creator_id;
	
	private String creator_name;
	
	@Column (updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
//	<<---------------Many To Many Relationship--------------->>
	
	@ManyToMany(mappedBy="ideas")
	private List<User> users;
	
//	<<---------------Defining Constructor--------------->>
	
	public Idea() {

	}

	public Idea(Long id, String name, Long creator_id, String creator_name, Date createdAt, Date updatedAt, List<User> users) {
	
		this.id = id;
		this.name = name;
		this.creator_id = creator_id;
		this.creator_name = creator_name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
	}
	
//	<<---------------Getters and Setters--------------->>
	
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

	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}
	
	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
