package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Report {

	@Id
	private String id;

	private String reportDetail;

	@DBRef
	private User user;

	public Report() {

	}

	public Report(String reportDetail, User user) {
		this.reportDetail = reportDetail;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public String getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(String reportDetail) {
		this.reportDetail = reportDetail;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return (id.equals(((Report) obj).id));
	}

	@Override
	public String toString() {
		return "Report[" + this.id + ", detail=" + this.reportDetail + ", userID=" + this.user.getUsername() + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
