package com.vpiaotong.elephant.core.utils.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/**
 * 	线程池工具
 * @author KJX 016/08/31
 *
 */
public class ThreadPoolUtil {

	private static ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(5);
	
	/**
	 * 	获取调度池
	 * @return
	 */
	public static ScheduledExecutorService getScheduledThreadPool() {
		
		return scheduledThreadPool;
	}
}
