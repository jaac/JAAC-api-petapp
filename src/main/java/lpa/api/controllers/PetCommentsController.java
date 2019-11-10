package lpa.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Comments;

import lpa.api.dtos.PetCommentsInputDto;
import lpa.api.dtos.PetCommentsOutputDto;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.PetCommentsRepository;
import lpa.api.repositories.core.UserRepository;

@Controller
public class PetCommentsController {

	@Autowired
	private PetCommentsRepository petCommentsRepository;
	@Autowired
	private LostPetRepository lostPetRepository;
	@Autowired
	private UserRepository userRepository;

	public List<PetCommentsOutputDto> readPetCommentsAll() {
		List<PetCommentsOutputDto> petCommentsOutputDtoList = new ArrayList<>();
		List<Comments> commentsList = this.petCommentsRepository.findPetCommentsAll();
		for (Comments comments : commentsList) {
			petCommentsOutputDtoList.add(new PetCommentsOutputDto(comments));
		}
		return petCommentsOutputDtoList;
	}

	public Page<PetCommentsOutputDto> readLostPetCommentsAll(String plId, int page, int size) {
		LostPet lostPet = this.lostPetRepository.findOne(plId);
		List<Comments> commentslist = this.petCommentsRepository
				.findByLostPet(lostPet, new PageRequest(page, size, Sort.Direction.DESC, "date")).getContent();
		List<PetCommentsOutputDto> petCommentsOutputDtoList = new ArrayList<>();
		for (Comments comments : commentslist) {
			PetCommentsOutputDto petCommentsOutputDto = new PetCommentsOutputDto(comments);
			petCommentsOutputDtoList.add(petCommentsOutputDto);
		}
		Page<PetCommentsOutputDto> petCommentsPage = new PageImpl<PetCommentsOutputDto>(petCommentsOutputDtoList,
				new PageRequest(page, size), petCommentsOutputDtoList.size());
		return petCommentsPage;
	}

	public boolean createPetComment(PetCommentsInputDto commentDto) {
		Comments comment = new Comments();
		comment.setComment(commentDto.getComment());
		comment.setDate(commentDto.getDate());
		comment.setiSaw(commentDto.isiSaw());
		comment.setLocation(commentDto.getLocation());
		
		if (this.userRepository.findOne(commentDto.getUserId()) != null) {
			comment.setUser(this.userRepository.findOne(commentDto.getUserId()));
		}else {
			return false;
		}

		comment.setLostPet(this.lostPetRepository.findOne(commentDto.getLostPetId()));
		comment.setPetImage(commentDto.getPetImage());
		if (this.petCommentsRepository.save(comment) != null) {
			return true;
		} else {
			return false;
		}

	}

}
