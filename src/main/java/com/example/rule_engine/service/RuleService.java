package com.example.rule_engine.service;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import com.example.rule_engine.model.Transaction;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


@Service
public class RuleService {
    private HashSet<String> rules = new HashSet<>();

    public void addRules(List<String> newRules) {
        for (String rule : newRules) {
            if (rule != null && !rule.trim().isEmpty()) {
                rules.add(rule);
            }
        }
    }

    public List<String> getRules() {
        return new ArrayList<>(rules);
    }

    public boolean evaluateRule(String rule, Transaction tx) throws ScriptException {
        try {
            ScriptEngine engine =
                new ScriptEngineManager().getEngineByName("JavaScript");
    
            engine.put("amount", tx.getAmount());
            engine.put("type", tx.getType());
            engine.put("category", tx.getCategory());
    
            Object result = engine.eval(rule);
    
            if (result instanceof Boolean) {
                return (Boolean) result;
            }
    
            return false;
    
        } catch (Exception e) {
            System.out.println("Invalid rule: " + rule);
            return false;
        }
    }

    public List<Transaction> evaluateTransactions(List<Transaction> transactions) {
        List<Transaction> matched = new ArrayList<>();
    
        for (Transaction tx : transactions) {
            for (String rule : rules) {
                try {
                    if (evaluateRule(rule, tx)) {
                        matched.add(tx);
                        break; // stop checking other rules
                    }
                } catch (Exception e) {
                    // ignore invalid rule
                }
            }
        }
        return matched;
    }
    
    
}