package spring.hibernate;

import java.util.HashSet;
import java.util.Set;

public class UserGroup {
	private String name;
	private int id;
	private Set<UserInfo> userSet = new HashSet<UserInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserInfo> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<UserInfo> userSet) {
		this.userSet = userSet;
	}


}
