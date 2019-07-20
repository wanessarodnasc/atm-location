package br.atm.location.exception;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.atm.location.controller.dto.ExceptionFormDto;

/**
* This class treated the code error to response an understandable message to this error. 
*
* @author Wanessa Nascimento
*/
@RestControllerAdvice
public class ErrorValidationHandler {
	
	@Autowired
	private MessageSource messageSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorValidationHandler.class);
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UsernameNotFoundException.class)
	public String handlerUsernameNotFoundException(UsernameNotFoundException exception) {
		LOGGER.info("Username Not Found.");
		return exception.getMessage();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(BadCredentialsException.class)
	public String handlerBadCredentialsException(BadCredentialsException exception) {
		LOGGER.info("Bad credentials.");
		return exception.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handlerMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
		LOGGER.info(exception.getMessage());
		return exception.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(BusinessException.class)
	public String handlerBusinessEexception(BusinessException exception) {
		LOGGER.info(MessageFormat.format("Business error. {0}", exception.getMessage()));
		return exception.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ExceptionFormDto> handle(MethodArgumentNotValidException exception) {
		List<ExceptionFormDto> dto = getErrorDetail(exception);
		LOGGER.info(MessageFormat.format("Method argument error. {0}", exception.getMessage()));
		return dto;
	}

	private List<ExceptionFormDto> getErrorDetail(MethodArgumentNotValidException exception) {
		List<ExceptionFormDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		for(FieldError fildError : fieldErrors) {
			String mensagem = messageSource.getMessage(fildError, LocaleContextHolder.getLocale());
			ExceptionFormDto erro = new ExceptionFormDto(fildError.getField(), mensagem);
			dto.add(erro);
		}
		return dto;
	}
}
