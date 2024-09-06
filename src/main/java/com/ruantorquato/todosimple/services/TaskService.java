package com.ruantorquato.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruantorquato.todosimple.models.Task;
import com.ruantorquato.todosimple.models.User;
import com.ruantorquato.todosimple.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserService userService;

	public Task findById(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.orElseThrow(
				() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));

	}

	public List<Task> findAllByUserId(Long userId) {
		List<Task> tasks = taskRepository.findByUser_Id(userId);
		return tasks;
	}

	public Task create(Task obj) {
		User user = this.userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);
		obj = taskRepository.save(obj);
		return obj;
	}

	public Task update(Task obj) {
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return taskRepository.save(newObj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}

}
