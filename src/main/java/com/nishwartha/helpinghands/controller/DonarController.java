package com.nishwartha.helpinghands.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nishwartha.helpinghands.Service.DonarRegisterService;
import com.nishwartha.helpinghands.common.JsonResponse;
import com.nishwartha.helpinghands.exception.ValidDataException;
import com.nishwartha.helpinghands.modal.DonarRegisterDTO;
import com.nishwartha.helpinghands.pdf.PdfGenerator;

@RestController
@RequestMapping("/bllodDonars")
@CrossOrigin
public class DonarController {

	@Value("${activities.path}")
	private String path;
	
	@Autowired
	private DonarRegisterService donarRegisterService;
	
	@RequestMapping(value = "/donarList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json,application/xml")
	public ResponseEntity<JsonResponse<Object>> getAllDonars(@RequestParam("bloodGroup") String bloodGroup) throws Exception {
		JsonResponse<Object> result = donarRegisterService.getBloodDonarsList(bloodGroup);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<JsonResponse<Object>> saveDonar(@RequestBody DonarRegisterDTO donar,BindingResult bindingResult) throws MethodArgumentNotValidException, ValidDataException, Exception {
		
		if(donar.getImage()!=null) {
			if(donar.getImage()!=null) {
				try {
					byte[] bytes = convertBase64StringToByteArray(donar.getImage());
					String fileName = "Jagan.png";
					Path path = Paths.get(this.path + fileName);
					Files.write(path, bytes);

				} catch (IOException e) {
					e.printStackTrace();
				}


			}

		}
		
		JsonResponse<Object> result = donarRegisterService.save(donar, bindingResult);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getPdf", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> getPDFPrint(@RequestParam("bloodGroup") String bloodGroup) throws MethodArgumentNotValidException, ValidDataException, Exception {
		List<DonarRegisterDTO> result = donarRegisterService.getDonarListForPDF(bloodGroup);
		ByteArrayInputStream bis = PdfGenerator.bloodDonarsPDFReport((List<DonarRegisterDTO>) result);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=DonarsList.pdf");
		return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test() {
	return "working";
	}
	public byte[] convertBase64StringToByteArray(String data) throws UnsupportedEncodingException {
		data = data.replaceFirst("^data:image/[^;]*;base64,?","");
		byte[] name = Base64.getMimeDecoder().decode(data);
		return name;
	}
	
}
