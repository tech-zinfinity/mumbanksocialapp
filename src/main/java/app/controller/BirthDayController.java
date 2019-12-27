package app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Contact;
import app.data.repository.regular.ContactRepoReg;


@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("dob")
public class BirthDayController {

	@Autowired private ContactRepoReg contactrepo;
	
	@GetMapping("getForToday/{td}")
	public List<Contact> getTodaysBOD(@PathVariable String td){
		System.out.println("beduk"+td);
		List<Contact> finalC = new ArrayList<>();
		contactrepo.findAll().stream().forEach(data ->{
			System.out.println(data.getDob());
			if(LocalDate.parse(td).equals(data.getDob())) {
				finalC.add(data);
			}
		});
		return finalC;
	}
	
	
}
