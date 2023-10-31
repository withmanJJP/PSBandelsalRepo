package dto;

import java.io.Serializable;

public class ReadersDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4851355715419281702L;

	private long id;

//No pertenecen al modelo

	private long rId;
	private long bId;

	private String names;

	public ReadersDto() {
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

	public long getrId() {
		return rId;
	}

	public void setrId(long rId) {
		this.rId = rId;
	}

	public long getbId() {
		return bId;
	}

	public void setbId(long bId) {
		this.bId = bId;
	}

}
