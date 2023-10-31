package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dto.BlogsDto;

@Named("inicioSesionFormBean")
@RequestScoped
public class InicioSesionModelFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5483251221727588708L;

	private String usuario;
	private String pwd;

	private ArrayList<BlogsDto> blogs;

	public InicioSesionModelFormBean() {
		usuario = "root";
		pwd = "root";

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public ArrayList<BlogsDto> getBlogs() {
		return blogs;
	}

	public void setBlogs(ArrayList<BlogsDto> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "InicioSesionModelFormBean [usuario=" + usuario + ", pwd=" + pwd + ", blogs=" + blogs + "]";
	}

	public String login() {
		if (this.getUsuario().equals("root") && this.getPwd().equals("root")) {
			return "/dashboard.xhtml";
		}

		return null;
	}

}
