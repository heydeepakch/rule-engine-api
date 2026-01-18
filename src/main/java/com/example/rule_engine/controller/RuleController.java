package com.example.rule_engine.controller;

import com.example.rule_engine.service.RuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/rules")
public class RuleController {
    
    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public String addRules(@RequestBody List<String> rules) {
        ruleService.addRules(rules);
        return "Rules added!";
    }

    @GetMapping
    public List<String> getRules() {
        return ruleService.getRules();
    }

}
