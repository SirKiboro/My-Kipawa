package com.my.kipawa.modules.talent.service;

import com.my.kipawa.modules.talent.dto.TalentProfileRequest;
import com.my.kipawa.modules.talent.entity.TalentProfile;
import com.my.kipawa.modules.talent.repository.TalentRepository;
import com.my.kipawa.modules.user.entity.User;
import com.my.kipawa.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentService {

    private final TalentRepository talentRepository;
    private final UserRepository userRepository;

    @Transactional
    public TalentProfile createProfile(Long userId, TalentProfileRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (talentRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Talent profile already exists for this user");
        }

        TalentProfile profile = TalentProfile.builder()
                .bio(request.getBio())
                .phoneNumber(request.getPhoneNumber())
                .location(request.getLocation())
                .skills(request.getSkills() != null ? request.getSkills() : List.of())
                .portfolioUrl(request.getPortfolioUrl())
                .user(user)
                .build();

        return talentRepository.save(profile);
    }

    public TalentProfile getProfileByUserId(Long userId) {
        return talentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
    }
}
