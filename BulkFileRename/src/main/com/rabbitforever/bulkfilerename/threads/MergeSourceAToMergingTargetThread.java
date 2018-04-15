package com.rabbitforever.bulkfilerename.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.services.FileManipulationMgr;
import com.rabbitforever.bulkfilerename.views.BaseView;

public class MergeSourceAToMergingTargetThread implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();	
	private FileManipulationMgr fileManipulationMgr;
	private BaseView baseView;
	
	public MergeSourceAToMergingTargetThread(BaseView baseView) throws Exception{
		try {
			this.baseView = baseView;
			fileManipulationMgr = new FileManipulationMgr();
		} catch (Exception e) {
        	logger.error(className + ".MergeSourceAToMergingTargetThread() - ",e);
        	throw e;
		}

	}
	
	@Override
	public void run(){
		try {
			fileManipulationMgr.mergeSourceAToMergingTarget(baseView);
		} catch (Exception e) {
        	logger.error(className + ".run() - ",e);
		}
	}

}
