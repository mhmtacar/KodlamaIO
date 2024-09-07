package Kodlama.io.Devs.business.abstracts;

import java.util.List;

import Kodlama.io.Devs.business.requests.technology.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technology.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologiesResponse;
import Kodlama.io.Devs.business.responses.technology.GetTechnologyResponse;

public interface TechnologyService {
	List<GetAllTechnologiesResponse> getAll();
    GetTechnologyResponse getById(int id) throws Exception;
    GetTechnologyResponse add(CreateTechnologyRequest technologyDto) throws Exception;
    GetTechnologyResponse update(int id, UpdateTechnologyRequest technologyDto) throws Exception;
    void delete(int id) throws Exception;
}
