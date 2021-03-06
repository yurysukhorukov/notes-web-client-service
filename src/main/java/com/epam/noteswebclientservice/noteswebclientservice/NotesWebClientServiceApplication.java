package com.epam.noteswebclientservice.noteswebclientservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;

@EnableFeignClients
@SpringBootApplication
@Controller
public class NotesWebClientServiceApplication {

	@Autowired
	NotesClient notesClient;

	public static void main(String[] args) {
		SpringApplication.run(NotesWebClientServiceApplication.class, args);
	}

	@RequestMapping("/get-notes")
	public String getNotes(Model model) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> notes = new TreeMap<>();
		try {
			notes = mapper.readValue(notesClient.getNotes(), new TypeReference<TreeMap<String,String>>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("notes", notes);
		return "notebook";
	}
}
