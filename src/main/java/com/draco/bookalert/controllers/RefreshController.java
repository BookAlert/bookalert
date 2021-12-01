package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.services.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class RefreshController {


    @Autowired
    private RefreshService refreshService;

//    @Scheduled(fixedRate = 3600000)
//    public void scheduleRelease() {
//        refreshService.run();
//        System.out.println("Alerted user");
//    }

}
