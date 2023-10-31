package models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blogs implements Serializable{

private static final long serialVersionUID = -7048138235070466856L;
@Id
@Column(name="id")
private long id;

@Column(name="title")
private String title;

@Column(name="description")
private String description;


public Blogs() {
	
	}


public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}



}
