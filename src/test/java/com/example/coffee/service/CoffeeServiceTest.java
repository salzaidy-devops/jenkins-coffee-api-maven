package com.example.coffee.service;

import com.example.coffee.model.Coffee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CoffeeServiceTest {
    @Test void all_hasDefaults() {
        CoffeeService svc = new CoffeeService();
        assertTrue(svc.all().size() >= 3);
    }
    @Test void get_found() {
        CoffeeService svc = new CoffeeService();
        assertTrue(svc.get(1L).isPresent());
    }
    @Test void get_missing() {
        CoffeeService svc = new CoffeeService();
        assertTrue(svc.get(999L).isEmpty());
    }
    @Test void add_new() {
        CoffeeService svc = new CoffeeService();
        Coffee c = new Coffee(9,"Mocha",500);
        assertEquals(9, svc.add(c).id());
    }
    @Test void total_sums() {
        CoffeeService svc = new CoffeeService();
        int total = svc.totalPriceCents(List.of(1L,2L,3L));
        assertTrue(total > 0);
    }
}
