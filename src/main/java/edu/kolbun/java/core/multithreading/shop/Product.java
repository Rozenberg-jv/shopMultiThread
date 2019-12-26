package edu.kolbun.java.core.multithreading.shop;

public class Product {

	private static int productsCount = 0;

	private final String title;

	public Product(String title) {

		this.title = title;
	}

	public static Product getRandomProduct() {

		return new Product("ExampleProduct" + productsCount++);
	}

	@Override
	public String toString() {

		return this.title;
	}
}
