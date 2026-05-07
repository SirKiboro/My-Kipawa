package com.my.kipawa.modules.talent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TalentProfileRequest {

    @NotBlank(message = "Bio is required")
    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    private String bio;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Location is required")
    private String location;

    @Size(min = 1, message = "At least one skill is required")
    private List<String> skills;

    private String portfolioUrl;

}
