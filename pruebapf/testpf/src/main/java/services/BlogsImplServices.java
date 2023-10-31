package services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.BlogsDto;
import models.Blogs;
import models.Consecutivo;
import utils.NextConsecutive;

@Named("blogsImplServices")
@RequestScoped
public class BlogsImplServices implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6355995421520403565L;
	@PersistenceContext(unitName="bandelsal")
	 private final EntityManager em = JPA.getEntityManagerFactory().createEntityManager();
	 
	
	public BlogsImplServices() {
		
	}

	
	 public synchronized long getSequence(String tabla) {
		 //em.getTransaction().begin();   
		 Consecutivo c = em.find(Consecutivo.class, tabla);
		    long valor = c.getValor();
		    c.setValor(valor + 1);
		    em.merge(c);
		    //actualizo el valor
		    return valor;
		  }
	
	
   
    public List<BlogsDto> getBlogsDto(BlogsDto blog) {
        List<BlogsDto> result = new ArrayList<BlogsDto>();
        List<Blogs> list = new ArrayList<Blogs>();

        StringBuffer sql = new StringBuffer();
        Query query = null;
        Map<String, Object> map = new HashMap<String, Object>();

        sql.append("SELECT o FROM ");
        sql.append(Blogs.class.getCanonicalName());
        sql.append(" o WHERE 1 = 1 ");

        if (blog.getId() > 0) {
            sql.append(" AND o.id = :id ");
            map.put("id", blog.getId());
        }

        if (blog.getTitle() != null && !blog.getTitle().isEmpty()) {
            sql.append(" AND upper(o.title) like :title ");
            map.put("title", "%" + blog.getTitle().toUpperCase() + "%");
        }
        
        if (blog.getDescription() != null && !blog.getDescription().isEmpty()) {
            sql.append(" AND upper(o.description) like :description ");
            map.put("description", "%" + blog.getDescription().toUpperCase() + "%");
        }

        sql.append(" ORDER BY o.id ");
        query = em.createQuery(sql.toString());
        for (Map.Entry<String, Object> valor : map.entrySet()) {
            query.setParameter(valor.getKey(), valor.getValue());
        }

        list = query.getResultList();
        for (Blogs b : list) {
			BlogsDto bDto = new BlogsDto();
			bDto.setId(b.getId());
			bDto.setTitle(b.getTitle());
			bDto.setDescription(b.getDescription());
			result.add(bDto);
		}
        
        return result;
    }

    public void create(BlogsDto blogDto) {
        em.getTransaction().begin();
        Blogs blog = new Blogs();
        blog.setId(getSequence(NextConsecutive.BLOGS));
        blog.setTitle(blogDto.getTitle());
        blog.setDescription(blogDto.getDescription());
        em.persist(blog);
        em.getTransaction().commit();
    }

    public BlogsDto getBlogById(Long id) {
    	BlogsDto dto = new BlogsDto();
    	Blogs blog = em.find(Blogs.class, id);
    	if (blog != null) {
			dto.setId(blog.getId());
			dto.setTitle(blog.getTitle());
			dto.setDescription(blog.getDescription());
		}
    	
    	return dto;
    }

    public void update(BlogsDto dto) {
    	Blogs blog = em.find(Blogs.class, dto.getId());
    	blog.setId(dto.getId());
    	blog.setTitle(dto.getTitle());
    	blog.setDescription(dto.getDescription());
        em.getTransaction().begin();
        em.merge(blog);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Blogs blog = em.find(Blogs.class, id);
        if (blog != null) {
            em.remove(blog);
        }
        em.getTransaction().commit();
    }
//
//    public void createBlogReader(Blog blog, Reader reader) {
//        em.getTransaction().begin();
//        BlogsReaders br = new BlogsReaders();
//        br.setB_id(blog.getId());
//        br.setR_id(reader.getId());
//        em.persist(br);
//        em.getTransaction().commit();
//    }



}

