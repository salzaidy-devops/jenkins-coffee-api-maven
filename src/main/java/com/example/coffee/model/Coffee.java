package com.example.coffee.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record Coffee(@Min(1) long id, @NotBlank String name, @Min(1) int priceCents) {}
