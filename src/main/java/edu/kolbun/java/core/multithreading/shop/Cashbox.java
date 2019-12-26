package edu.kolbun.java.core.multithreading.shop;

class Cashbox {

	private final String name;

	private boolean occupied = false;

	Cashbox(int number) {

		this.name = "Cashbox#" + number;
	}

	void sellProduct(Product product) {

		System.out.printf("Cashbox [%s] is processing [%s]\n", name, product);
	}

	boolean isOccupied() {

		return occupied;
	}

	void setOccupied(boolean occupied) {

		this.occupied = occupied;
	}

	@Override
	public String toString() {

		return this.name;
	}
}
