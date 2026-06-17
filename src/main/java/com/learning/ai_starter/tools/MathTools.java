package com.learning.ai_starter.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class MathTools {

    @Tool(description = "Calculate simple math expressions. " +
            "call this for any mathematical calculations or When asked to calculate something.")
    public double calculate(String expression){
        // Simple calculator using ScriptEngine
        try {
            javax.script.ScriptEngine engine =
                    new javax.script.ScriptEngineManager()
                            .getEngineByName("JavaScript");
            Object result = engine.eval(expression);
            return Double.parseDouble(result.toString());
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Tool(description = "Calculate compound interest. " +
            "Parameters: principal amount, annual rate (%), " +
            "time in years, compounds per year.")
    public String calculateCompoundInterest(
            double principal,
            double annualRate,
            int years,
            int compoundsPerYear) {

        double amount = principal *
                Math.pow(1 + (annualRate / 100) / compoundsPerYear,
                        compoundsPerYear * years);
        double interest = amount - principal;

        return String.format("""
                Principal: ₹%.2f
                Annual Rate: %.1f%%
                Time: %d years
                Final Amount: ₹%.2f
                Interest Earned: ₹%.2f
                """, principal, annualRate, years, amount, interest);
    }


}
