package com.example.rule_engine.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.example.rule_engine.model.Transaction;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


@Service
public class RuleService {
    private List<String> rules = new ArrayList<>();

    public void addRules(List<String> newRules) {
        rules.addAll(newRules);
    }

    public List<String> getRules() {
        return rules;
    }

    public boolean evaluateRule(String rule, Transaction tx) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
    
        engine.put("amount", tx.getAmount());
        engine.put("type", tx.getType());
        engine.put("category", tx.getCategory());
    
        return (Boolean) engine.eval(rule);
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