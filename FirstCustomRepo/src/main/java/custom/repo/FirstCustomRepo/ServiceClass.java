package custom.repo.FirstCustomRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceClass {

    private final DaoClass custRepo;

    @Autowired
    public ServiceClass(DaoClass custRepo) {
        this.custRepo = custRepo;
    }

    public EntityClass saveEntity(EntityClass entity) {
        return custRepo.addEntityClassProfile(entity);
    }

    public List<EntityClass> getEntities() {
        return custRepo.getEntities();
    }

    public EntityClass updateEntity(EntityClass updatedEntity, Integer id) {
        EntityClass existingEntity = custRepo.getEntityById(id);

        if (existingEntity != null) {
            existingEntity.setName(updatedEntity.getName());
            existingEntity.setEmail(updatedEntity.getEmail());
            existingEntity.setAge(updatedEntity.getAge());
            existingEntity.setCompanyName(updatedEntity.getCompanyName());

            return custRepo.updateEntityClassProfile(existingEntity);
        } else {
            // Handle the case where the entity with the given ID doesn't exist
            return null;
        }
    }

    public String deleteEntity(int id) {
        return custRepo.deleteEntityClassProfile(id);
    }
//
//    public Page<EntityClass> getPaginated(Pageable pageable) {
//        return custRepo.getAllPaginated(pageable);
//    }
    public List<EntityClass> getStudentList(int page, int size, String sortField,boolean isAscending) {

        return custRepo.getStudentList(page, size, sortField,isAscending);

    }

 

    public Long getTotalStudentCount() {

        return custRepo.getTotalStudentCount();

    }
//    public List<EntityClass> getSortedEntities(String sortField, String sortOrder) {
//        return custRepo.getSortedEntities(sortField, sortOrder);
//    }
    
    public List<EntityClass> searchEntities(String searchTerm) {
        return custRepo.searchEntities(searchTerm);
    }

}
