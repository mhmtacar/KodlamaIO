package Kodlama.io.Devs.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Kodlama.io.Devs.business.abstracts.TechnologyService;
import Kodlama.io.Devs.business.requests.technology.CreateTechnologyRequest;
import Kodlama.io.Devs.business.requests.technology.UpdateTechnologyRequest;
import Kodlama.io.Devs.business.responses.technology.GetAllTechnologiesResponse;
import Kodlama.io.Devs.business.responses.technology.GetTechnologyResponse;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {
	private TechnologyService technologyService;

	@Autowired
	public TechnologiesController(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}
	
	@GetMapping("getall")
	public List<GetAllTechnologiesResponse> getAll(){
		return technologyService.getAll();
	}
	
	@GetMapping("getbyid")
    public GetTechnologyResponse getById(int id) {
		try {
			return technologyService.getById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("add")
    GetTechnologyResponse add(CreateTechnologyRequest technologyDto) {
		try {
			return technologyService.add(technologyDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("update")
    GetTechnologyResponse update(int id, UpdateTechnologyRequest technologyDto) {
		try {
			return technologyService.update(id, technologyDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("delete")
    void delete(int id) {
		try {
			technologyService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}









