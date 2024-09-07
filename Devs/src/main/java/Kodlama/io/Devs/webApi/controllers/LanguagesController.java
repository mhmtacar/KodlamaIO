package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.language.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.language.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.language.GetAllLanguagesResponse;
import Kodlama.io.Devs.business.responses.language.GetLanguageResponse;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@GetMapping("getall")
	List<GetAllLanguagesResponse> getAll(){
		return languageService.getAll();
	}
	
	@GetMapping("add")
	GetLanguageResponse add(CreateLanguageRequest language) {
		try {
			return languageService.add(language);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("delete")
	void delete(int id) {
		try {
			languageService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
	}
	
	@GetMapping("update")
	GetLanguageResponse update(int id,UpdateLanguageRequest language) {
		try {
			return languageService.update(id,language);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	@GetMapping("getbyid")
	GetLanguageResponse getById(int id) {
		try {
			return languageService.getById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
		return null;
	}
	
}
