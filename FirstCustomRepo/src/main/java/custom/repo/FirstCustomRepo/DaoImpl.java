package custom.repo.FirstCustomRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoImpl implements DaoClass {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger logger = LoggerFactory.getLogger(DaoImpl.class);

	@Override
	public EntityClass addEntityClassProfile(EntityClass entityClass) {
		entityManager.persist(entityClass);
		logger.info("EntityClass has been added successfully, EntityClass details=" + entityClass);
		return entityClass;
	}

	@Override
	public EntityClass updateEntityClassProfile(EntityClass entityClass) {
		entityManager.merge(entityClass);
		logger.info("EntityClass has been updated successfully, EntityClass details=" + entityClass);
		return entityClass;
	}

	@Override
	public String deleteEntityClassProfile(int id) {
		EntityClass entity = entityManager.find(EntityClass.class, id);
		if (entity != null) {
			entityManager.remove(entity);
			logger.info("EntityClass has been deleted successfully, EntityClass details=" + entity);
		}
		return "Entity with ID " + id + " deleted successfully";
	}

	@Override
	public List<EntityClass> getEntities() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntityClass> cq = cb.createQuery(EntityClass.class);
		Root<EntityClass> root = cq.from(EntityClass.class);
		CriteriaQuery<EntityClass> all = cq.select(root);
		TypedQuery<EntityClass> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public EntityClass getEntityById(int id) {
		return entityManager.find(EntityClass.class, id);
	}
	

//    @Override
//    public List<EntityClass> getStudentList(int page, int size, PaginationCriteria paginationCriteria) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<EntityClass> query = criteriaBuilder.createQuery(EntityClass.class);
//        Root<EntityClass> root = query.from(EntityClass.class);
//        query.select(root);
//
//        // Sorting logic
//        if (paginationCriteria.getSortField() != null && !paginationCriteria.getSortField().isEmpty()) {
//            Order order;
//            if (paginationCriteria.getSortType() == 2) {
//                order = criteriaBuilder.desc(root.get(paginationCriteria.getSortField()));
//            } else {
//                order = criteriaBuilder.asc(root.get(paginationCriteria.getSortField()));
//            }
//            query.orderBy(order);
//        } else {
//            query.orderBy(criteriaBuilder.desc(root.get("id")));
//        }
//
//        TypedQuery<EntityClass> typedQuery = entityManager.createQuery(query)
//                .setFirstResult(page * size)
//                .setMaxResults(size);
//
//        return typedQuery.getResultList();
//    }
//	
	@Override
	public List<EntityClass> getStudentList(int page, int size, String SortField, boolean isAscending) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<EntityClass> query = criteriaBuilder.createQuery(EntityClass.class);

		Root<EntityClass> root = query.from(EntityClass.class);

		query.select(root);
		Path<Object> path = root.get(SortField);
		if(isAscending) {
			query.orderBy(criteriaBuilder.asc(path));
			
		}else {
			query.orderBy(criteriaBuilder.desc(path));
		}
		
		

		TypedQuery<EntityClass> typedQuery = entityManager.createQuery(query)

				.setFirstResult(page * size)

				.setMaxResults(size);

		return typedQuery.getResultList();

	}

	@Override
	public Long getTotalStudentCount() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

		Root<EntityClass> root = query.from(EntityClass.class);

		query.select(criteriaBuilder.count(root));

		return entityManager.createQuery(query).getSingleResult();

	}
//	@Override
//	public List<EntityClass> getSortedEntities(String sortField, String sortOrder) {
//	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//	    CriteriaQuery<EntityClass> cq = cb.createQuery(EntityClass.class);
//	    Root<EntityClass> root = cq.from(EntityClass.class);
//	    cq.select(root);
//
//	    if (sortField != null && !sortField.isEmpty()) {
//	        if ("asc".equalsIgnoreCase(sortOrder)) {
//	            cq.orderBy(cb.asc(root.get(sortField)));
//	        } else if ("desc".equalsIgnoreCase(sortOrder)) {
//	            cq.orderBy(cb.desc(root.get(sortField)));
//	        }
//	    }
//
//	    TypedQuery<EntityClass> query = entityManager.createQuery(cq);
//	    return query.getResultList();
//	}

	
	
	//Search Functionality
	@Override
    public List<EntityClass> searchEntities(String searchTerm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntityClass> criteriaQuery = criteriaBuilder.createQuery(EntityClass.class);
        Root<EntityClass> root = criteriaQuery.from(EntityClass.class);

        List<Predicate> predicates = new ArrayList<>();
        if (searchTerm != null && !searchTerm.isEmpty()) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchTerm.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + searchTerm.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("companyName")), "%" + searchTerm.toLowerCase() + "%")
                    
                    // Add more fields to search here
            ));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
