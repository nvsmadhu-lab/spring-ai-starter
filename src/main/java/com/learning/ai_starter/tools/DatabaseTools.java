package com.learning.ai_starter.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseTools {

    // Simulated database
    private static final Map<String, Map<String, String>> PRODUCTS = new HashMap<>();
    private static final Map<String, String> ORDER_STATUS = new HashMap<>();

    static {
        // Mock product data
        PRODUCTS.put("P001", Map.of(
                "name", "Spring Boot Book",
                "price", "₹599",
                "stock", "In Stock"
        ));
        PRODUCTS.put("P002", Map.of(
                "name", "Java Complete Guide",
                "price", "₹799",
                "stock", "Out of Stock"
        ));
        PRODUCTS.put("P003", Map.of(
                "name", "Microservices Patterns",
                "price", "₹999",
                "stock", "In Stock"
        ));

        // Mock order data
        ORDER_STATUS.put("ORD001", "Delivered on 10-Jun-2025");
        ORDER_STATUS.put("ORD002", "Out for Delivery");
        ORDER_STATUS.put("ORD003", "Processing");
    }

    @Tool(description = "Get product details by product ID. " +
            "Returns name, price, and stock status.")
    public String getProductDetails(String productId) {
        Map<String, String> product = PRODUCTS.get(productId.toUpperCase());
        if (product == null) {
            return "Product not found: " + productId;
        }
        return String.format("Product: %s | Price: %s | Stock: %s",
                product.get("name"),
                product.get("price"),
                product.get("stock"));
    }

    @Tool(description = "Check order status by order ID.")
    public String getOrderStatus(String orderId) {
        String status = ORDER_STATUS.get(orderId.toUpperCase());
        return status != null ? "Order " + orderId + ": " + status
                : "Order not found: " + orderId;
    }
}
