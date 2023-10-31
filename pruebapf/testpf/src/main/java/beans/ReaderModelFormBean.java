package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dto.BlogsReadersDto;
import dto.ReadersDto;
import services.ReadersImplServices;

@Named("readerFormBean")
@SessionScoped
public class ReaderModelFormBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4494575119877030289L;

	@Inject
	private ReadersImplServices readersImplServices;

	private ReadersDto objectQ;

	private ReadersDto object;

	private ArrayList<ReadersDto> readers;

	public ReaderModelFormBean() {

		
	}
	
	@PostConstruct
	public void init() {
		
		this.objectQ = new ReadersDto();
		this.object = new ReadersDto();
		this.readers = new ArrayList<ReadersDto>();
	}


	public ReadersImplServices getReadersImplServices() {
		return readersImplServices;
	}

	public void setReadersImplServices(ReadersImplServices readersImplServices) {
		this.readersImplServices = readersImplServices;
	}

	public ReadersDto getObjectQ() {
		return objectQ;
	}

	public void setObjectQ(ReadersDto objectQ) {
		this.objectQ = objectQ;
	}

	public ReadersDto getObject() {
		return object;
	}

	public void setObject(ReadersDto object) {
		this.object = object;
	}

	public ArrayList<ReadersDto> getReaders() {
		return readers;
	}

	public void setReaders(ArrayList<ReadersDto> readers) {
		this.readers = readers;
	}

	public String buscarReaders() {
		try {
			this.readers = (ArrayList<ReadersDto>) readersImplServices.getReadersDto(objectQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String editar(long id) {
		try {
			this.object = id > 0 ? this.readers.stream().filter(blog -> blog.getId() == id).findFirst().orElse(null)
					: new ReadersDto();

			return "readersEdit";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String agregar() {
		try {
			this.object = new ReadersDto();
			return "readersEdit";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String borrar(Long id) {
		try {
			ReadersDto objQ = new ReadersDto();
			objQ.setrId(id);
			ArrayList<BlogsReadersDto> l = readersImplServices.getBlogsReadersDto(objQ);
			if (l != null && !l.isEmpty()) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje de información", "Este Lector tiene registros en Lectores Por Blog, No se puede eliminar"));
			}else {
				readersImplServices.delete(id);
			}

			this.buscarReaders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void limpiar() {
		this.objectQ = new ReadersDto();
		this.readers = new ArrayList<>();
	}

	public String anterior() {
		try {
			return "readersQuery";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String actualizar() {
		try {
			if (this.object.getId() > 0) {
				readersImplServices.update(this.object);
			} else {
				// readersImplServices.createBlogReader();
				readersImplServices.create(this.object);
			}

			this.buscarReaders();
			return anterior();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
