package dev.noah.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private int mId;
	@Column(name = "name")
	private String name;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(int mId, String username, String password) {
		super();
		this.mId = mId;
		this.username = username;
		this.password = password;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Manager [mId=" + mId + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}
}
