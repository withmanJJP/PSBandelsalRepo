package models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "blogs_readers")
public class BlogsReaders implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 5957260091778266065L;

	@EmbeddedId
	private BlogsReadersPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "r_id", insertable = false, updatable = false)
	private Readers readers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "b_id", insertable = false, updatable = false)
	private Blogs blogs;

	public BlogsReaders() {

	}

	public BlogsReadersPK getId() {
		return id;
	}

	public void setId(BlogsReadersPK id) {
		this.id = id;
	}


	public Readers getReaders() {
		return readers;
	}

	public void setReaders(Readers readers) {
		this.readers = readers;
	}

	public Blogs getBlogs() {
		return blogs;
	}

	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}

	}
