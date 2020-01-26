package com.mrj.opportunity.controller;

import com.mrj.opportunity.model.Opportunity;
import com.mrj.opportunity.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {

    @Autowired
    private OpportunityRepository opportunities;

    @GetMapping
    public List<Opportunity> list() {
        return opportunities.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> search(@PathVariable Long id) {
        Optional<Opportunity> opportunity = opportunities.findById(id);

        return opportunity.isPresent() ? ResponseEntity.ok(opportunity.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Opportunity add(@Valid @RequestBody Opportunity opportunity) {
        Optional<Opportunity> opportunityExistent = opportunities
                .findByDescriptionAndProspectName(opportunity.getDescription(),
                        opportunity.getProspectName());

        if (opportunityExistent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Oportunidade Existente para este prospecto");
        }

        return opportunities.save(opportunity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        opportunities.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Opportunity> update(@PathVariable Long id, @Valid @RequestBody Opportunity opportunity) {

        return opportunities.findById(id).map(item -> {
            item.setProspectName(opportunity.getProspectName());
            item.setDescription(opportunity.getDescription());
            item.setValue(opportunity.getValue());
            Opportunity opportunityUpdated = opportunities.save(item);
            return ResponseEntity.ok().body(opportunityUpdated);
        }).orElse(ResponseEntity.notFound().build());
    }

}