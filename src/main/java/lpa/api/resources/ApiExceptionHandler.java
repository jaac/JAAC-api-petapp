package lpa.api.resources;

import javax.servlet.http.HttpServletRequest;

import lpa.api.resources.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ UserIdNotFoundException.class, FileException.class, LostPetIdNotFoundException.class, PetTypeNotFoundException.class })
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ Exception.class, UserFieldAlreadyExistException.class, LostPetDistanceNotAllowedException.class,
			FieldInvalidException.class, LostPetBadRequest.class, PetTypeAlreadyExistException.class,
			PetCommentsException.class,LostPetException.class })
	@ResponseBody
	public ErrorMessage badRequest(Exception exception) {
		return new ErrorMessage(exception, "");
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ForbiddenException.class, AccessDeniedException.class })
	@ResponseBody
	public ErrorMessage forbiddenRequest(Exception exception) {
		return new ErrorMessage(exception, "");
	}
}
