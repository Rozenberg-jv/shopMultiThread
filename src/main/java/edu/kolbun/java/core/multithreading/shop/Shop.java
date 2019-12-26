package edu.kolbun.java.core.multithreading.shop;

import java.util.*;
import java.util.stream.Stream;

public class Shop extends Thread {

	private final List<Cashbox> cashboxLine = new ArrayList<>();

	private final Queue<Buyer> buyersQueue = new ArrayDeque<>();

	public Shop(int cashboxCount) {

		for (int i = 0; i < cashboxCount; i++)
			cashboxLine.add(new Cashbox(i + 1));
	}

	public void addNumberOfBuyersToQueue(int number) {

		Stream.iterate(0, x -> x < number, x -> x + 1).forEach(x -> this.addBuyerToQueueWithNumberOfProducts((int) (Math.random() * 5 + 1)));
	}

	@Override
	public void run() {

		while (!buyersQueue.isEmpty() || cashboxLine.stream().anyMatch(Cashbox::isOccupied)) {
			Buyer awaitingBuyer = buyersQueue.peek();

			if (awaitingBuyer != null)
				getFreeCashbox().ifPresent(cb -> {
					awaitingBuyer.setCashbox(cb);
					awaitingBuyer.start();
					buyersQueue.remove();
				});
		}
	}

	// privates
	private Optional<Cashbox> getFreeCashbox() {

		return cashboxLine.stream().filter(cb -> !cb.isOccupied()).findFirst();
	}

	private void addBuyerToQueueWithNumberOfProducts(int numberOfProducts) {

		buyersQueue.add(new Buyer(numberOfProducts));
	}
}