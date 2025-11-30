
package com.bhavana.inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items", uniqueConstraints = @UniqueConstraint(columnNames = {"sku"}))
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    private String sku;

    @NotBlank
    @Size(max = 120)
    private String name;

    @Min(0)
    private int quantity = 0;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price = BigDecimal.ZERO;

    public Item() {}

    public Item(String sku, String name, int quantity, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
