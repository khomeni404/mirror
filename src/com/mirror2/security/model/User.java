

package com.mirror2.security.model;

import com.mirror2.icm.model.InternalRequisition;
import com.mirror2.icm.model.ItemOut;
import com.mirror2.icm.model.ItemUsageHistory;
import com.mirror2.time.model.AttendanceRegister;
import com.mirror2.time.model.LeaveApplication;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("admin")
@Table(name = "user_auth")
public class User implements Serializable {
	
	private static final long serialVersionUID = 7389991231663740924L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorizeId;

	@OneToOne
	private Token token;

	private boolean active;
	
	@ManyToMany
	private List<AuthorizedGroups> authorizedGroups;


    @OneToMany
    @JoinTable(name = "ICM_JT_USER_ITEM_OUT",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_OUT_ID"))
    private List<ItemOut> itemOuts = new ArrayList<ItemOut>();

    @OneToMany
    @JoinTable(name = "ICM_JT_USER_ITEM_USAGE_HISTORY",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_HISTORY_ID"))
    private List<ItemUsageHistory> itemUsageHistories = new ArrayList<ItemUsageHistory>();

    @OneToMany
    @JoinTable(name = "ICM_JT_USER_INTERNAL_REQUISITION",
            joinColumns = @JoinColumn(name = "REQUEST_BY_ID"),
            inverseJoinColumns = @JoinColumn(name = "REQUISITION_ID"))
    private List<InternalRequisition> internalRequisitions = new ArrayList<InternalRequisition>();

    @OneToMany
    @JoinTable(name = "TIME_JT_USER_ATTENDANCE_REGISTER",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "RESISTER_ID"))
    private List<AttendanceRegister> registers = new ArrayList<AttendanceRegister>();

    @OneToMany
    @JoinTable(name = "TIME_JT_USER_LEAVE_APPLICATION",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_ID"))
    private List<LeaveApplication> applications = new ArrayList<LeaveApplication>();

    public List<AuthorizedGroups> getAuthorizedGroups() {
		return authorizedGroups;
	}
	public void setAuthorizedGroups(List<AuthorizedGroups> authorizedGroups) {
		this.authorizedGroups = authorizedGroups;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getAuthorizeId() {
		return authorizeId;
	}
	public void setAuthorizeId(Long authorizeId) {
		this.authorizeId = authorizeId;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public List<InternalRequisition> getInternalRequisitions() {
        return internalRequisitions;
    }

    public void setInternalRequisitions(List<InternalRequisition> internalRequisitions) {
        this.internalRequisitions = internalRequisitions;
    }

    public List<ItemOut> getItemOuts() {
        return itemOuts;
    }

    public void setItemOuts(List<ItemOut> itemOuts) {
        this.itemOuts = itemOuts;
    }

    public List<ItemUsageHistory> getItemUsageHistories() {
        return itemUsageHistories;
    }

    public void setItemUsageHistories(List<ItemUsageHistory> itemUsageHistories) {
        this.itemUsageHistories = itemUsageHistories;
    }

    public List<AttendanceRegister> getRegisters() {
        return registers;
    }

    public void setRegisters(List<AttendanceRegister> registers) {
        this.registers = registers;
    }

    public List<LeaveApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<LeaveApplication> applications) {
        this.applications = applications;
    }
}
