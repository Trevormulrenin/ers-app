package com.revature.model;

public class Manager {
	private int managerId;
	private String mUsername;
	private String mPassword;
	private String mFirstName;
	private String mLastName;
	private String mEmail;
	
	public Manager() {
		super();
	}
	
	public Manager(int managerId, String mUsername, String mPassword, String mFirstName, String mLastName,
			String mEmail) {
		super();
		this.managerId = managerId;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mFirstName = mFirstName;
		this.mLastName = mLastName;
		this.mEmail = mEmail;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getmUsername() {
		return mUsername;
	}

	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmFirstName() {
		return mFirstName;
	}

	public void setmFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getmLastName() {
		return mLastName;
	}

	public void setmLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
		result = prime * result + ((mFirstName == null) ? 0 : mFirstName.hashCode());
		result = prime * result + ((mLastName == null) ? 0 : mLastName.hashCode());
		result = prime * result + ((mPassword == null) ? 0 : mPassword.hashCode());
		result = prime * result + ((mUsername == null) ? 0 : mUsername.hashCode());
		result = prime * result + managerId;
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
		Manager other = (Manager) obj;
		if (mEmail == null) {
			if (other.mEmail != null)
				return false;
		} else if (!mEmail.equals(other.mEmail))
			return false;
		if (mFirstName == null) {
			if (other.mFirstName != null)
				return false;
		} else if (!mFirstName.equals(other.mFirstName))
			return false;
		if (mLastName == null) {
			if (other.mLastName != null)
				return false;
		} else if (!mLastName.equals(other.mLastName))
			return false;
		if (mPassword == null) {
			if (other.mPassword != null)
				return false;
		} else if (!mPassword.equals(other.mPassword))
			return false;
		if (mUsername == null) {
			if (other.mUsername != null)
				return false;
		} else if (!mUsername.equals(other.mUsername))
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", mUsername=" + mUsername + ", mPassword=" + mPassword
				+ ", mFirstName=" + mFirstName + ", mLastName=" + mLastName + ", mEmail=" + mEmail + "]";
	}
	
}
