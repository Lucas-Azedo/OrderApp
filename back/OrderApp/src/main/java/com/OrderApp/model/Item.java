package com.OrderApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    UUID id;

    String name;

    String description;
}
