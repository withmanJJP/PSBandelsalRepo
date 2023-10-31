package dto;

import java.io.Serializable;

public class BlogsReadersDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679101706662611081L;

	private long rId;

	private long bId;

	private BlogsDto blogsDto;

	private ReadersDto readersDto;

	public BlogsReadersDto() {
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

	public BlogsDto getBlogsDto() {
		return blogsDto;
	}

	public void setBlogsDto(BlogsDto blogsDto) {
		this.blogsDto = blogsDto;
	}

	public ReadersDto getReadersDto() {
		return readersDto;
	}

	public void setReadersDto(ReadersDto readersDto) {
		this.readersDto = readersDto;
	}

}
