package com.my.kipawa.modules.talent.dto;

import lombok.Data;

import java.util.List;

@Data
public class TalentProfileRequest {

    private String bio;
    private String phoneNumber;
    private String location;
    private List<String> skills;
    private String portfolioUrl;

}
