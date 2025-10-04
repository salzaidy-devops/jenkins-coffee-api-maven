package com.example.coffee.service;

import com.example.coffee.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoffeeService {
    private final Map<Long, Coffee> db = new HashMap<>();
    public CoffeeService() {
        db.put(1L, new Coffee(1,"Espresso",300));
        db.put(2L, new Coffee(2,"Latte",450));
        db.put(3L, new Coffee(3,"Cappuccino",400));
    }
    public List<Coffee> all() { return new ArrayList<>(db.values()); }
    public Optional<Coffee> get(long id) { return Optional.ofNullable(db.get(id)); }
    public Coffee add(Coffee c) {
        if (db.containsKey(c.id())) throw new IllegalArgumentException("exists");
        db.put(c.id(), c);
        return c;
    }
    public int totalPriceCents(List<Long> ids) {
        return ids.stream().map(id -> db.getOrDefault(id, new Coffee(0,"Unknown",0)).priceCents())
                .reduce(0, Integer::sum);
    }
}
