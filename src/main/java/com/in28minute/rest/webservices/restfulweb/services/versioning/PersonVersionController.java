package com.in28minute.rest.webservices.restfulweb.services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	@GetMapping("/v1/person")
	public PersonV1 getPerson1URI() {
		return new PersonV1("Ankur Saxena");
	}

	@GetMapping("/v2/person")
	public PersonV2 getPerson2URI() {
		return new PersonV2(new Name("Ankur"," Saxena"));
	}
	
	@GetMapping(value="/person/params" ,params ="version=1")
	public PersonV1 getPerson1Params() {
		return new PersonV1("Ankur Saxena");
	}

	@GetMapping(value="/person/params",params ="version=2")
	public PersonV2 getPerson2Parama( ) {
		return new PersonV2(new Name("Ankur"," Saxena"));
	}
	
	@GetMapping(value="/person/header" ,headers =  "X_PATH_VERSION=1")
	public PersonV1 getPerson1Header() {
		return new PersonV1("Ankur Saxena");
	}

	@GetMapping(value="/person/header",headers ="X_PATH_VERSION=2")
	public PersonV2 getPerson2Header( ) {
		return new PersonV2(new Name("Ankur"," Saxena"));
	}
	
	@GetMapping(value="/person/produces" ,produces =  "application/com.my.company1+json")
	public PersonV1 getPerson1Produces() {
		return new PersonV1("Ankur Saxena");
	}

	@GetMapping(value="/person/produces",produces ="application/com.my.company2+json")
	public PersonV2 getPerson2Produces( ) {
		return new PersonV2(new Name("Ankur"," Saxena"));
	}
	
}
