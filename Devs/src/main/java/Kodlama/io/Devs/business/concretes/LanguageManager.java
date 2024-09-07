package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.LanguageService;
import Kodlama.io.Devs.business.requests.language.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.language.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.language.GetAllLanguagesResponse;
import Kodlama.io.Devs.business.responses.language.GetLanguageResponse;
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
	public List<GetAllLanguagesResponse> getAll() {
		
		List<Language> languages = languageRepository.findAll();
        List<GetAllLanguagesResponse> dtos = new ArrayList<>();
        for (Language language : languages) {
            GetAllLanguagesResponse dto = new GetAllLanguagesResponse();
            dto.setId(language.getId());
            dto.setName(language.getName());
        }
        return dtos;
	}

	@Override
	public GetLanguageResponse add(CreateLanguageRequest createLanguageRequest) throws Exception {
		if(emptyNameControl(createLanguageRequest.getName())){
			throw new Exception("Programlama dili boş geçilemez");
		}
		if(duplicateName(createLanguageRequest.getName())) {
			throw new Exception("Programlama dili ismi tekrar edemez");
		}
		Language language = new Language();
		language.setName(createLanguageRequest.getName());
		Language savedLanguage = languageRepository.save(language);

        GetLanguageResponse dto = new GetLanguageResponse();
        dto.setId(savedLanguage.getId());
        dto.setName(savedLanguage.getName());
        return dto;
	}

	@Override
	public void delete(int id) throws Exception {
		Optional<Language> language = languageRepository.findById(id);
		if(language.isPresent()){
			languageRepository.deleteById(id);
		}
		throw new Exception("Yanlış language id değeri");
	}

	@Override
	public GetLanguageResponse update(int id, UpdateLanguageRequest updateLanguageRequest) throws Exception {
		Optional<Language> language = languageRepository.findById(id);
            if (language.isPresent()) {
                Language languageToUpdate = language.get();
                if(emptyNameControl(updateLanguageRequest.getName())){
        			throw new Exception("Programlama dili boş geçilemez");
        		}
        		if(duplicateName(updateLanguageRequest.getName())) {
        			throw new Exception("Programlama dili ismi tekrar edemez");
        		}
                languageToUpdate.setName(updateLanguageRequest.getName());
                Language updatedLanguage = languageRepository.save(languageToUpdate);

                GetLanguageResponse dto = new GetLanguageResponse();
                dto.setId(updatedLanguage.getId());
                dto.setName(updatedLanguage.getName());
                return dto;
            }
        
            throw new Exception("Yanlış language id değeri");
	}

	@Override
	public GetLanguageResponse getById(int id) throws Exception {
		Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            Language lang = language.get();
            GetLanguageResponse dto = new GetLanguageResponse();
            dto.setId(lang.getId());
            dto.setName(lang.getName());
            return dto;
        }
        throw new Exception("Yanlış language id değeri");
	}
	
	private boolean emptyNameControl(String name) {
		return name.isEmpty();
	}
	
	private boolean duplicateName(String name) {
		for (Language language : languageRepository.findAll()) {
			if(language.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

}
