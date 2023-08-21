package custom.repo.FirstCustomRepo;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoClass {

    EntityClass addEntityClassProfile(EntityClass entityClass);

    String deleteEntityClassProfile(int id);

    //Page<EntityClass> getAllPaginated(Pageable pageable);

    List<EntityClass> getEntities();

    EntityClass getEntityById(int id);

    EntityClass updateEntityClassProfile(EntityClass entityClass);

	Long getTotalStudentCount();

	List<EntityClass> getStudentList(int page, int size, String sortField, boolean isAscending);

	List<EntityClass> searchEntities(String searchTerm);
	
	//List<EntityClass> getSortedEntities(String sortField, String sortOrder);

}
