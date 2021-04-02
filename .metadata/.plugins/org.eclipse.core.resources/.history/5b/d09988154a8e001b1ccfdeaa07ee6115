package com.revature.model;

public class Reimbursement {
	private int reimbursementId;
	private String reimbursementDescription;
	private boolean isPendingReimbursement;
	private boolean isApproved;
	private double reimbursementAmount;
	private int reimbursementEmployeeId;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, String reimbursementDescription, boolean isPendingReimbursement,
			boolean isApproved, double reimbursementAmount, int reimbursementEmployeeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementDescription = reimbursementDescription;
		this.isPendingReimbursement = isPendingReimbursement;
		this.isApproved = isApproved;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementEmployeeId = reimbursementEmployeeId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getReimbursementDescription() {
		return reimbursementDescription;
	}

	public void setReimbursementDescription(String reimbursementDescription) {
		this.reimbursementDescription = reimbursementDescription;
	}

	public boolean isPendingReimbursement() {
		return isPendingReimbursement;
	}

	public void setPendingReimbursement(boolean isPendingReimbursement) {
		this.isPendingReimbursement = isPendingReimbursement;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public int getReimbursementEmployeeId() {
		return reimbursementEmployeeId;
	}

	public void setReimbursementEmployeeId(int reimbursementEmployeeId) {
		this.reimbursementEmployeeId = reimbursementEmployeeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", reimbursementDescription="
				+ reimbursementDescription + ", isPendingReimbursement=" + isPendingReimbursement + ", isApproved="
				+ isApproved + ", reimbursementAmount=" + reimbursementAmount + ", reimbursementEmployeeId="
				+ reimbursementEmployeeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isApproved ? 1231 : 1237);
		result = prime * result + (isPendingReimbursement ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(reimbursementAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbursementDescription == null) ? 0 : reimbursementDescription.hashCode());
		result = prime * result + reimbursementEmployeeId;
		result = prime * result + reimbursementId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (isApproved != other.isApproved)
			return false;
		if (isPendingReimbursement != other.isPendingReimbursement)
			return false;
		if (Double.doubleToLongBits(reimbursementAmount) != Double.doubleToLongBits(other.reimbursementAmount))
			return false;
		if (reimbursementDescription == null) {
			if (other.reimbursementDescription != null)
				return false;
		} else if (!reimbursementDescription.equals(other.reimbursementDescription))
			return false;
		if (reimbursementEmployeeId != other.reimbursementEmployeeId)
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		return true;
	}
}