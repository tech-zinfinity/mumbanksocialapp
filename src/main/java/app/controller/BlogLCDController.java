package app.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.BlogComment;
import app.data.entity.BlogDisLikes;
import app.data.entity.BlogLikes;
import app.data.repository.regular.BlogCommentReactiveRepositiry;
import app.data.repository.regular.BlogDisLikesReactiveRepositiry;
import app.data.repository.regular.BlogLikesReactiveRepositiry;
import app.data.repository.regular.BlogReactiveRepository;
import app.model.Comment;
import app.model.DisLike;
import app.model.Like;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(allowedHeaders="*")
@RestController @RequestMapping("bloglcd")
public class BlogLCDController {

	@Autowired private BlogReactiveRepository blogRepo;
	@Autowired private BlogCommentReactiveRepositiry cmtRepo;
	@Autowired private BlogLikesReactiveRepositiry likesRepo;
	@Autowired private BlogDisLikesReactiveRepositiry dislikesRepo;

	@PostMapping("addLike/{id}")
	public Mono<Boolean> addLike(@PathVariable("id") String id, @RequestBody Like like){
		like.setCreatedOn(LocalDateTime.now());
		return Mono.create(sink ->{
			likesRepo.findByBlogId(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				this.removeDisLike(id, like.getUserId()).subscribe(tt->{
				  BlogLikes b = BlogLikes.builder()
						  .blogId(id)
						  .likes(Arrays.asList(like))
						  .build();
				  likesRepo.save(b)
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
					  err.printStackTrace();
					  sink.success(false);
				  });
				},er->{
					sink.success(false);
				});
			}))
			.subscribe(data ->{
				System.out.println("data id"+data);

				this.removeDisLike(id, like.getUserId()).subscribe(tt->{
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
				}, err->{
					sink.success(false);
				});
			}, err ->{
				sink.success(false);
			});
		});
	}
	
	@PostMapping("addDisLike/{id}")
	public Mono<Boolean> addDisLike(@PathVariable("id") String id, @RequestBody DisLike like){
		like.setCreatedOn(LocalDateTime.now());
		return Mono.create(sink ->{
			dislikesRepo.findByBlogId(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				this.removeLike(id, like.getUserId()).subscribe(tt ->{
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
				},er->{
					sink.success(false);
				});
			}))
			.subscribe(data ->{
				this.removeLike(id, like.getUserId()).subscribe(tt ->{
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
				}, er ->{
					
				});

			}, err ->{
				sink.success(false);
			});
		});
	}
	
	@GetMapping("checkIfUserLiked/{id}/{userId}")
	public Mono<Boolean> checkIfUserLiked(@PathVariable("id") String id, @PathVariable("userId") String userId){
		return Mono.create(sink ->{
			likesRepo.findByBlogId(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				  sink.success(false);
			}))
			.subscribe(data ->{
				if(data.getLikes().stream().filter(d ->d.getUserId().equals(userId)).count()>0) {
					sink.success(true);
				}else {
					sink.success(false);
				}
			}, err ->{
				sink.success(false);
			});
		});
	}
	
	@GetMapping("checkIfUserDisiked/{id}/{userId}")
	public Mono<Boolean> checkIfUserDisiked(@PathVariable("id") String id, @PathVariable("userId") String userId){
		return Mono.create(sink ->{
			dislikesRepo.findByBlogId(id)
			.switchIfEmpty(Mono.fromRunnable( ()->{
				sink.success(false);
			}))
			.subscribe(data ->{
				if(data.getLikes().stream().filter(d ->d.getUserId().equals(userId)).count()>0) {
					sink.success(true);
				}else {
					sink.success(false);
				}
			}, err ->{
				sink.success(false);
			});
		});
	}
	
	public Mono<Boolean> removeLike(@PathVariable("id") String id, @PathVariable("userId") String userId){
		return Mono.create(sink ->{
			this.checkIfUserLiked(id, userId).subscribe(data->{
				if(data) {
					this.likesRepo.findByBlogId(id).subscribe(liked->{
						var flike = liked.getLikes().stream().filter(d -> !(d.getUserId().equals(userId))).collect(Collectors.toList());
						liked.setLikes(flike);
						likesRepo.save(liked).subscribe(slike ->{
							blogRepo.findById(id).subscribe(blog ->{
								if(blog.getDisLikes()>0) {
									blog.setDisLikes(blog.getDisLikes()-1);
								}								
								blogRepo.save(blog).subscribe(fblog->{
									sink.success(true);
								}, er->{
									sink.success(false);
								});
							}, err->{
								sink.success(false);
							});
						}, err->{
							sink.success(false);
						});
					}, er->{
						sink.success(false);
					});
				}else {
					sink.success(false);
				}
			}, err->{
				sink.success(false);
			});
		});
	}
	
	public Mono<Boolean> removeDisLike(@PathVariable("id") String id, @PathVariable("userId") String userId){
		return Mono.create(sink ->{
			this.checkIfUserDisiked(id, userId).subscribe(data->{
				if(data) {
					dislikesRepo.findByBlogId(id).subscribe(liked->{
						var flike = liked.getLikes().stream().filter(d -> !(d.getUserId().equals(userId))).collect(Collectors.toList());
						liked.setLikes(flike);
						dislikesRepo.save(liked).subscribe(slike ->{
							blogRepo.findById(id).subscribe(blog ->{
								if(blog.getDisLikes()>0) {
									blog.setDisLikes(blog.getDisLikes()-1);
								}
								blogRepo.save(blog).subscribe(fblog ->{
									sink.success(true);
								}, er->{
									sink.success(false);
								});
							}, err->{
								sink.success(false);
							});
						}, err->{
							sink.success(false);
						});
					}, er->{
						sink.success(false);
					});
				}else {
					sink.success(false);
				}
			}, err->{
				sink.success(false);
			});
		});
	}
	
	@PostMapping("addComment/{id}")
	public Mono<BlogComment> addComment(@PathVariable("id") String id, @RequestBody Comment comment) {
		return Mono.create(sink ->{
			cmtRepo.findByBlogId(id)
			.switchIfEmpty(Mono.fromRunnable( () ->{
				var c = BlogComment.builder()
						.blogId(id)
						.comment(Arrays.asList(comment))
						.build();
				cmtRepo.insert(c).subscribe(cmt->{
					blogRepo.findById(id).subscribe(blog ->{
						blog.setCommetCount(blog.getCommetCount()+1);
						blogRepo.save(blog).subscribe(fblog->{
							sink.success(cmt);
						}, e->{
							sink.error(e);
						});
					}, er->{
						sink.error(er);
					});
				}, err->{
					sink.error(err);
				});
			}))
			.subscribe(cmt ->{
				var cm = cmt.getComment();
				cm.add(comment);
				cmt.setComment(cm);
				cmtRepo.save(cmt).subscribe(cmtf->{
					blogRepo.findById(id).subscribe(blog ->{
						blog.setCommetCount(blog.getCommetCount()+1);
						blogRepo.save(blog).subscribe(fblog->{
							sink.success(cmtf);
						}, e->{
							sink.error(e);
						});
					}, er->{
						sink.error(er);
					});
				}, err->{
					sink.error(err);
				});
			}, err->{
				sink.error(err);
			});
		});
	}
	
	@GetMapping("getAllCommentsByBlogId/{id}")
	public Mono<BlogComment> getAllCommentsByBlogId(@PathVariable("id") String id){
		return cmtRepo.findByBlogId(id);
	}

}
