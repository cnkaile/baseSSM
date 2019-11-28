package com.nouser.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class OrderNumberGenerator {
	private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
	private static final CountDownLatch latch = new CountDownLatch(1);
	public static final int maxPerMSECSize = 100;

	private static void init() {
		for (int i = 0; i < maxPerMSECSize; i++) {
			queue.offer(Integer.valueOf(i));
		}
		latch.countDown();
	}

	public static Integer poll() {
		try {
			if (latch.getCount() > 0L) {
				init();
				latch.await(1L, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer i = (Integer) queue.poll();
		queue.offer(i);
		return i;
	}

	public static String getOrderNO() {
		long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		String number = 1000 + poll().intValue() + "";
		return nowLong + number.substring(1);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getOrderNO());
		}
		System.out.println(queue.size());
	}
}
