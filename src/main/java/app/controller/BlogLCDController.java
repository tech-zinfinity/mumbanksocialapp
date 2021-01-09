package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.repository.BlogRepository;
import app.data.repository.regular.BlogCommentReactiveRepositiry;
import app.data.repository.regular.BlogDisLikesReactiveRepositiry;
import app.data.repository.regular.BlogLikesReactiveRepositiry;
import app.data.repository.regular.BlogReactiveRepository;
import app.model.Like;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("bloglcd")
public class BlogLCDController {

	@Autowired private BlogReactiveRepository blogRepo;
	@Autowired private BlogCommentReactiveRepositiry cmtRepo;
	@Autowired private BlogLikesReactiveRepositiry likesRepo;
	@Autowired private BlogDisLikesReactiveRepositiry dislikesRepo;

	
	public Mono<Boolean> addLike(@PathVariable("id") String id, @RequestBody Like like){
		return Mono.create(sink ->{
			likesRepo.findById(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				  
			}))
			.subscribe(data ->{
				
			}, err ->{
				
			});
		});
	}
	
//	addDisLike(){
//		
//	}
//	
//	addComment(){
//		
//	}
}
