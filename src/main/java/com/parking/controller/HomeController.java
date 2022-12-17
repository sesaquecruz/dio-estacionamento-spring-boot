package com.parking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    @Operation(tags = {"Home"})
    public ResponseEntity<String> getHome() {
        return ResponseEntity.ok("Welcome to parking!");
    }
}
