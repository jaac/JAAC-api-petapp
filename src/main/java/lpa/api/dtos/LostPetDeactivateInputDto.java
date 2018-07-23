package lpa.api.dtos;

import javax.validation.constraints.NotNull;

public class LostPetDeactivateInputDto {

	@NotNull
	private String userId;
	@NotNull
	private String lostpeId;

	public LostPetDeactivateInputDto() {
		// Empty for framework
	}

	public LostPetDeactivateInputDto(String userId, String lostpeId) {

		this.userId = userId;
		this.lostpeId = lostpeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLostpeId() {
		return lostpeId;
	}

	public void setLostpeId(String lostpeId) {
		this.lostpeId = lostpeId;
	}

	@Override
	public String toString() {
		return "LostPetDeactivateInputDto [userId=" + userId + ", lostpeId=" + lostpeId + "]";
	}
}
