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
import dto.BlogsReadersDto;
import dto.ReadersDto;
import models.BlogsReaders;
import models.BlogsReadersPK;
import models.Consecutivo;
import models.Readers;
import utils.NextConsecutive;

@Named("readersImplServices")
@RequestScoped
public class ReadersImplServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2427636158837008106L;

	public ReadersImplServices() {

	}

	@PersistenceContext(unitName = "bandelsal")
	private final EntityManager em = JPA.getEntityManagerFactory().createEntityManager();

	public synchronized long getSequence(String tabla) {
//		em.getTransaction().begin();
		Consecutivo c = em.find(Consecutivo.class, tabla);
		long valor = c.getValor();
		c.setValor(valor + 1);
		em.merge(c);
		// actualizo el valor
//		em.getTransaction().commit();
		return valor;
	}

	public List<ReadersDto> getReadersDto(ReadersDto reader) {
		List<ReadersDto> result = new ArrayList<ReadersDto>();
		List<Readers> list = new ArrayList<Readers>();

		StringBuffer sql = new StringBuffer();
		Query query = null;
		Map<String, Object> map = new HashMap<String, Object>();

		sql.append("SELECT o FROM ");
		sql.append(Readers.class.getCanonicalName());
		sql.append(" o WHERE 1 = 1 ");

		if (reader.getId() > 0) {
			sql.append(" AND o.id = :id ");
			map.put("id", reader.getId());
		}

		if (reader.getNames() != null && !reader.getNames().isEmpty()) {
			sql.append(" AND upper(o.names) = :o.names ");
			map.put("names", "%" + reader.getNames().toUpperCase() + "%");
		}

		sql.append(" ORDER BY o.id ");
		query = em.createQuery(sql.toString());
		for (Map.Entry<String, Object> valor : map.entrySet()) {
			query.setParameter(valor.getKey(), valor.getValue());
		}

		list = query.getResultList();
		for (Readers r : list) {
			ReadersDto rDto = new ReadersDto();
			rDto.setId(r.getId());
			rDto.setNames(r.getNames());
			result.add(rDto);
		}

		return result;
	}

	public void create(ReadersDto readersDto) {
		em.getTransaction().begin();
		Readers reader = new Readers();
		reader.setId(getSequence(NextConsecutive.READERS));
		reader.setNames(readersDto.getNames());
		em.persist(reader);
		em.getTransaction().commit();
	}

	public ReadersDto getBlogById(Long id) {
		ReadersDto dto = new ReadersDto();
		Readers reader = em.find(Readers.class, id);
		if (reader != null) {
			dto.setId(reader.getId());
			dto.setNames(reader.getNames());
		}

		return dto;
	}

	public void update(ReadersDto dto) {
		Readers reader = em.find(Readers.class, dto.getId());
		reader.setId(dto.getId());
		reader.setNames(dto.getNames());
		em.getTransaction().begin();
		em.merge(reader);
		em.getTransaction().commit();
	}

	public void delete(Long id) {
		em.getTransaction().begin();
		Readers reader = em.find(Readers.class, id);
		if (reader != null) {
			em.remove(reader);
		}
		em.getTransaction().commit();
	}

	public void createBlogsReaders(BlogsReadersDto blogsReadersDto) {
		em.getTransaction().begin();
		
		BlogsReaders br = new BlogsReaders();
		br.setId(new BlogsReadersPK());
		br.getId().setbId(blogsReadersDto.getbId());
		br.getId().setrId(blogsReadersDto.getrId());
		em.persist(br);
		em.getTransaction().commit();
	}

	public ArrayList<BlogsReadersDto> getBlogsReadersDto(ReadersDto objectReadersQ) {
		ArrayList<BlogsReadersDto> result = new ArrayList<BlogsReadersDto>();
		List<BlogsReaders> list = new ArrayList<BlogsReaders>();

		StringBuffer sql = new StringBuffer();
		Query query = null;
		Map<String, Object> map = new HashMap<String, Object>();

		sql.append("SELECT o FROM ");
		sql.append(BlogsReaders.class.getCanonicalName());
		sql.append(" o WHERE 1 = 1 ");

		if (objectReadersQ.getrId() > 0) {
			sql.append(" AND o.id.rId = :rId ");
			map.put("rId", objectReadersQ.getrId());
		}
		
		if (objectReadersQ.getbId() > 0) {
			sql.append(" AND o.id.bId = :bId ");
			map.put("bId", objectReadersQ.getbId());
		}

		sql.append(" ORDER BY o.id.rId ");
		query = em.createQuery(sql.toString());
		for (Map.Entry<String, Object> valor : map.entrySet()) {
			query.setParameter(valor.getKey(), valor.getValue());
		}

		list = query.getResultList();
		for (BlogsReaders br : list) {
			BlogsReadersDto rDto = new BlogsReadersDto();
			rDto.setBlogsDto(new BlogsDto());
			rDto.getBlogsDto().setId(br.getId().getbId());
			rDto.getBlogsDto().setTitle(br.getBlogs().getTitle());
			rDto.getBlogsDto().setDescription(br.getBlogs().getDescription());
			rDto.setReadersDto(new ReadersDto());
			rDto.getReadersDto().setId(br.getId().getrId());
			rDto.getReadersDto().setNames(br.getReaders().getNames());
			result.add(rDto);
		}

		return result;
	}
	
	public void deleteReaderBlogs(BlogsReadersDto objectReadersQ) {
		ArrayList<BlogsReadersDto> result = new ArrayList<BlogsReadersDto>();
		List<BlogsReaders> list = new ArrayList<BlogsReaders>();

		StringBuffer sql = new StringBuffer();
		Query query = null;
		Map<String, Object> map = new HashMap<String, Object>();

		sql.append("SELECT o FROM ");
		sql.append(BlogsReaders.class.getCanonicalName());
		sql.append(" o WHERE 1 = 1 ");

		if (objectReadersQ.getrId() > 0) {
			sql.append(" AND o.id.rId = :rId ");
			map.put("rId", objectReadersQ.getrId());
		}
		
		if (objectReadersQ.getbId() > 0) {
			sql.append(" AND o.id.bId = :bId ");
			map.put("bId", objectReadersQ.getbId());
		}

		sql.append(" ORDER BY o.id.rId ");
		query = em.createQuery(sql.toString());
		for (Map.Entry<String, Object> valor : map.entrySet()) {
			query.setParameter(valor.getKey(), valor.getValue());
		}

		list = query.getResultList();
		for (BlogsReaders br : list) {
			em.getTransaction().begin();
			if (br != null) {
				em.remove(br);
			}
			
		}
		
		em.getTransaction().commit();
		
	}

}
