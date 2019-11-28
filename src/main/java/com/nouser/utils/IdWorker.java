package com.nouser.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;

/**
 * 项目地址为：https://github.com/twitter/snowflake
 * 
 * @author hjf
 * @date 2016-12-15
 * @pack com.qcode.core.util
 */
public class IdWorker {
	private final static long twepoch = 1288834974657L;
	private final static long workerIdBits = 5L;
	private final static long datacenterIdBits = 5L;
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final static long sequenceBits = 12L;
	private final static long workerIdShift = sequenceBits;
	private final static long datacenterIdShift = sequenceBits + workerIdBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;
	
	private static IdWorker idWorker = null;

	public IdWorker(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}

	private IdWorker() {
		this.datacenterId = getDatacenterId(maxDatacenterId);
		this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
		System.out.println("IdWorker实例 datacenterId="+datacenterId);
		System.out.println("workerId="+workerId);
	}

	/**
	 * 获取 maxWorkerId
	 */
	protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
		StringBuffer mpid = new StringBuffer();
		mpid.append(datacenterId);
		String name = ManagementFactory.getRuntimeMXBean().getName();
		if (!name.isEmpty()) {
			/*
			 * GET jvmPid
			 */
			mpid.append(name.split("@")[0]);
		}
		/*
		 * MAC + PID 的 hashcode 获取16个低位
		 */
		return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
	}

	/**
	 * 数据标识id部分
	 */
	protected static long getDatacenterId(long maxDatacenterId) {
		long id = 0L;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			if (network == null) {
				id = 1L;
			} else {
				byte[] mac = network.getHardwareAddress();
				System.out.println(Arrays.toString(mac));
				id = ((0x000000FF & (long) mac[mac.length - 1])
						| (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
				System.out.println(id);
				id = id % (maxDatacenterId + 1);
			}
		} catch (Exception e) {
			throw new RuntimeException("getDatacenterId: " + e.getMessage(),e);
		}
		return id;
	}

	public static long getId() {
//		IdWorker idWorker = new IdWorker(0, 0);
		//change 20190731 单机调用改支持分布式调用。
		if(idWorker == null) {
			synchronized (IdWorker.class.getName()) {
				if(idWorker == null) {
					idWorker = new IdWorker();
				}
			}
		}
		return idWorker.nextId();
	}
	static{
		//非必须，方便测试
		long id1 = IdWorker.getId();
		System.out.println("init id = "+id1);
	}
//	public static void main(String[] args) {
////		IdWorker idWorker = new IdWorker(1, 1);
//		for (int i = 0; i < 1000; i++) {
//			long id1 = IdWorker.getId();
//			System.out.println(id1);
////			long id = idWorker.nextId();
////			System.out.println(id);
//		}
//	}
}