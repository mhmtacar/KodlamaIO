package Kodlama.io.Devs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {
	
	private LanguageRepository languageRepository;
	
	@Autowired
	public LanguageManager(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}

	@Override
	public List<Language> getAll() {
		return languageRepository.getAll();
	}

	@Override
	public void add(Language language) throws Exception {
		if(emptyNameControl(language.getName())){
			throw new Exception("Programlama dili boş geçilemez");
		}
		if(duplicateName(language.getName())) {
			throw new Exception("Programlama dili ismi tekrar edemez");
		}
		languageRepository.add(language);
	}

	@Override
	public void delete(int id) throws Exception {
		if(checkId(id)){
			languageRepository.delete(id);
		}
		throw new Exception("Yanlış id değeri");
	}

	@Override
	public void update(Language language) throws Exception {
		if(emptyNameControl(language.getName())){
			throw new Exception("Programlama dili boş geçilemez");
		}
		if(!checkId(language.getId())){
			throw new Exception("Yanlış id değeri");
		}
		
		languageRepository.update(language);
	}

	@Override
	public Language getById(int id) throws Exception {
		if(checkId(id)){
			return languageRepository.getById(id);
		}
		throw new Exception("Yanlış id değeri");
	}
	
	private boolean emptyNameControl(String name) {
		return name.isEmpty();
	}
	
	private boolean checkId(int id) {
		for (Language language : languageRepository.getAll()) {
			if(language.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	private boolean duplicateName(String name) {
		for (Language language : languageRepository.getAll()) {
			if(language.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

}
