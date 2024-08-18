package Kodlama.io.Devs.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import Kodlama.io.Devs.entities.concretes.Language;

@Repository
public class InMemoryLanguageRepository implements LanguageRepository {
	
	List<Language> languages;
	
	public InMemoryLanguageRepository() {
		languages = new ArrayList<Language>();
		languages.add(new Language(1,"C#"));
		languages.add(new Language(2,"Java"));
		languages.add(new Language(3,"Python"));
	}

	@Override
	public List<Language> getAll() {
		return languages;
	}

	@Override
	public void add(Language language) {
		languages.add(language);
	}

	@Override
	public void delete(int id) {
		languages.remove(id-1);
	}

	@Override
	public void update(Language language) {
		for (Language l : languages) {
			if(language.getId() == l.getId()) {
				l.setName(language.getName());
			}
		}
	}

	@Override
	public Language getById(int id) {
		return languages.get(id-1);
	}

}
