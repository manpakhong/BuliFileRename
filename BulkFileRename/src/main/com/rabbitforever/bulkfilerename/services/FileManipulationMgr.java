package com.rabbitforever.bulkfilerename.services;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;
import com.rabbitforever.bulkfilerename.factories.PropertiesFactory;
import com.rabbitforever.bulkfilerename.helpers.FileManipulationMgrHelper;
import com.rabbitforever.bulkfilerename.utils.CommonUtils;
import com.rabbitforever.bulkfilerename.utils.FileUtils;
import com.rabbitforever.bulkfilerename.views.BaseView;

public class FileManipulationMgr {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();		
	private FileUtils fileUtils;
	private CommonUtils commonUtils;
	private SysProperties sysProperties;
	private FileManipulationMgrHelper fileManipulationMgrHelper;
	public FileManipulationMgr() throws Exception{
		init();
	}
	
	private void init() throws Exception {
		try {
			fileManipulationMgrHelper = new FileManipulationMgrHelper();
			fileUtils = new FileUtils();
			commonUtils = new CommonUtils();
			sysProperties = PropertiesFactory.getInstanceOfSysProperties();
		} catch (Exception ex) {
        	logger.error(className + ".init() - ",ex);
        	throw ex;
		}		

	}
	public List<File> mergeSourceAToMergingTarget() throws Exception {
		List<File> exceptionFileList = null;
		try {
			exceptionFileList = mergeSourceAToMergingTarget(null);
		} catch (Exception e) {
        	logger.error(className + ".mergeSourceAToMergingTarget() - ",e);
        	throw e;
		}
		return exceptionFileList;
	}
	public List<File> mergeSourceAToMergingTarget(BaseView baseView) throws Exception {
		File targetFile = null;
		List<File> exceptionFileList = null;
		JProgressBar progressBar = null;
		try {

			String sourceAFolderRoot = sysProperties.getFolderRootMergingSourceA();
			String mergingTargetRoot = sysProperties.getFolderRootMergingTarget();


			fileUtils.createDirectoryIfNotExisted(mergingTargetRoot);
			
			List<File> sourceFileList = getFileListRecrusively(sourceAFolderRoot);
			sourceFileList = fileManipulationMgrHelper.sortByNumber(sourceFileList);
			int totalCount = sourceFileList.size();
			JLabel mergingLabel = baseView.getMergingLabel();
			if (mergingLabel != null){
				mergingLabel.setText("Merging Source A...");
			}
			progressBar = baseView.getMergingProgressSourceABar();
			
			if (progressBar != null){
				progressBar.setMinimum(0);
				progressBar.setMaximum(totalCount);
			}
			int progressBarCount = 0;
			exceptionFileList = new ArrayList<File>();
			for (int i = 0; i < sourceFileList.size(); i++){
				File sourceFile = sourceFileList.get(i);
				Path sourcePath = sourceFile.toPath();
				
				File sourceFileFolder = sourceFile.getParentFile();
				
				String newFileName = fileManipulationMgrHelper.renameSourceAFileNamingConvention(sourceFile);
				
				
				
				String sourceFileRootPath = sourceAFolderRoot;
				String targetRootPath = mergingTargetRoot;
				
				
				boolean isEqual = fileManipulationMgrHelper.isFileFolderPathEquivalentToRootPath(sourceFileFolder, sourceFileRootPath);
				String originalRemainPath  = "";
				String targetRemainPath = "";
				if (!isEqual){
					originalRemainPath += sourceFileFolder.getName() + "/";
					targetRemainPath = sourceFileFolder.getName() + "/";
				}
				
				originalRemainPath += sourceFile.getName();
				targetRemainPath += newFileName;
				
				
//				if (sourceFile.getAbsolutePath().contains("00. New Case-LCK-C-050045_ASSB002_ASS_20160705185408000028_P02")){
//					int a = 3;
//					int b = a;
//				}
				String mergingTargetFile = fileManipulationMgrHelper.concatenateTargetSourcePath(sourceFileRootPath, sourceFile, targetRootPath, targetRemainPath, originalRemainPath);
				
				
//				mergingTargetFile = mergingTargetRoot + "/" + sourceFileFolder.getName() + "/" + newFileName;
				targetFile = new File(mergingTargetFile);
				File targetFileFolder = targetFile.getParentFile();
				String targetFileFolderString = targetFileFolder.getAbsolutePath();
				
				File targetFileParentFolder = targetFileFolder.getParentFile();
				String targetFileParentFolderString = targetFileParentFolder.getAbsolutePath();
				fileUtils.createDirectoryIfNotExisted(targetFileParentFolderString);
				fileUtils.createDirectoryIfNotExisted(targetFileFolderString);
				Path targetPath = targetFile.toPath();
				fileUtils.copyFile(sourcePath, targetPath,exceptionFileList);
				progressBarCount++;
				if (progressBar != null){
					progressBar.setValue(progressBarCount);
				}
			}
			if (baseView != null){
				baseView.notifyMergeSourceAToMergingTarget(exceptionFileList);
			}
		} catch (Exception e) {
        	logger.error(className + ".mergeSourceAToMergingTarget() - ", e);
        	throw e;
		} finally{
			if (targetFile != null){
				targetFile = null;
			}
		}

		return exceptionFileList;
	}
	public List<File> mergeSourceBToMergingTarget() throws Exception {
		List<File> exceptionFileList = null;
		try {
			exceptionFileList = mergeSourceBToMergingTarget(null);
		} catch (Exception e) {
        	logger.error(className + ".mergeSourceBToMergingTarget() - ",e);
        	throw e;
		}
		return exceptionFileList;
	}
	public List<File> mergeSourceBToMergingTarget(BaseView baseView) throws Exception {
		File targetFile = null;
		List<File> exceptionFileList = null;
		JProgressBar progressBar = null;
		try {

			String sourceBFolderRoot = sysProperties.getFolderRootMergingSourceB();
			String mergingTargetRoot = sysProperties.getFolderRootMergingTarget();


			fileUtils.createDirectoryIfNotExisted(mergingTargetRoot);
			
			List<File> sourceFileList = getFileListRecrusively(sourceBFolderRoot);
			sourceFileList = fileManipulationMgrHelper.sortByNumber(sourceFileList);
			int totalCount = sourceFileList.size();
			JLabel mergingLabel = baseView.getMergingLabel();
			if (mergingLabel != null){
				mergingLabel.setText("Merging Source B...");
			}
			progressBar = baseView.getMergingProgressSourceBBar();
			
			if (progressBar != null){
				progressBar.setMinimum(0);
				progressBar.setMaximum(totalCount);
			}
			int progressBarCount = 0;
			exceptionFileList = new ArrayList<File>();
			for (int i = 0; i < sourceFileList.size(); i++){
				File sourceFile = sourceFileList.get(i);
				Path sourcePath = sourceFile.toPath();
				
				File sourceFileFolder = sourceFile.getParentFile();
				
				String newFileName = fileManipulationMgrHelper.renameSourceBFileNamingConvention(sourceFile);

				
				
				
				String sourceFileRootPath = sourceBFolderRoot;
				String targetRootPath = mergingTargetRoot;

				boolean isEqual = fileManipulationMgrHelper.isFileFolderPathEquivalentToRootPath(sourceFileFolder, sourceFileRootPath);
				String originalRemainPath  = "";
				String targetRemainPath = "";
				if (!isEqual){
					originalRemainPath += sourceFileFolder.getName() + "/";
					targetRemainPath = sourceFileFolder.getName() + "/";
				}
				
				originalRemainPath += sourceFile.getName();
				targetRemainPath += newFileName;
				String mergingTargetFile = fileManipulationMgrHelper.concatenateTargetSourcePath(sourceFileRootPath, sourceFile, targetRootPath, targetRemainPath, originalRemainPath);
				
//				mergingTargetFile = mergingTargetRoot + "/" + sourceFileFolder.getName() + "/" + newFileName;
				
				targetFile = new File(mergingTargetFile);
				File targetFileFolder = targetFile.getParentFile();
				String targetFileFolderString = targetFileFolder.getAbsolutePath();
			
				fileUtils.createDirectoryIfNotExisted(targetFileFolderString);
				Path targetPath = targetFile.toPath();
				fileUtils.copyFile(sourcePath, targetPath,exceptionFileList);
				progressBarCount++;
				if (progressBar != null){
					progressBar.setValue(progressBarCount);
				}
			}
			if (baseView != null){
				baseView.notifyMergeSourceBToMergingTarget(exceptionFileList);
			}
		} catch (Exception e) {
        	logger.error(className + ".mergeSourceBToMergingTarget() - ", e);
        	throw e;
		} finally{
			if (targetFile != null){
				targetFile = null;
			}
		}
		return exceptionFileList;
	}
	public List<File> copyUsbBackupFilesToTarget() throws Exception {
		List<File> exceptionFileList = null;
		try {
			exceptionFileList = copyUsbBackupFilesToTarget(null);
		} catch (Exception e) {
        	logger.error(className + ".cloneList() - ",e);
        	throw e;
		}
		return exceptionFileList;
	}
	public List<File> copyUsbBackupFilesToTarget(BaseView baseView) throws Exception {
		File targetFile = null;
		List<File> exceptionFileList = null;
		JProgressBar progressBar = null;
		try {

			String usbBackupFolderRoot = sysProperties.getUsbBackupFolderRoot();
			String folderRootFromUsbToTarget = sysProperties.getFolderRootFromUsbToTarget();
			// for windows box
			String driveLetter = fileUtils.getCurrentDriveLetter();
			usbBackupFolderRoot = driveLetter + usbBackupFolderRoot;
			fileUtils.createDirectoryIfNotExisted(folderRootFromUsbToTarget);
			
			List<File> sourceFileList = getFileListRecrusively(usbBackupFolderRoot);
			sourceFileList = fileManipulationMgrHelper.sortByNumber(sourceFileList);
			int totalCount = sourceFileList.size();
			
			progressBar = baseView.getUsbToTargetProgressBar();
			
			if (progressBar != null){
				progressBar.setMinimum(0);
				progressBar.setMaximum(totalCount);
			}
			int progressBarCount = 0;
			
			
			JLabel mergingLabel = baseView.getMergingLabel();
			if (mergingLabel != null){
				mergingLabel.setText("USB to Target...");
			}
			exceptionFileList = new ArrayList<File>();

			for (int i = 0; i < sourceFileList.size(); i++){
				File sourceFile = sourceFileList.get(i);
				Path sourcePath = sourceFile.toPath();
				
				File sourceFileFolder = sourceFile.getParentFile();
				
				String sourceFileRootPath = usbBackupFolderRoot;
				String targetRootPath = folderRootFromUsbToTarget;
				String targetRemainPath = sourceFileFolder.getName() + "/" + sourcePath.getFileName();
				String usbToTargetFile = fileManipulationMgrHelper.concatenateTargetSourcePath(sourceFileRootPath, sourceFile, targetRootPath, targetRemainPath);
				
//				usbToTargetFile = folderRootFromUsbToTarget + "/" + sourceFileFolder.getName() + "/" + sourcePath.getFileName();
				targetFile = new File(usbToTargetFile);
				File targetFileFolder = targetFile.getParentFile();
				String targetFileFolderString = targetFileFolder.getAbsolutePath();
			
				fileUtils.createDirectoryIfNotExisted(targetFileFolderString);
				Path targetPath = targetFile.toPath();
				fileUtils.copyFile(sourcePath, targetPath,exceptionFileList);
				progressBarCount++;
				if (progressBar != null){
					progressBar.setValue(progressBarCount);
				}

			}
			if (baseView != null){
				baseView.notifyFinishedCopyUsbToTarget(exceptionFileList);
			}
		} catch (Exception e) {
        	logger.error(className + ".copyUsbBackupFilesToTarget() - ", e);
        	throw e;
		} finally{
			if (targetFile != null){
				targetFile = null;
			}
		}
		return exceptionFileList;
	}
	public List<File> copySourceFilesToUsb() throws Exception{
		List<File> exceptionFileList = null;
		try {
			exceptionFileList = copySourceFilesToUsb(null);
		} catch (Exception e) {
        	logger.error(className + ".cloneList() - ",e);
        	throw e;
		}
		return exceptionFileList;
	}
	public List<File> copySourceFilesToUsb(BaseView baseView) throws Exception{
		File targetFile = null;
		List<File> exceptionFileList = null;
		JProgressBar progressBar = null;
		try {
			String folderRootFromSourceToUsb = sysProperties.getFolderRootFromSourceToUsb();
			String usbBackupFolderRoot = sysProperties.getUsbBackupFolderRoot();
			// for windows box
			String driveLetter = fileUtils.getCurrentDriveLetter();
			usbBackupFolderRoot = driveLetter + usbBackupFolderRoot;
			fileUtils.createDirectoryIfNotExisted(usbBackupFolderRoot);
			List<File> sourceFileList = getFileListRecrusively(folderRootFromSourceToUsb);
			sourceFileList = fileManipulationMgrHelper.sortByNumber(sourceFileList);
			int totalCount = sourceFileList.size();
			
			progressBar = baseView.getSourceToUsbProgressBar();
			
			if (progressBar != null){
				progressBar.setMinimum(0);
				progressBar.setMaximum(totalCount);
			}
			int progressBarCount = 0;
			exceptionFileList = new ArrayList<File>();
			for (int i = 0; i < sourceFileList.size(); i++){
				File sourceFile = sourceFileList.get(i);
				Path sourcePath = sourceFile.toPath();
				File usbBackupFolder = sourceFile.getParentFile();
				
				String sourceFileRootPath = folderRootFromSourceToUsb;
				String targetRootPath = usbBackupFolderRoot;
				String targetRemainPath = usbBackupFolder.getName() + "/" + sourcePath.getFileName();
				String usbBackupFile = fileManipulationMgrHelper.concatenateTargetSourcePath(sourceFileRootPath, sourceFile, targetRootPath, targetRemainPath);
				//usbBackupFile = targetRootPath + "/" + usbBackupFolder.getName() + "/" + sourcePath.getFileName();
				
				targetFile = new File(usbBackupFile);
				File targetFileFolder = targetFile.getParentFile();
				String targetFileFolderString = targetFileFolder.getAbsolutePath();
			
				fileUtils.createDirectoryIfNotExisted(targetFileFolderString);
				Path targetPath = targetFile.toPath();
				
				if (sourcePath.toFile().getAbsolutePath().contains("06-4060-0017_merged - Assessment-Step10.doc")){
					int a = 3;
					int b = a;
				}
				fileUtils.copyFile(sourcePath, targetPath,exceptionFileList);
				progressBarCount++;
				if (progressBar != null){
					progressBar.setValue(progressBarCount);
				}
			}
			if (baseView != null){
				baseView.notifyCopySourceToUsb(exceptionFileList);
			}
		} catch (Exception e) {
        	logger.error(className + ".copySourceFilesToUsb() - ", e);
        	throw e;
		} finally{
			if (targetFile != null){
				targetFile = null;
			}
		}
		return exceptionFileList;
	}

	public List<File> getFileListRecrusively(String folderPath){
		List<File> fileList = null;
		try {
			String folderRootFromSourceToUsb = folderPath;
			fileList = new ArrayList<File>();
			fileUtils.traverseDir(folderRootFromSourceToUsb, fileList);
		} catch (Exception ex) {
        	logger.error(className + ".getFileListRecrusively() - folderPath=" + folderPath, ex);
        	throw ex;
		}
		return fileList;
	}
	public List<File> getFileListRecrusively(){
		List<File> fileList = null;
		String folderRootFromSourceToUsb = null;
		try {
			folderRootFromSourceToUsb = sysProperties.getFolderRootFromSourceToUsb();
			fileList = new ArrayList<File>();
			fileUtils.traverseDir(folderRootFromSourceToUsb, fileList);
		} catch (Exception ex) {
        	logger.error(className + ".getFileListRecrusively() - folderRootFromSourceToUsb=" + folderRootFromSourceToUsb, ex);
        	throw ex;
		}
		return fileList;
	}
}
