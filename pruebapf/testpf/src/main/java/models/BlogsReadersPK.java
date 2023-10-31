package models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class BlogsReadersPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8426700360805525331L;
	@Column(name = "r_id")
	private Long rId;
	@Column(name = "b_id")
	private Long bId;

	public BlogsReadersPK() {
	}

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public Long getbId() {
		return bId;
	}

	public void setbId(Long bId) {
		this.bId = bId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BlogsReadersPK)) {
			return false;
		}
		BlogsReadersPK castOther = (BlogsReadersPK) other;
		return this.rId.equals(castOther.rId) && this.bId.equals(castOther.bId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rId.hashCode();
		hash = hash * prime + this.bId.hashCode();

		return hash;
	}
}