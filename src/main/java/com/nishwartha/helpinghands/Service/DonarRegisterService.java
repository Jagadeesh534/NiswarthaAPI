package com.nishwartha.helpinghands.Service;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.nishwartha.helpinghands.common.JsonResponse;
import com.nishwartha.helpinghands.exception.ValidDataException;
import com.nishwartha.helpinghands.modal.DonarRegisterDTO;

public interface DonarRegisterService {

	public JsonResponse<Object> getBloodDonarsList(String bloodGroup) throws Exception;
	
	public JsonResponse<Object> save(DonarRegisterDTO dto, BindingResult bindingResult) throws MethodArgumentNotValidException, ValidDataException, Exception;

	public List<DonarRegisterDTO> getDonarListForPDF(String bloodGroup) throws Exception;
	
}
