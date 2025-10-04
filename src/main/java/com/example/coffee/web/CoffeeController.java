package com.example.coffee.web;

import com.example.coffee.model.Coffee;
import com.example.coffee.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {
    private final CoffeeService service;
    public CoffeeController(CoffeeService service) { this.service = service; }

    @GetMapping public List<Coffee> all() { return service.all(); }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> one(@PathVariable long id) {
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coffee> add(@Valid @RequestBody Coffee c) {
        return ResponseEntity.ok(service.add(c));
    }

    @PostMapping("/total")
    public int total(@RequestBody List<Long> ids) {
        return service.totalPriceCents(ids);
    }

    @GetMapping("/health")
    public String health() { return "OK"; }
}
