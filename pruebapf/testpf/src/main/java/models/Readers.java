package models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "readers")
public class Readers implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -2760765916049669809L;

@Id
@Column(name="id")
private long id;

@Column(name="names")
private String names;

public Readers() {
	
}


public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public String getNames() {
	return names;
}


public void setNames(String names) {
	this.names = names;
}



}
