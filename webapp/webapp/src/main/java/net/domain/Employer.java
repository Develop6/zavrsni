package net.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employer"
    ,catalog="jobDatabase"
)
public class Employer implements Serializable{

	private static final long serialVersionUID = 5537441462396307202L;
	private Integer id;
	private String name;
	private String address;
	private String link;
	
	private Set<Job> jobs = new HashSet<Job>(0);
	
	public Employer () {}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	@Column(name = "address", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="employer")
	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@Column(name="link", nullable = true, length = 400)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
