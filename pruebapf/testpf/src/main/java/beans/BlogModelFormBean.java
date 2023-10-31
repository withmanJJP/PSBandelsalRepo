package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
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

@Named("blogFormBean")
@SessionScoped
public class BlogModelFormBean implements Serializable {

	private static final long serialVersionUID = 1376319740144826761L;

	@Inject
	private BlogsImplServices blogsImplServices;

	@Inject
	private ReadersImplServices readersImplServices;

	private BlogsDto objectQ;

	private BlogsDto object;

	private ArrayList<BlogsDto> blogs;

	private String phase;

	public BlogModelFormBean() {

	}

	@PostConstruct
	public void init() {
		this.objectQ = new BlogsDto();
		this.object = new BlogsDto();
		this.blogs = new ArrayList<BlogsDto>();
	}

	public BlogsImplServices getBlogsImplServices() {
		return blogsImplServices;
	}

	public void setBlogsImplServices(BlogsImplServices blogsImplServices) {
		this.blogsImplServices = blogsImplServices;
	}

	public ReadersImplServices getReadersImplServices() {
		return readersImplServices;
	}

	public void setReadersImplServices(ReadersImplServices readersImplServices) {
		this.readersImplServices = readersImplServices;
	}

	public BlogsDto getObjectQ() {
		return objectQ;
	}

	public void setObjectQ(BlogsDto objectQ) {
		this.objectQ = objectQ;
	}

	public BlogsDto getObject() {
		return object;
	}

	public void setObject(BlogsDto object) {
		this.object = object;
	}

	public ArrayList<BlogsDto> getBlogs() {
		return blogs;
	}

	public void setBlogs(ArrayList<BlogsDto> blogs) {
		this.blogs = blogs;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String buscarBlogs() {
		try {
			this.blogs = (ArrayList<BlogsDto>) blogsImplServices.getBlogsDto(objectQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editar(long id) {
		try {
			this.object = id > 0 ? this.blogs.stream().filter(blog -> blog.getId() == id).findFirst().orElse(null)
					: new BlogsDto();

			return "/pages/blogs/blogs_edition.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String agregar() {
		try {
			this.setObject(new BlogsDto());
			return "blogsEdit";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String anterior() {
		try {
			return "blogsQuery";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizar() {
		try {
			if (this.object.getId() > 0) {
				blogsImplServices.update(this.object);
			} else {
				// blogsImplServices.createBlogReader();
				blogsImplServices.create(this.object);
			}

			this.buscarBlogs();
			return anterior();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String borrar(Long id) {
		try {
			ReadersDto objQ = new ReadersDto();
			objQ.setbId(id);
			ArrayList<BlogsReadersDto> l = readersImplServices.getBlogsReadersDto(objQ);
			if (l != null && !l.isEmpty()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de información", "Este Blog tiene registros en Lectores Por Blog, No se puede eliminar"));
			}else {
				blogsImplServices.delete(id);
			}


			this.buscarBlogs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void limpiar() {
		this.objectQ = new BlogsDto();
		this.blogs = new ArrayList<>();
	}

}
