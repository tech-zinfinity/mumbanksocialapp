package app.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.BlogDisLikes;
import app.data.entity.BlogLikes;
import app.data.repository.BlogRepository;
import app.data.repository.regular.BlogCommentReactiveRepositiry;
import app.data.repository.regular.BlogDisLikesReactiveRepositiry;
import app.data.repository.regular.BlogLikesReactiveRepositiry;
import app.data.repository.regular.BlogReactiveRepository;
import app.model.DisLike;
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
				  var b = BlogLikes.builder()
						  .blogId(id)
						  .likes(Arrays.asList(like))
						  .build();
				  likesRepo.insert(b)
				  .subscribe(d->{
						blogRepo.findById(id).subscribe(blog ->{
							blog.setLikes(d.getLikes().size());
							blogRepo.save(blog).subscribe(t ->{
								sink.success(true);
							}, er->{
								sink.success(false);
							});
						}, err->{
							sink.success(false);
						});
				  },err ->{
					  sink.success(false);
				  });  
			}))
			.subscribe(data ->{
				Set<Like> d = data.getLikes().stream().collect(Collectors.toSet());
				d.add(like);
				data.setLikes(d.stream().collect(Collectors.toList()));
				likesRepo.save(data).subscribe(o ->{
					blogRepo.findById(id).subscribe(blog ->{
						blog.setLikes(data.getLikes().size());
						blogRepo.save(blog).subscribe(t ->{
							sink.success(true);
						}, er->{
							sink.success(false);
						});
					}, err->{
						sink.success(false);
					});
				},err->{
					sink.success(false);

				});
			}, err ->{
				sink.success(false);
			});
		});
	}
	
	public Mono<Boolean> addDisLike(@PathVariable("id") String id, @RequestBody DisLike like){
		return Mono.create(sink ->{
			dislikesRepo.findById(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				  var b = BlogDisLikes.builder()
						  .blogId(id)
						  .likes(Arrays.asList(like))
						  .build();
				  dislikesRepo.insert(b)
				  .subscribe(d->{
						blogRepo.findById(id).subscribe(blog ->{
							blog.setDisLikes(d.getLikes().size());
							blogRepo.save(blog).subscribe(t ->{
								sink.success(true);
							}, er->{
								sink.success(false);
							});
						}, err->{
							sink.success(false);
						});
				  },err ->{
					  sink.success(false);
				  });  
			}))
			.subscribe(data ->{
				Set<DisLike> d = data.getLikes().stream().collect(Collectors.toSet());
				d.add(like);
				data.setLikes(d.stream().collect(Collectors.toList()));
				dislikesRepo.save(data).subscribe(o ->{
					blogRepo.findById(id).subscribe(blog ->{
						blog.setDisLikes(data.getLikes().size());
						blogRepo.save(blog).subscribe(t ->{
							sink.success(true);
						}, er->{
							sink.success(false);
						});
					}, err->{
						sink.success(false);
					});
				},err->{
					sink.success(false);

				});
			}, err ->{
				sink.success(false);
			});
		});
	}
	
	public Mono<Boolean> checkIfUserLiked(@PathVariable("id") String id, @RequestBody Like like){
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
