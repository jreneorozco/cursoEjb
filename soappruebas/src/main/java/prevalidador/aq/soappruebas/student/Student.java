package prevalidador.aq.soappruebas.student;

public class Student {

	private Long id;
	private String name;
	private String passportName;
	
	public Student() {
		super();
	}

	public Student(Long id, String name, String passportName) {
		super();
		this.id = id;
		this.name = name;
		this.passportName = passportName;
	}

	public Student(String name, String passportName) {
		super();
		this.name = name;
		this.passportName = passportName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", passportName=" + passportName + "]";
	}
	
	
}
