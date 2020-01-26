package com.mrj.opportunity.repository;

import com.mrj.opportunity.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    Optional<Opportunity> findByDescriptionAndProspectName(String description, String prospectName);
}
