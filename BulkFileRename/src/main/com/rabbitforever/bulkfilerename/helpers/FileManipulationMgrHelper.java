package com.rabbitforever.bulkfilerename.helpers;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;
import com.rabbitforever.bulkfilerename.factories.PropertiesFactory;

public class FileManipulationMgrHelper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private SysProperties sysProperties;
	
	public FileManipulationMgrHelper() throws Exception {
		init();
	}
	private void init() throws Exception{
		try {
			sysProperties = PropertiesFactory.getInstanceOfSysProperties();
		} catch (Exception e) {
        	logger.error(className + ".init() - ",e);
        	throw e;
		}

	}
    public List<File> sortByNumber(List<File> files) {
    	File[] fileArray = new File[files.size()];
    	
		try {
	    	fileArray= files.toArray(fileArray);
	        Arrays.sort(fileArray, new Comparator<File>() {
	            @Override
	            public int compare(File o1, File o2) {
	            	String absolutePath1 = o1.getAbsolutePath();
	            	String [] splitArray1 = absolutePath1.split("\\\\");
	            	String absolutePath2 = o2.getAbsolutePath();
	            	String [] splitArray2 = absolutePath2.split("\\\\");
	                int n1 =splitArray1.length;
	                int n2 = splitArray2.length;
	                return n1 - n2;
	            }
	        });
		} catch (Exception e) {
        	logger.error(className + ".sortByNumber() - ",e);
        	throw e;
		}
        return Arrays.asList(fileArray);
    }
	public String concatenateTargetSourcePath(String sourceFileRootPath, File sourceFile, String targetRootPath, String targetRemainPath){
		String targetPath = null;
		try {
			targetPath = concatenateTargetSourcePath(sourceFileRootPath, sourceFile, targetRootPath, targetRemainPath, null);

		} catch (Exception e) {
        	logger.error(className + ".concatenateTargetSourcePath() - ",e);
        	throw e;
		}
		return targetPath;
	}
	public boolean isFileFolderPathEquivalentToRootPath(File fileFolder, String rootPath) throws Exception{
		boolean isEqual = false;
		try {
			String absolutePath = fileFolder.getAbsolutePath();
			absolutePath = absolutePath.replace("\\", "/");
			if (absolutePath.equals(rootPath)){
				isEqual = true;
			}
			
		} catch (Exception e) {
        	logger.error(className + ".isFileFolderPathEquivalentToRootPath() - ",e);
        	throw e;
		}
		return isEqual;
	}
	public String concatenateTargetSourcePath(String sourceFileRootPath, File sourceFile, String targetRootPath, String targetRemainPath, String originalRemainPath){
		String targetPath = null;
		try {
			String absolutePath = sourceFile.getAbsolutePath();
			absolutePath = absolutePath.replace("\\", "/");
			String middlePath = absolutePath.replace(sourceFileRootPath, "");
			
			if (originalRemainPath == null){
				middlePath = middlePath.replace(targetRemainPath, "");
				targetPath = targetRootPath + middlePath + targetRemainPath;
			} else {
				middlePath = middlePath.replace(originalRemainPath, "");
				targetPath = targetRootPath + middlePath + targetRemainPath;
			}

		} catch (Exception e) {
        	logger.error(className + ".concatenateTargetSourcePath() - ",e);
        	throw e;
		}
		return targetPath;
	}
	//sit
	public String renameSourceAFileNamingConvention(File file){
		String newName = null;
		try {
			String originalFileName = file.getName();
			
			int lastIndexOfDot = originalFileName.lastIndexOf(".");
			if (lastIndexOfDot > -1){
				String subOriginalFileName = originalFileName.substring(0, lastIndexOfDot);
//				File parentDir = file.getParentFile();
//				String parentDirString = parentDir.getParent();
				String extFileName = originalFileName.substring(lastIndexOfDot, originalFileName.length());
				
				String suffix = sysProperties.getUsbToTargetAppendixSuffix();
				newName = subOriginalFileName + "_" + suffix + extFileName;
//				System.out.println(parentDirString);
//				System.out.println(originalFileName);
//				System.out.println(subOriginalFileName);
//				System.out.println(extFileName);
			}
		} catch (Exception e) {
        	logger.error(className + ".renameSourceAFileNamingConvention() - ",e);
        	throw e;
		}
		return newName;
	}
	// demo
	public String renameSourceBFileNamingConvention(File file){
		String newName = null;
		try {
			String originalFileName = file.getName();
			
			int lastIndexOfDot = originalFileName.lastIndexOf(".");
			if (lastIndexOfDot > -1){
				String subOriginalFileName = originalFileName.substring(0, lastIndexOfDot);
//				File parentDir = file.getParentFile();
//				String parentDirString = parentDir.getParent();
				String extFileName = originalFileName.substring(lastIndexOfDot, originalFileName.length());
				
				String suffix = sysProperties.getSourceToUsbAppendixSuffix();
				newName = subOriginalFileName + "_" + suffix + extFileName;
//				System.out.println(parentDirString);
//				System.out.println(originalFileName);
//				System.out.println(subOriginalFileName);
//				System.out.println(extFileName);
			}
		} catch (Exception e) {
        	logger.error(className + ".renameSourceBFileNamingConvention() - ",e);
        	throw e;
		}
		return newName;
	}

}
