package com.my.kipawa.modules.talent.repository;

import com.my.kipawa.modules.talent.entity.TalentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<TalentProfile, Long> {

    Optional<TalentProfile> findByUserId(Long userId);
}
