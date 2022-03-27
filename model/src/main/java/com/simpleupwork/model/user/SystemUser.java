package com.simpleupwork.model.user;

import lombok.Data;
import com.simpleupwork.model.application.Application;
import com.simpleupwork.model.application.Offer;
import com.simpleupwork.model.application.Skill;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "UserEntity")
@Data
public class SystemUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<UserGroup> groups;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Skill> skills;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Application> applications;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Offer> offers;

	private String username;

	private String firstName;

	private String lastName;

	private String title;

	private String bio;

	private String image;

	private String salary;

	private String email;

	private String password;

	private boolean active;

	private Date createTime;
}
