package com.rabbitforever.bulkfilerename.bundles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysProperties extends PropertiesBase{
	private final static String FILE_NAME = "system.properties";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private Integer consumerThreadPool;
	private Integer consumerSleepTime;
	private Integer producerThreadPool;
	private Integer producerSleepTime;
	private Integer threadQueueSize;
	
	
	
	
	public SysProperties() throws Exception {
		super(FILE_NAME);
	}
	public Integer getConsumerThreadPool() {
		try {
			String consumerThreadPoolString = this.getPropValues("consumer_thread_pool");
			consumerThreadPool = Integer.parseInt(consumerThreadPoolString);
		} catch (Exception e) {
			logger.error(className + ".getConsumerThreadPool()", e);
		}
		return consumerThreadPool;
	}

	public Integer getConsumerSleepTime() {
		try {
			String consumerSleepTimeString = this.getPropValues("consumer_sleep_time");
			consumerSleepTime = Integer.parseInt(consumerSleepTimeString);
		} catch (Exception e) {
			logger.error(className + ".getConsumerSleepTime()", e);
		}
		return consumerSleepTime;
	}

	public Integer getProducerThreadPool() {
		String producerThreadPoolString = this.getPropValues("producer_thread_pool");
		producerThreadPool = Integer.parseInt(producerThreadPoolString);
		return producerThreadPool;
	}

	public Integer getProducerSleepTime() {
		try {
			String producerSleepTimeString = this.getPropValues("producer_sleep_time");
			producerSleepTime = Integer.parseInt(producerSleepTimeString);
		} catch (Exception e) {
			logger.error(className + ".getProducerSleepTime()", e);
		}
		return producerSleepTime;
	}

	public Integer getThreadQueueSize() {
		try {
			String threadQueueSizeString = this.getPropValues("thread_queue_size");
			threadQueueSize = Integer.parseInt(threadQueueSizeString);
		} catch (Exception e) {
			logger.error(className + ".getThreadQueueSize()", e);
		}
		return threadQueueSize;
	}
	public String getSourceToUsbAppendixSuffix(){
		String property = null;
		try {
			String propertyString = this.getPropValues("source_to_usb_appendix_suffix");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getSourceToUsbAppendixSuffix()", ex);
			throw ex;
		}
		return property;
	}		
	public String getUsbToTargetAppendixSuffix() {
		String property = null;
		try {
			String propertyString = this.getPropValues("usb_to_target_appendix_suffix");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getUsbToTargetAppendixSuffix()", ex);
			throw ex;
		}
		return property;
	}
	public String getFolderRootFromSourceToUsb(){
		String property = null;
		try {
			String propertyString = this.getPropValues("folder_root_from_source_to_usb");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getFolderRootFromSourceToUsb()", ex);
			throw ex;
		}
		return property;
	}
	public String getFolderRootFromUsbToTarget(){
		String property = null;
		try {
			String propertyString = this.getPropValues("folder_root_from_usb_to_target");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getFolderRootFromUsbToTarget()", ex);
			throw ex;
		}
		return property;
	}
	public String getUsbBackupFolderRoot() {
		String property = null;
		try {
			String propertyString = this.getPropValues("usb_backup_folder_root");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getUsbBackupFolderRoot()", ex);
			throw ex;
		}
		return property;
	}
	public String getFolderRootMergingSourceA() {
		String property = null;
		try {
			String propertyString = this.getPropValues("folder_root_merging_source_a");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getFolderRootMergingSourceA()", ex);
			throw ex;
		}
		return property;
	}
	public String getFolderRootMergingSourceB() {
		String property = null;
		try {
			String propertyString = this.getPropValues("folder_root_merging_source_b");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getFolderRootMergingSourceB()", ex);
			throw ex;
		}
		return property;
	}
	public String getFolderRootMergingTarget() {
		String property = null;
		try {
			String propertyString = this.getPropValues("folder_root_merging_target");
			property = propertyString;
		} catch (Exception ex) {
			logger.error(className + ".getFolderRootMergingTarget()", ex);
			throw ex;
		}
		return property;
	}

}
