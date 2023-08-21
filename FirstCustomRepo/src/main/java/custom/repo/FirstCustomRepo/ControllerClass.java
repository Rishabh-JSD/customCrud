package custom.repo.FirstCustomRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entity-profile")
@CrossOrigin("*")
public class ControllerClass {

    private final ServiceClass ds;

    @Autowired
    public ControllerClass(ServiceClass ds) {
        this.ds = ds;
    }

    @PostMapping("/addentity")
    public EntityClass addEntity(@RequestBody EntityClass entity) {
        return ds.saveEntity(entity);
    }
    
    @GetMapping("/entities")
    public List<EntityClass> findAllEntities() {
        return ds.getEntities();
    }

//    @GetMapping("/getp")
//    public Page<EntityClass> findAllEntitiesPaginated(Pageable pageable) {
//        return ds.getPaginated(pageable);
//    }
//    
    @PutMapping("/update/{id}")
    public EntityClass updateEntity(@RequestBody EntityClass entity, @PathVariable Optional<Integer> id) {
        if (id.isPresent()) {
            return ds.updateEntity(entity, id.get());
        } else {
            // Handle the case where 'id' is not present
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEntity(@PathVariable int id) {
        return ds.deleteEntity(id);
    }
    @GetMapping("/page")

    public ResponseEntity<List<EntityClass>> getStudentList(

            @RequestParam int page,

            @RequestParam int size, 
            @RequestParam boolean isAscending,
    		@RequestParam String sortField){

        List<EntityClass> students = ds.getStudentList(page, size,sortField,isAscending);

        return ResponseEntity.ok(students);

    }

 

@GetMapping("/count")

public ResponseEntity<Long> getTotalStudentCount() {

    Long count = ds.getTotalStudentCount();

    return ResponseEntity.ok(count);

}
//@GetMapping("/entities/sort")
//public List<EntityClass> getSortedEntities(
//        @RequestParam(required = false) String sortField,
//        @RequestParam(defaultValue = "asc") String sortOrder) {
//    return ds.getSortedEntities(sortField, sortOrder);
//}

@GetMapping("/search")
public List<EntityClass> searchEntities(@RequestParam(required = false) String searchTerm) {
    if (searchTerm == null || searchTerm.isEmpty()) {
        // Handle case where searchTerm is empty or null
        return Collections.emptyList();
    }

    return ds.searchEntities(searchTerm);
}

}
