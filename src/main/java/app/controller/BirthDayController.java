package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Contact;
import app.data.entity.Members;
import app.data.entity.RegisteredUsers;
import app.data.repository.regular.ContactRepoReg;
import app.data.repository.regular.RTrustRepo;
import app.data.repository.regular.RUserRepo;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("dob")
public class BirthDayController {

	@Autowired private RUserRepo userrepo;
	@Autowired private RTrustRepo trustrepo;
	@Autowired private ContactRepoReg contactrepo;
	
	
	@GetMapping("getForToday/{td}")
	public List<Contact> getTodaysBOD(@PathVariable String td){
		
//		List<Contact> finalvalue = new ArrayList<>();
//		
//		List<RegisteredUsers> rusers = userrepo.findByDob(td);
//		
//		List<Members> members = new ArrayList<>();
//		List<Members> members2 = new ArrayList<Members>();
//		trustrepo.findAll().stream().forEach(value -> value.getMembers().forEach(data ->
//		{
//			if(data != null ) {
//				members.add(data);
//			}
//		}
//		));
//		members2 = members.stream().filter(data -> data.getDob().equals(td)).collect(Collectors.toList());
//		
//		List<Contact> contacts = contactrepo.findByDob(td);
//		
//		rusers.stream().forEach(data ->{
//			if(data != null) {
//				Contact c  = Contact.builder().name(data.getName()).dob(data.getDob()).build();
//				finalvalue.add(c);
//			}
//		});
//		
//		members2.stream().forEach(data ->{
//			Contact c  = Contact.builder().name(data.getMemname()).dob(data.getDob()).build();
//			finalvalue.add(c);
//		});
//		contacts.stream().forEach(data ->{
//			Contact c  = Contact.builder().name(data.getName()).dob(data.getDob()).build();
//			finalvalue.add(c);
//		});
//		
//		return finalvalue;
//	}
		return  null;
	}
	
}
