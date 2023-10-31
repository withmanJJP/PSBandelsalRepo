package dto;

import java.io.Serializable;

public class BlogsDto implements Serializable {



/**
	 * 
	 */
  private static final long serialVersionUID = -8479529582774679940L;

  private long id;

  private String title;
  
  private String description; 

  public BlogsDto() {
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
