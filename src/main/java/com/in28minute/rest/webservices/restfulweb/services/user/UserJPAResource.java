package com.in28minute.rest.webservices.restfulweb.services.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	// retrieve all users
	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// retrieve one user
	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	// delete one user
	@DeleteMapping("/jpa/users/{id}")
	public User deleteUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		userRepository.deleteById(id);

		return user.get();
	}

	// add a user
	@PostMapping("/jpa/users")
	public ResponseEntity addUser(@Valid @RequestBody User user) {

		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

		// return ResponseEntity.created(null).build();

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllUsersPosts(@PathVariable Integer id) {

		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		return userOptional.get().getPosts();

	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity createUsersPosts(@PathVariable Integer id, @RequestBody Post post) {

		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		post.setUser(userOptional.get());
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
