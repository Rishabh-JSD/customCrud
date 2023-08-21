package custom.repo.FirstCustomRepo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class EntityClass {
    @Id
    @GeneratedValue
    private int id;
    private String age;
    private String companyName;
    private String email;
    private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EntityClass(int id, String age, String companyName, String email, String name) {
		super();
		this.id = id;
		this.age = age;
		this.companyName = companyName;
		this.email = email;
		this.name = name;
	}
	public EntityClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
   
   

    // Constructors, getters, setters, etc. can be added here
	
}
