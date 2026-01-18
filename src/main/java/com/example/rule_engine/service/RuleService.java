package com.example.rule_engine.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class RuleService {
    private List<String> rules = new ArrayList<>();

    public void addRules(List<String> newRules) {
        rules.addAll(newRules);
    }

    public List<String> getRules() {
        return rules;
    }
}
