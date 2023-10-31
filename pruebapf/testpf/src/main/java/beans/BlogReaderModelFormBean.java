package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dto.BlogsDto;
import dto.BlogsReadersDto;
import dto.ReadersDto;
import services.BlogsImplServices;
import services.ReadersImplServices;

@Named("blogreaderFormBean")
@SessionScoped
public class BlogReaderModelFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4352077517623512250L;

	@Inject
	private ReadersImplServices readersImplServices;

	@Inject
	private BlogsImplServices blogsImplServices;

	private ReadersDto objectReadersQ;
	private BlogsDto objectBlogQ;
	private ReadersDto objectReader;
	private BlogsDto objectBlog;

	private BlogsReadersDto blogsReadersDto;
	private BlogsReadersDto blogsReadersDelete;

	private ArrayList<ReadersDto> readers;
	private ArrayList<BlogsDto> blogs;
	private ArrayList<BlogsReadersDto> blogsReaders;

	public BlogReaderModelFormBean() {

	}

	@PostConstruct
	public void listas() {
		objectReadersQ = new ReadersDto();
		objectReader = new ReadersDto();
		objectBlogQ = new BlogsDto();
		objectBlog = new BlogsDto();
		blogsReadersDto = new BlogsReadersDto();
		readers = new ArrayList<ReadersDto>();
		blogs = new ArrayList<BlogsDto>();
		blogsReaders = new ArrayList<BlogsReadersDto>();
		readers = (ArrayList<ReadersDto>) readersImplServices.getReadersDto(objectReader);
		blogs = (ArrayList<BlogsDto>) blogsImplServices.getBlogsDto(objectBlog);

	}

	public ReadersImplServices getReadersImplServices() {
		return readersImplServices;
	}

	public void setReadersImplServices(ReadersImplServices readersImplServices) {
		this.readersImplServices = readersImplServices;
	}

	public ArrayList<ReadersDto> getReaders() {
		return readers;
	}

	public void setReaders(ArrayList<ReadersDto> readers) {
		this.readers = readers;
	}

	public ReadersDto getObjectReadersQ() {
		return objectReadersQ;
	}

	public void setObjectReadersQ(ReadersDto objectReadersQ) {
		this.objectReadersQ = objectReadersQ;
	}

	public BlogsDto getObjectBlogQ() {
		return objectBlogQ;
	}

	public void setObjectBlogQ(BlogsDto objectBlogQ) {
		this.objectBlogQ = objectBlogQ;
	}

	public ReadersDto getObjectReader() {
		return objectReader;
	}

	public void setObjectReader(ReadersDto objectReader) {
		this.objectReader = objectReader;
	}

	public BlogsDto getObjectBlog() {
		return objectBlog;
	}

	public void setObjectBlog(BlogsDto objectBlog) {
		this.objectBlog = objectBlog;
	}

	public BlogsImplServices getBlogsImplServices() {
		return blogsImplServices;
	}

	public void setBlogsImplServices(BlogsImplServices blogsImplServices) {
		this.blogsImplServices = blogsImplServices;
	}

	public BlogsReadersDto getBlogsReadersDto() {
		return blogsReadersDto;
	}

	public void setBlogsReadersDto(BlogsReadersDto blogsReadersDto) {
		this.blogsReadersDto = blogsReadersDto;
	}

	public ArrayList<BlogsDto> getBlogs() {
		return blogs;
	}

	public void setBlogs(ArrayList<BlogsDto> blogs) {
		this.blogs = blogs;
	}

	public ArrayList<BlogsReadersDto> getBlogsReaders() {
		return blogsReaders;
	}

	public void setBlogsReaders(ArrayList<BlogsReadersDto> blogsReaders) {
		this.blogsReaders = blogsReaders;
	}

	public BlogsReadersDto getBlogsReadersDelete() {
		return blogsReadersDelete;
	}

	public void setBlogsReadersDelete(BlogsReadersDto blogsReadersDelete) {
		this.blogsReadersDelete = blogsReadersDelete;
	}

	public String agregar() {
		try {
			readers = (ArrayList<ReadersDto>) readersImplServices.getReadersDto(objectReader);
			blogs = (ArrayList<BlogsDto>) blogsImplServices.getBlogsDto(objectBlog);
			return "blogsReadersEdit";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String buscar() {
		try {
			this.blogsReaders = (ArrayList<BlogsReadersDto>) readersImplServices.getBlogsReadersDto(objectReadersQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String buscarReaders() {
		try {
			this.readers = (ArrayList<ReadersDto>) readersImplServices.getReadersDto(objectReadersQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editar(Long bId, Long rId) {
		try {
			this.blogsReadersDto = bId != null && rId != null
					? this.blogsReaders.stream()
							.filter(blogreader -> blogreader.getBlogsDto().getId() == bId
									&& blogreader.getReadersDto().getId() == rId)
							.findFirst().orElse(null)
					: new BlogsReadersDto();
			ReadersDto objR = new ReadersDto();
			objR.setId(rId);
			// escojo el lector y lleno la lista de los blogs
			readers = (ArrayList<ReadersDto>) readersImplServices.getReadersDto(objR);
			blogs = (ArrayList<BlogsDto>) blogsImplServices.getBlogsDto(new BlogsDto());
			this.blogsReadersDelete = this.blogsReadersDto;
			return "/pages/blogsreaders/blogsreaders_edition.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String borrar(Long bId, Long rId) {
		try {
			BlogsReadersDto dto = new BlogsReadersDto();
			dto.setbId(bId);
			dto.setrId(rId);
			readersImplServices.deleteReaderBlogs(dto);

			this.buscar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void limpiar() {
		this.objectReadersQ = new ReadersDto();
		this.blogsReaders = new ArrayList<>();
	}

	public String anterior() {
		try {
			return "blogsReadersQuery";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizar() {
		try {

			ReadersDto objRB = new ReadersDto();
			objRB.setbId(blogsReadersDto.getbId());
			objRB.setrId(blogsReadersDto.getrId());
			ArrayList<BlogsReadersDto> l = (ArrayList<BlogsReadersDto>) readersImplServices.getBlogsReadersDto(objRB);
			if (l != null && !l.isEmpty()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de información",
								"El Lector: " + l.get(0).getReadersDto().getNames()
										+ " ya tiene registrado o ha leído el Blog: "
										+ l.get(0).getBlogsDto().getTitle()));

			} else {
				readersImplServices.createBlogsReaders(this.blogsReadersDto);
				this.buscar();
				return anterior();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
