package com.obdsimulation.obdautodata.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private int active;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide your username")
    private String username;

    @Column(name = "password")
    @Length(min = 7, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

	//@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<UserRole> roles;
	
    // contruct missing

	public List<UserRole> getRoles()
	{
		return this.roles;
	}

	public User setRoles(List<UserRole> roles)
	{
		this.roles = roles;
		return this;
	}


	public String getUsername() {
		return this.username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}
    

	public String getPassword()
	{
		return this.password;
	}

	public User setPassword(String password)
	{
		this.password = password;
		return this;
	}


	public int getActive()
	{
		return this.active;
	}

	public User setActive(int active)
	{
		this.active = active;
		return this;
	}


	public String getLastName()
	{
		return this.lastName;
	}

	public User setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}


	public String getName()
	{
		return this.name;
	}

	public User setName(String name)
	{
		this.name = name;
		return this;
	}


	public String getEmail()
	{
		return this.email;
	}

	public User setEmail(String email)
	{
		this.email = email;
		return this;
	}


	public Long getId()
	{
		return this.id;
	}

	public User setId(Long id)
	{
		this.id = id;
		return this;
	}




}