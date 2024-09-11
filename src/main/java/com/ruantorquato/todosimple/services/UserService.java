package com.ruantorquato.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruantorquato.todosimple.models.User;
import com.ruantorquato.todosimple.repository.TaskRepository;
import com.ruantorquato.todosimple.repository.UserRepository;
import com.ruantorquato.todosimple.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;

	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));

	}

	public User create(User obj) {
		obj = userRepository.save(obj);
		taskRepository.saveAll(obj.getTasks());
		return obj;

	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return userRepository.save(newObj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
		}
	}
}
