package spring.hibernate;

public class UserInfo {
	private int id;
	private String name;
	private int age;
	private int sex;
public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
		private int version;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getId() {
		return id;
	}
	public UserInfo() {
		super();
	}
	public UserInfo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
