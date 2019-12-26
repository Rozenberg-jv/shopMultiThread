package edu.kolbun.java.core.multithreading;

/*
 * Напишите программу моделрующую работу кассы в магазине. Существуют несколько касс, работающих одновременно.
 * Каждый покупатель - отдельный поток. Общее количество покупателей может быть больше чем количество касс, но
 * одновременно не может обрабатываться покупателей больше, чем имеется рабочих касс. У каждого покупателя есть
 * набор товаров, которые должны быть выведены в процессе обслуживания.
 */

import edu.kolbun.java.core.multithreading.shop.Shop;

public class Main {

	public static void main(String[] args) throws Exception {

		// устанавливаем количество касс
		Shop shop = new Shop(2);

		// добавляем людей в общую очередь
		shop.addNumberOfBuyersToQueue(3);

		// стартуем магазин
		shop.start();

		// ждем 3 секунды
		Thread.sleep(3000);

		// добавляем еще людей в очередь
		shop.addNumberOfBuyersToQueue(3);

		shop.join();

		System.out.println("!!! end !!!");
	}

}
