package com.carloszaragoza.UnitConverter.controller;

import com.carloszaragoza.UnitConverter.model.Measure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConvertController {
    private static final Map<String, Double> LENGTH_FACTORS = new HashMap<>();
    private static final Map<String, Double> WEIGHT_FACTORS = new HashMap<>();

    static {
        // Length conversion factors relative to meters
        LENGTH_FACTORS.put("millimeter", 1000.0);
        LENGTH_FACTORS.put("centimeter", 100.0);
        LENGTH_FACTORS.put("meter", 1.0);
        LENGTH_FACTORS.put("kilometer", 0.001);
        LENGTH_FACTORS.put("inch", 39.3701);
        LENGTH_FACTORS.put("foot", 3.28084);
        LENGTH_FACTORS.put("yard", 1.09361);
        LENGTH_FACTORS.put("mile", 0.000621371);

        // Weight conversion factors relative to grams
        WEIGHT_FACTORS.put("milligram", 1000.0);
        WEIGHT_FACTORS.put("gram", 1.0);
        WEIGHT_FACTORS.put("kilogram", 0.001);
        WEIGHT_FACTORS.put("ounce", 0.035274);
        WEIGHT_FACTORS.put("pound", 0.00220462);
    }

    @GetMapping("/convert")
    public ResponseEntity<Object> convertValue(
            @RequestParam(name = "value", value = "") String value,
            @RequestParam(name = "prevMeasure", value = "") String prevMeasure,
            @RequestParam(name = "finalMeasure", value = "") String finalMeasure,
            @RequestParam(name = "type", value = "") String typeOfMeasure) {

        if (value.isEmpty() || prevMeasure.isEmpty() || finalMeasure.isEmpty() || typeOfMeasure.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: All parameters (value, prevMeasure, finalMeasure, type) are required.");
        }

        double currentValue;
        try {
            currentValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Error: Invalid numeric value. " + e.getMessage());
        }

        try {
            double convertedValue = switch (typeOfMeasure.toLowerCase()) {
                case "length" -> convertUsingFactors(currentValue, prevMeasure, finalMeasure, LENGTH_FACTORS);
                case "weight" -> convertUsingFactors(currentValue, prevMeasure, finalMeasure, WEIGHT_FACTORS);
                case "temperature" -> convertTemperature(currentValue, prevMeasure.toLowerCase(), finalMeasure.toLowerCase());
                default -> throw new IllegalArgumentException("Invalid measure type: " + typeOfMeasure);
            };

            return ResponseEntity.ok(new Measure(prevMeasure, currentValue, convertedValue, finalMeasure));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Generalized conversion using predefined factors.
     */
    private double convertUsingFactors(double value, String fromUnit, String toUnit, Map<String, Double> factors) {
        if (!factors.containsKey(fromUnit) || !factors.containsKey(toUnit)) {
            throw new IllegalArgumentException("Invalid units: " + fromUnit + " or " + toUnit);
        }
        double valueInBaseUnit = value / factors.get(fromUnit); // Convert to base unit
        return valueInBaseUnit * factors.get(toUnit);           // Convert to target unit
    }

    /**
     * Handles temperature conversion specifically.
     */
    private double convertTemperature(double value, String fromUnit, String toUnit) {
        double valueInCelsius;

        // Convert to Celsius as base unit
        valueInCelsius = switch (fromUnit) {
            case "celsius" -> value;
            case "fahrenheit" -> (value - 32) * 5 / 9;
            case "kelvin" -> value - 273.15;
            default -> throw new IllegalArgumentException("Invalid source unit for temperature: " + fromUnit);
        };

        // Convert from Celsius to target unit
        return switch (toUnit) {
            case "celsius" -> valueInCelsius;
            case "fahrenheit" -> (valueInCelsius * 9 / 5) + 32;
            case "kelvin" -> valueInCelsius + 273.15;
            default -> throw new IllegalArgumentException("Invalid target unit for temperature: " + toUnit);
        };
    }
}
