package app.sheduler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.data.repository.UserRepository;
import app.http.request.SendMessageRequest;
import app.service.BirthDayService;
import app.service.MessageService;
import app.data.entity.Contact;


@Service
public class BithrdayMessageScheduler {
	
	@Autowired BirthDayService bdservice;
	@Autowired UserRepository userrepo;
	@Autowired MessageService messageservice;
	
	String message = "Today's Birthday From App \n";

	
	@Scheduled(cron = "0 0 9 * * ?")
	public void fetchContactsAndSendMessageScheduler() {
		userrepo.findAll().stream().filter(data -> data.getRole().equalsIgnoreCase("ADMIN")).forEach(test ->{
			List<Contact> list = bdservice.getForToday(LocalDate.now().toString());
			System.out.println(list);
				if(!list.isEmpty()) {
					list.stream().forEachOrdered(contact ->{
						System.out.println("coming here");
						message = message.concat("\n"+contact.getName()+"\t"+contact.getPhoneno1());
						if(!contact.getPhoneno2().isEmpty()) {
							System.out.println(message);
							message = message.concat("\t"+contact.getPhoneno2());
						}
					});
				messageservice.sendMessage(SendMessageRequest.builder().numbers(Arrays.asList(test.getMobileno())).message(message).build());
				message = "";
				}
		});
		
	}
}
