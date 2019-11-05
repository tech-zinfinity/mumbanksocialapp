//package app.config;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.security.core.userdetails.User;
//import app.data.repository.UserRepository;
//
//@Component
//public class MongoUserDetailsService implements UserDetailsService{
//
//	  @Autowired
//	  private UserRepository repository;
//
//	  @Override
//	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		  	Optional<app.data.entity.User> user = repository.findByUsername(username);
//			if(user.isEmpty()) {
//			  throw new UsernameNotFoundException("User not found");
//			}else {
//				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//				user.get().setPassword(passwordEncoder.encode(user.get().getPassword()));
//			}
//			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//			
//			return new User(user.get().getUsername(), user.get().getPassword(), authorities);
//	  }
//}
