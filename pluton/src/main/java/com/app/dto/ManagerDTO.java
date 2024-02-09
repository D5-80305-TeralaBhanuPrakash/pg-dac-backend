package com.app.dto;

import com.app.entities.ManagerRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class ManagerDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

    
    private String username;

    
    private String password; // Consider using a secure password hashing mechanism

    
    private String firstName;

    
    private String lastName;

    
    private String email;

    private ManagerRole role;
}
