package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.language.CreateLanguageRequest;
import Kodlama.io.Devs.business.requests.language.UpdateLanguageRequest;
import Kodlama.io.Devs.business.responses.language.GetAllLanguagesResponse;
import Kodlama.io.Devs.business.responses.language.GetLanguageResponse;

public interface LanguageService {
	GetLanguageResponse add(CreateLanguageRequest createLanguageRequest) throws Exception;
	void delete(int id) throws Exception;
	GetLanguageResponse update(int id, UpdateLanguageRequest updateLanguageRequest) throws Exception;
	List<GetAllLanguagesResponse> getAll();
	GetLanguageResponse getById(int id) throws Exception;
}
