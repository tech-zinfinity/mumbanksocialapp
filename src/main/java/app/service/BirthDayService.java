package app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.entity.Contact;
import app.data.repository.regular.ContactRepoReg;

@Service
public class BirthDayService {

	@Autowired private ContactRepoReg contactrepo;

	public  List<Contact> getForToday(String td){
		List<Contact> finalC = new ArrayList<>();
		contactrepo.findAll().parallelStream().forEach(data ->{
			if(data.getDob() != null) {
				if(LocalDate.parse(td).getMonth().equals(data.getDob().getMonth()) && LocalDate.parse(td).getDayOfWeek().equals(data.getDob().getDayOfWeek())) {
				finalC.add(data);
				}
			}
		});
		return finalC;
	}
}
