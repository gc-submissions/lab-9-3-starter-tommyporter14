//9.3 Tommy Porter & Clara Balmer
package co.grandcircus.trackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.trackerapi.CountPairsRepository;
import co.grandcircus.trackerapi.model.CountPair;
import co.grandcircus.trackerapi.service.TrackerService;
import co.grandcircus.trackerapi.service.TrackerServiceA;

@RestController
public class TrackerApiController {
	
	// When you have multiple services that implement TrackerService,
	// the @Qualifier annotation picks which to inject here.
//	@Qualifier("trackerServiceA")
	@Autowired
	private TrackerServiceA service;
	
	@Autowired
	private CountPairsRepository repo;
	private String recent;

	@PostMapping("/track/{token}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void track(@PathVariable String token) {
		service.add(token);
		recent = token;
	}
	
	@DeleteMapping("/track")
	public void reset() {
		service.reset();
		recent = "";
	}
	
	@GetMapping("/count")
	public int count() {
		return service.getTotalCount();
	}
	
	@GetMapping("/exists/{token}")
	public boolean exists(@PathVariable String token) {
		return service.getTokenExists(token);
	}
	
	@GetMapping("/count/{token}")
	public int count(@PathVariable String token) {
		return service.getTokenCount(token);
	}
	
	@GetMapping("/latest")
	public String latest() {
		service.sendRecent(recent);
		return service.getLatest();
	}
	
	@GetMapping("/top")
	public CountPair top() {
		return service.getTop();
	}
	
	@GetMapping("/latest-5")
	public List<String> latest5() {
		return service.getLatest5();
	}
	
	@GetMapping("/top-5")
	public List<CountPair> top5() {
		return service.getTop5();
	}
}
