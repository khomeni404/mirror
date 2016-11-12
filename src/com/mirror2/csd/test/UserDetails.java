package com.mirror2.csd.test;



import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;



@Entity
@Table (name="USER_DETAILS")
public class UserDetails {
	@Id
	@GeneratedValue
	@Column (name="USER_ID")
	private int	id;
	private String username;

	@OneToMany
	@JoinTable(name="USER_CARS", joinColumns=@JoinColumn(name="USER_ID"),
				inverseJoinColumns=@JoinColumn(name="CAR_ID")
			)
	private Collection<Car> addressList = new ArrayList<Car>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<Car> getAddressList() {
		return addressList;
	}

	public void setAddressList(Collection<Car> addressList) {
		this.addressList = addressList;
	}

	

	
	
}
