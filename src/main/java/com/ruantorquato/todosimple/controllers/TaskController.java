package com.ruantorquato.todosimple.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ruantorquato.todosimple.models.Task;
import com.ruantorquato.todosimple.services.TaskService;
import com.ruantorquato.todosimple.services.UserService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id){
		Task obj = taskService.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity <List<Task>> findAllByUserId(@PathVariable Long userId){
		userService.findById(userId);
		List<Task> objs = taskService.findAllByUserId(userId);
		return ResponseEntity.ok().body(objs);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Task obj){
		taskService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Task obj, @PathVariable Long id){
		obj.setId(id);
		taskService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		taskService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
