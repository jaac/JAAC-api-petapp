package lpa.api.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lpa.api.resources.exceptions.ErrorMessage;
import lpa.api.resources.exceptions.FileException;
import lpa.api.resources.exceptions.ForbiddenException;
import lpa.api.resources.exceptions.LostPetBadRequest;
import lpa.api.resources.exceptions.LostPetIdNotFoundException;
import lpa.api.resources.exceptions.LotPetDistanceNotAllowedExecption;
import lpa.api.resources.exceptions.PetTypeAlreadyExistException;
import lpa.api.resources.exceptions.UserIdNotFoundException;
import lpa.api.resources.exceptions.UserFieldAlreadyExistException;
import lpa.api.resources.exceptions.FieldInvalidException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ UserIdNotFoundException.class, FileException.class, LostPetIdNotFoundException.class })
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ Exception.class, UserFieldAlreadyExistException.class, LotPetDistanceNotAllowedExecption.class,
			FieldInvalidException.class, LostPetBadRequest.class, PetTypeAlreadyExistException.class })
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
