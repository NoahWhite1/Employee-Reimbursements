package dev.noah.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int rId;
	@Column(name = "purpose")
	private String purpose;
	@Column(name = "amount")
	private double amount;
	@Column(name = "created_by")
	private int createdBy;
	@Column(name = "approved_by")
	private Integer approvedBy;
	@Column(name = "is_approved")
	private Boolean isApproved;
	@Column(name = "note")
	private String note;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reimbursement(int rId, String purpose, double amount, int createdBy, Integer approvedBy, Boolean isApproved,
			String note) {
		super();
		this.rId = rId;
		this.purpose = purpose;
		this.amount = amount;
		this.createdBy = createdBy;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.note = note;
	}
	
	

	public int getrId() {
		return rId;
	}


	public void setrId(int rId) {
		this.rId = rId;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public int getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}


	public boolean isApproved() {
		return isApproved;
	}


	public void setApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", purpose=" + purpose + ", amount=" + amount + ", createdBy=" + createdBy
				+ ", approvedBy=" + approvedBy + ", isApproved=" + isApproved + ", note=" + note + "]";
	}



	
}
