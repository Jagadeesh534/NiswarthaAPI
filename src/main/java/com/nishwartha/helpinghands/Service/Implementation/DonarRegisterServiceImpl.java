package com.nishwartha.helpinghands.Service.Implementation;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.nishwartha.helpinghands.DAO.DonarRegisterDAO;
import com.nishwartha.helpinghands.Service.DonarRegisterService;
import com.nishwartha.helpinghands.common.JsonResponse;
import com.nishwartha.helpinghands.common.PageResponse;
import com.nishwartha.helpinghands.entity.DonarRegister;
import com.nishwartha.helpinghands.exception.ValidDataException;
import com.nishwartha.helpinghands.modal.Activities;
import com.nishwartha.helpinghands.modal.DonarRegisterDTO;

@Service()
@Transactional(readOnly = true)
public class DonarRegisterServiceImpl implements DonarRegisterService {

	@Value("${activities.path}")
	private String path;
	
	private int c = 0;

	@Autowired
	private DonarRegisterDAO registerDao;


	@Override
	public JsonResponse<Object> getBloodDonarsList(String bloodGroup) throws Exception {
		List<DonarRegister> entityList = registerDao.findAllByBloodgroup(bloodGroup);
		registerDao.print(bloodGroup);
		List<DonarRegisterDTO> result = convertToDonarRegister(entityList);
		Long count = (long) result.size();
		PageResponse<DonarRegisterDTO> pageResponse = new PageResponse<DonarRegisterDTO>(count, result);
		return new JsonResponse<Object>(true, "Load Success", pageResponse);
	}

	public List<DonarRegisterDTO> convertToDonarRegister(List<DonarRegister> list) {
		List<DonarRegisterDTO> dtoList = new ArrayList<DonarRegisterDTO>();
		if (list.size() > 0) {
			list.forEach((d) -> {
				dtoList.add(new DonarRegisterDTO(d));
			});
		}
		return dtoList;
	}

	@Override
	@Transactional(readOnly = false)
	public JsonResponse<Object> save(DonarRegisterDTO dto, BindingResult bindingResult)
			throws MethodArgumentNotValidException, ValidDataException, Exception {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date systemDate = new java.sql.Date(utilDate.getTime());
		DonarRegister register = new DonarRegister();
		if (dto.getId() == null) {
			register.setCreateddate(systemDate);
		}
		register.setName(dto.getName());
		register.setAddress(dto.getAddress());
		register.setAge(dto.getAge());
		register.setBloodgroup(dto.getBloodgroup());
		register.setEmail(dto.getEmail());
		register.setLastblooddonateddate(dto.getLastblooddonateddate());
		register.setMobilenumber(dto.getMobilenumber());
		register.setWeight(dto.getWeight());
		register.setGender(dto.getGender());
		registerDao.save(register);
		return new JsonResponse<Object>(true, "Successfully Saved", register.getName());
	}

	@Override
	public List<DonarRegisterDTO> getDonarListForPDF(String bloodGroup) throws Exception {
		List<DonarRegister> entityList = registerDao.findAllByBloodgroup(bloodGroup);
		registerDao.print(bloodGroup);
		List<DonarRegisterDTO> result = convertToDonarRegister(entityList);
		return result;
	}

	public String getActualImage(String fileName) {
		String base64Image = "";
		File fileFolder = new File(path, fileName);
		if (fileFolder != null) {
			String base64;
			try {
        		String ext = FilenameUtils.getExtension(fileFolder.getName());
        		FileInputStream fileInputStream = new FileInputStream(fileFolder);
        		byte[] bytes = new byte[(int)fileFolder.length()];
        		fileInputStream.read(bytes);
        		base64 = Base64.getEncoder().encodeToString(bytes);
        		base64Image = "data:image/"+ext+";base64,"+base64;
        		fileInputStream.close();
        		
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
		}
		return base64Image;
	}
}
