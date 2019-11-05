package lpa.api.dtos;

import lpa.api.documents.core.LostPet;

public class LostPetUpdateInputDto extends LostPetInputDto {

	private String userId;
	private String lostPetId;
	private LostPet lostPet;

	public LostPetUpdateInputDto() {
		super();
	}

	public LostPetUpdateInputDto(LostPet lostPet, String userId, String lostPetId) {
		super(lostPet);
		this.lostPetId = lostPetId;
		this.userId = userId;
		this.setLostPet(lostPet);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLostPetId() {
		return lostPetId;
	}

	public void setLostPetId(String lostPetId) {
		this.lostPetId = lostPetId;
	}

	public LostPet getLostPet() {
		return lostPet;
	}

	public void setLostPet(LostPet lostPet) {
		this.lostPet = lostPet;
	}

	@Override
	public String toString() {
		return "LostPetUpdateInputDto [userId=" + userId + ", lostPetId=" + lostPetId + ", lostPet=" + lostPet + "]";
	}

}
