package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CustomerDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer customerId;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    @NotBlank
	@Email
	@Size(min=5)
    private String email;

    @JsonProperty(access =Access.WRITE_ONLY)
	@NotBlank
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password!!!!")
	private String password;
   
    private String phoneNumber;
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDate registrationDate;
    
    @JsonProperty(access = Access.READ_ONLY)
    private boolean status;
    
    @JsonProperty(access = Access.READ_ONLY)
    private Role role;
}
