package com.project.LeaugeOfLegendsApp.auth.dto;

public record CreateUserForm(String name, String secondName, String surname, Short yearOfBirth, String username, String password, String email) {
}
