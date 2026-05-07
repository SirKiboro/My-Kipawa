package com.my.kipawa.modules.talent.controller;

import com.my.kipawa.common.response.ApiResponse;
import com.my.kipawa.modules.talent.dto.TalentProfileRequest;
import com.my.kipawa.modules.talent.entity.TalentProfile;
import com.my.kipawa.modules.talent.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/talents")
@RequiredArgsConstructor
public class TalentController {

    private final TalentService talentService;

    @PostMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<TalentProfile>> createProfile(
            @PathVariable Long userId,
            @RequestBody TalentProfileRequest request){

        TalentProfile profile = talentService.createProfile(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Profile created successfully", profile));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ApiResponse<TalentProfile>> getProfile(
            @PathVariable Long userId){

        TalentProfile profile = talentService.getProfileByUserId(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Profile retrieved successfully", profile));


    }


}
