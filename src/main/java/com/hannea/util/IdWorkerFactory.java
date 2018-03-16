package com.hannea.util;

public class IdWorkerFactory {

	volatile private static IdWorker idWorker = null;
	public static IdWorker getIdWorker() {
		try {
			if(idWorker == null) {
				//创建实例之前可能会有一些准备性的耗时工作
				Thread.sleep(300);
				synchronized (IdWorkerFactory.class) {
					//二次检查
					if (idWorker == null) {
						idWorker = new IdWorker(1, 1);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return idWorker;

	}
}
