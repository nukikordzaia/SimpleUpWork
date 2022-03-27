package com.simpleupwork.model.application;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Skill> requiredSkills;

	private String name;

	private String description;

	private long salary;

	private boolean active;

	private Date createTime;

	private Date lastUpdateTime;

	private Date startDate;

	private Date endDate;
}
