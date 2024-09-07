package Kodlama.io.Devs.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.requests.technology.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technology.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologiesResponse;
import Kodlama.io.Devs.business.responses.technology.GetTechnologyResponse;
import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import Kodlama.io.Devs.entities.concretes.Language;
import Kodlama.io.Devs.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {

    private TechnologyRepository technologyRepository;
    private LanguageRepository languageRepository;

    @Autowired
    public TechnologyManager(TechnologyRepository technologyRepository, LanguageRepository languageRepository) {
        this.technologyRepository = technologyRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public List<GetAllTechnologiesResponse> getAll() {
        List<Technology> technologies = technologyRepository.findAll();
        List<GetAllTechnologiesResponse> dtos = new ArrayList<>();
        for (Technology technology : technologies) {
            GetAllTechnologiesResponse dto = new GetAllTechnologiesResponse();
            dto.setId(technology.getId());
            dto.setName(technology.getName());
            dto.setLanguageId(technology.getLanguage().getId());
            dto.setLanguageName(technology.getLanguage().getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public GetTechnologyResponse getById(int id) throws Exception{
        Optional<Technology> technology = technologyRepository.findById(id);
        if (technology.isPresent()) {
            Technology tech = technology.get();
            GetTechnologyResponse dto = new GetTechnologyResponse();
            dto.setId(tech.getId());
            dto.setName(tech.getName());
            dto.setLanguageId(tech.getLanguage().getId());
            dto.setLanguageName(tech.getLanguage().getName());
            return dto;
        }
        throw new Exception("Yanlış technology id değeri");
    }

    @Override
    public GetTechnologyResponse add(CreateTechnologyRequest technologyDto) throws Exception {
        Optional<Language> language = languageRepository.findById(technologyDto.getLanguageId());
        if (language.isPresent()) {
            Technology technology = new Technology();
            if(emptyNameControl(technologyDto.getName())){
    			throw new Exception("Programlama dili boş geçilemez");
    		}
    		if(duplicateName(technologyDto.getName())) {
    			throw new Exception("Programlama dili ismi tekrar edemez");
    		}
            technology.setName(technologyDto.getName());
            technology.setLanguage(language.get());
            Technology savedTechnology = technologyRepository.save(technology);

            GetTechnologyResponse dto = new GetTechnologyResponse();
            dto.setId(savedTechnology.getId());
            dto.setName(savedTechnology.getName());
            dto.setLanguageId(savedTechnology.getLanguage().getId());
            dto.setLanguageName(savedTechnology.getLanguage().getName());
            return dto;
        }
        return null;
    }

    @Override
    public GetTechnologyResponse update(int id, UpdateTechnologyRequest technologyDto) throws Exception {
        Optional<Technology> technology = technologyRepository.findById(id);
        if (technology.isPresent()) {
            Optional<Language> language = languageRepository.findById(technologyDto.getLanguageId());
            if (language.isPresent()) {
                Technology techToUpdate = technology.get();
                if(emptyNameControl(technologyDto.getName())){
        			throw new Exception("Programlama dili boş geçilemez");
        		}
        		if(duplicateName(technologyDto.getName())) {
        			throw new Exception("Programlama dili ismi tekrar edemez");
        		}
                techToUpdate.setName(technologyDto.getName());
                techToUpdate.setLanguage(language.get());
                Technology updatedTechnology = technologyRepository.save(techToUpdate);

                GetTechnologyResponse dto = new GetTechnologyResponse();
                dto.setId(updatedTechnology.getId());
                dto.setName(updatedTechnology.getName());
                dto.setLanguageId(updatedTechnology.getLanguage().getId());
                dto.setLanguageName(updatedTechnology.getLanguage().getName());
                return dto;
            }
        }
        throw new Exception("Yanlış technology id değeri");
    }

    @Override
    public void delete(int id) throws Exception {
    	Optional<Technology> technology = technologyRepository.findById(id);
		if(technology.isPresent()){
			technologyRepository.deleteById(id);
		}
		throw new Exception("Yanlış technology id değeri");
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
