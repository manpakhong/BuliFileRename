package com.rabbitforever.bulkfilerename.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.services.FileManipulationMgr;
import com.rabbitforever.bulkfilerename.views.BaseView;

public class MergeSourceBToMergingTargetThread implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();	
	private FileManipulationMgr fileManipulationMgr;
	private BaseView baseView;
	
	public MergeSourceBToMergingTargetThread(BaseView baseView) throws Exception{
		try {
			this.baseView = baseView;
			fileManipulationMgr = new FileManipulationMgr();
		} catch (Exception e) {
        	logger.error(className + ".MergeSourceBToMergingTargetThread() - ",e);
        	throw e;
		}

	}
	
	@Override
	public void run(){
		try {
			fileManipulationMgr.mergeSourceBToMergingTarget(baseView);
		} catch (Exception e) {
        	logger.error(className + ".run() - ",e);
		}
	}

}
