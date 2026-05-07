package com.my.kipawa.modules.talent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.my.kipawa.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "talent_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String phoneNumber;

    private String location;

    @ElementCollection
    @CollectionTable(name = "talent_skills", joinColumns = @JoinColumn(name = "talent_profile_id"))
    @Column(name = "skills")
    @Builder.Default
    private List<String> skills = new ArrayList<>();

    private String portfolioUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
