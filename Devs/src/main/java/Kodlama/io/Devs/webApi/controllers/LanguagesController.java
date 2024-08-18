package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.entities.concretes.Language;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	@GetMapping("getall")
	List<Language> getAll(){
		return languageService.getAll();
	}
	
	@GetMapping("add")
	void add(Language language) {
		try {
			languageService.add(language);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
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
	void update(Language language) {
		try {
			languageService.update(language);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
	}
	
	@GetMapping("getbyid")
	Language getById(int id) {
		try {
			return languageService.getById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}
		return null;
	}
	
}
