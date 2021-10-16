package com.homework.shopingcart.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class Cart {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
  private List<OrderLine> orderLines;
  private long rawTotal;
  private long discount;
  private long vatTax;
  private long total;

  public Cart() {
    orderLines = Collections.emptyList();
    rawTotal = 0;
    discount = 0;
    vatTax = 0;
    total = 0;
  }

  public Cart(List<OrderLine> orderLines, double discountPercentage, boolean isVATIncluded) {
    this.orderLines = orderLines;
    rawTotal = 0;

    orderLines.stream().forEach(orderLine -> {
      rawTotal += orderLine.getCount() * orderLine.getProduct().getPrice();
    });

    discount = (long) Math.round(rawTotal * discountPercentage);

    if (isVATIncluded) {
      vatTax = (long) Math.round((rawTotal - discount) * 0.01f);
    } else {
      vatTax = 0;
    }

    total = rawTotal - discount + vatTax;
  }

  public void addOrderLine(OrderLine orderLine) {
    orderLines.add(orderLine);
    orderLine.setCart(this);
  }

  public void removeOrderLine(OrderLine orderLine) {
    orderLines.remove(orderLine);
    orderLine.setCart(null);
  }

  public void test() {
    // Product p = new Product(0L,'saddd','sad ',1L,'asd');
  }
}