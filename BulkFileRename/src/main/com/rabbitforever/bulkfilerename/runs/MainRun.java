package com.rabbitforever.bulkfilerename.runs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.services.FileManipulationMgr;

public class MainRun {
	private final static Logger logger = LoggerFactory.getLogger(MainRun.class.getName());
	//private String className = this.getClass().getName();
	public static void main(String[] args) {
		try{

			FileManipulationMgr fileManipulatorMgr = new FileManipulationMgr();
			fileManipulatorMgr.copySourceFilesToUsb();
			fileManipulatorMgr.copyUsbBackupFilesToTarget();
			fileManipulatorMgr.mergeSourceBToMergingTarget();
			fileManipulatorMgr.mergeSourceAToMergingTarget();
//			List<File> fileList = fileManipulatorMgr.getFileListRecrusively();
//
//			
//			for(File file : fileList){
//				String originalFileName = file.getName();
//				
//				int lastIndexOfDot = originalFileName.lastIndexOf(".");
//				if (lastIndexOfDot > -1){
//					String subOriginalFileName = originalFileName.substring(0, lastIndexOfDot);
//					File parentDir = file.getParentFile();
//					String parentDirString = parentDir.getParent();
//					String extFileName = originalFileName.substring(lastIndexOfDot, originalFileName.length());
//					
//					
//					
//					System.out.println(parentDirString);
//					System.out.println(originalFileName);
//					System.out.println(subOriginalFileName);
//					System.out.println(extFileName);
//				}
//			}
			
		} catch (Exception ex){
			logger.error("MainRun.main()", ex);

		} finally{

		}

	}

}
