package model.application;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
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
