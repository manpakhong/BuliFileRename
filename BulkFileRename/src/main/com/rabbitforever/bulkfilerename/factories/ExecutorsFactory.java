package com.rabbitforever.bulkfilerename.factories;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;
import com.rabbitforever.bulkfilerename.dtos.FlowTestJobDto;

public class ExecutorsFactory {
	private final static Logger logger = LoggerFactory.getLogger(ExecutorsFactory.class);
	private final static String className = ExecutorsFactory.class.getName();
	private static SysProperties sysProperties;
	private static int QUEUE_SIZE = 0;
//	protected static FlowTestJobsExecutor<FlowTestJobDto> flowTestJobsConsumerJobsExecutor;
	protected static BlockingQueue<FlowTestJobDto> flowTestJobQueue;
//	protected static FlowTestJobsExecutor<FlowTestJobDto> flowTestJobsProviderJobsExecutor;

	private ExecutorsFactory() {
	}

	private static SysProperties getInstanceOfSysProperties() throws Exception{
		try {
			if (sysProperties == null) {
				sysProperties = PropertiesFactory.getInstanceOfSysProperties();
			}
		} catch (Exception e) {
			logger.error(className + ".getInstanceOfSysProperties()", e);
			throw e;
		}
		return sysProperties;
	}

//	public static FlowTestJobsExecutor<FlowTestJobDto> getFlowTestJobsConsumerExecutorInstance() {
//		try {
//			if (flowTestJobsConsumerJobsExecutor == null) {
//				flowTestJobsConsumerJobsExecutor = new FlowTestJobsConsumerExecutorImpl(getFlowTestJobQueueInstance());
//			}
//		} catch (Exception e) {
//			logger.error(className + ".getFlowTestJobsConsumerExecutorInstance()", e);
//		}
//		return flowTestJobsConsumerJobsExecutor;
//	}
//
//	public static FlowTestJobsExecutor<FlowTestJobDto> getFlowTestJobsProviderExecutorInstance() {
//		try {
//			if (flowTestJobsProviderJobsExecutor == null) {
//				flowTestJobsProviderJobsExecutor = new FlowTestJobsProviderExecutorImpl(getFlowTestJobQueueInstance());
//			}
//		} catch (Exception e) {
//			logger.error(className + ".getFlowTestJobsProviderExecutorInstance()", e);
//		}
//		return flowTestJobsProviderJobsExecutor;
//	}

	protected static BlockingQueue<FlowTestJobDto> getFlowTestJobQueueInstance() {
		try {
			getInstanceOfSysProperties();
			Integer threadQueueSize = sysProperties.getThreadQueueSize();
			QUEUE_SIZE = threadQueueSize;
			if (flowTestJobQueue == null) {
				flowTestJobQueue = new ArrayBlockingQueue<FlowTestJobDto>(QUEUE_SIZE);
			}
		} catch (Exception e) {
			logger.error(className + ".getFlowTestJobQueueInstance()", e);
		}
		return flowTestJobQueue;
	}
}
