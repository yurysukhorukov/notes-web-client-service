package com.epam.noteswebclientservice.noteswebclientservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("notekeeper-service")
public interface NotesClient {

    @RequestMapping("/notes")
    String getNotes();
}
