package com.mirror2.security.model;

import com.mirror2.security.DESEDE;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "token", uniqueConstraints= @UniqueConstraint(columnNames={"username"}))
public class Token implements Serializable {
	
	
	private static final long serialVersionUID = -2020752748932592756L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;

	private String username;
	private String password;

    //This three fields are temporally implemented
    private String name;
    private String department;
    private String designation;

    @OneToOne(mappedBy = "token")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
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
        DESEDE desede = new DESEDE(this.username);
        this.password = desede.encrypt(password);
	}
	private static String getDesPassword(String username, String password) {
        DESEDE desede = new DESEDE(username);
        return desede.decrypt(password);
	}

    public static void main(String[] args) {
        System.out.println(getDesPassword("yesali1988", "jZ3lFhHV+QiDXwk4karg+w=="));
    }

}
