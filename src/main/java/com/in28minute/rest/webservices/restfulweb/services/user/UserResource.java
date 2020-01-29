package com.in28minute.rest.webservices.restfulweb.services.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	// retrieve all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	// retrieve one user
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		 User user=userDaoService.findOne(id);
		 if(user==null) {
			 throw new UserNotFoundException("id-"+id); 
		 }
//		 EntityModel<User> model = new EntityModel<>(user);
//		 WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
//		 model.add(linkTo.withRel("all-users"));
		 return user;
	}

	
	// delete one user
		@DeleteMapping("/users/{id}")
		public User deleteUser(@PathVariable Integer id) {
			 User user=userDaoService.deleteById(id);
			 if(user==null) {
				 throw new UserNotFoundException("id-"+id); 
			 }
			 return user;
		}
	// add a user
	@PostMapping("/users")
	public ResponseEntity addUser(@Valid @RequestBody User user) {

		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
