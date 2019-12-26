package edu.kolbun.java.core.multithreading.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Buyer extends Thread {

	private static int buyersCounter = 0;

	private String name;

	private final List<Product> productList = new ArrayList<>();

	private Cashbox cashbox;

	Buyer(String... products) {

		this.setDaemon(true);

		this.name = "Buyer" + buyersCounter++;

		productList.addAll(Arrays.stream(products).map(Product::new).collect(Collectors.toList()));
	}

	Buyer(int productsCount) {

		this.setDaemon(true);

		this.name = "Buyer" + buyersCounter++;

		productList.addAll(Stream.iterate(
				0,
				x -> x < productsCount,
				x -> x + 1
		).map(
				x -> Product.getRandomProduct()
		).collect(Collectors.toList()));
	}

	@Override
	public void run() {

		if (cashbox == null)
			throw new RuntimeException("No cashbox set for this buyer");

		System.out.printf("Buyer %s occupy cashbox %s ->\n", this, this.cashbox);

		synchronized (cashbox) {
			for (Product product : productList) {
				try {
					cashbox.sellProduct(product);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
			cashbox.setOccupied(false);
		}
	}

	void setCashbox(Cashbox cashbox) {

		this.cashbox = cashbox;
		this.cashbox.setOccupied(true);
	}

	@Override
	public String toString() {

		String products = productList.stream().map(Product::toString).collect(Collectors.joining(", "));

		return name + "[" + products + "]";
	}
}
