package com.rabbitforever.bulkfilerename.views;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;
import com.rabbitforever.bulkfilerename.factories.PropertiesFactory;
import com.rabbitforever.bulkfilerename.threads.MergeSourceAToMergingTargetThread;
import com.rabbitforever.bulkfilerename.threads.MergeSourceBToMergingTargetThread;

public abstract class BaseView {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	
	public final static int LABEL_WIDTH = 300;
	public final static int LABEL_HEIGHT = 20;
	public final static int BUTTON_WIDTH = 100;
	public final static int BUTTON_HEIGHT = 20;
	public final static int TEXT_FIELD_WIDTH = 200;
	public final static int TEXT_FIELD_HEIGHT = 20;
	
	public static final String MODE_ADD = "ADD";
	public static final String MODE_EDIT = "EDIT";
	public static final String MODE_DELETE = "DELETE";
	public static final String MODE_VIEW = "VIEW";
	
	public static final int MAIN_FRAME_WIDTH = 1200;
	public static final int MAIN_FRAME_HEIGHT = 500;
	
	public static final String MAIN_SCREEN_TYPE_CAPTURE = "CAPTURE";
	public static final String MAIN_SCREEN_TYPE_RESTORE = "RESTORE";
	
	public static final int MAINTABLE_WIDTH = 1000;
	public static final int MAINTABLE_HEIGHT = 250;

	public static final String PROGRESS_STATUS_STAND_BY = "STANDBY";
	public static final String PROGRESS_STATUS_PROCESSING = "PROCESSING";
	
	private final static int CHECK_BOX_COLUMN_INDEX = 0;
	
	protected SysProperties sysProperties;

	protected JFrame mainFrame = null;
	protected JPanel mainPanel = null;
	

	protected JProgressBar sourceToUsbProgressBar = null;
	protected JProgressBar usbToTargetProgressBar = null;
	protected JProgressBar mergingProgressSourceABar = null;
	protected JProgressBar mergingProgressSourceBBar = null;
	protected JLabel sourceToUsbLabel = null;
	protected JLabel usbToTargetLabel = null;
	protected JLabel mergingLabel = null;
	protected JButton processButton = null;
	

	protected Date startTime;
	protected Date endTime;
	public BaseView(){
		try{
			initParent();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private void initParent()throws Exception{
		try {
			sysProperties = PropertiesFactory.getInstanceOfSysProperties();
		} catch (Exception e) {
        	logger.error(className + ".init() - ",e);
        	throw e;
		}
	}
	public void notifyCopySourceToUsb(List<File> exceptionFileList) throws Exception{
		try {
			String timeString = null;
			endTime = new Date();
			if (startTime != null && endTime != null){
				timeString = "Start: " + startTime.toString() + ", End: " + endTime.toString();
			}
			
			String message = "The Process of Backup up Source to USB is completed.";
			if (timeString != null){
				message += ", " + timeString;
			}
			
			JOptionPane.showMessageDialog(this.getMainFrame(), message);

			processButton.setEnabled(true);
		} catch (Exception e) {
        	logger.error(className + ".notifyFinishedCopyUsbToTarget() - ",e);
        	throw e;
		}
	}
	public void notifyFinishedCopyUsbToTarget(List<File> exceptionFileList) throws Exception{
		try {
			MergeSourceAToMergingTargetThread mergeSourceAToMergingTargetThread = new MergeSourceAToMergingTargetThread(this);
			Thread thread = new Thread(mergeSourceAToMergingTargetThread);
			thread.start();
		} catch (Exception e) {
        	logger.error(className + ".notifyFinishedCopyUsbToTarget() - ",e);
        	throw e;
		}
	}
	public void notifyMergeSourceAToMergingTarget(List<File> exceptionFileList) throws Exception{
		try {
			MergeSourceBToMergingTargetThread mergeSourceBToMergingTargetThread = new MergeSourceBToMergingTargetThread(this);
			Thread thread = new Thread(mergeSourceBToMergingTargetThread);
			thread.start();
		} catch (Exception e) {
        	logger.error(className + ".notifyMergeSourceAToMergingTarget() - ",e);
        	throw e;
		}
	}
	public void notifyMergeSourceBToMergingTarget(List<File> exceptionFileList){
		try {
			String timeString = null;
			endTime = new Date();
			if (startTime != null && endTime != null){
				timeString = "Start: " + startTime.toString() + ", End: " + endTime.toString();
			}
			
			String message = "Processes of Backup and Merging from USB to Target are completed.";
			if (timeString != null){
				message += ", " + timeString;
			}
			JOptionPane.showMessageDialog(this.getMainFrame(), message);
			processButton.setEnabled(true);
		} catch (Exception e) {
        	logger.error(className + ".notifyMergeSourceBToMergingTarget() - ",e);
        	throw e;
		}
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public JButton getProcessButton() {
		return processButton;
	}
	public void setProcessButton(JButton processButton) {
		this.processButton = processButton;
	}
	public JProgressBar getSourceToUsbProgressBar() {
		return sourceToUsbProgressBar;
	}
	public void setSourceToUsbProgressBar(JProgressBar sourceToUsbProgressBar) {
		this.sourceToUsbProgressBar = sourceToUsbProgressBar;
	}
	public JProgressBar getUsbToTargetProgressBar() {
		return usbToTargetProgressBar;
	}
	public void setUsbToTargetProgressBar(JProgressBar usbToTargetProgressBar) {
		this.usbToTargetProgressBar = usbToTargetProgressBar;
	}
	public JProgressBar getMergingProgressSourceABar() {
		return mergingProgressSourceABar;
	}
	public void setMergingProgressSourceABar(JProgressBar mergingProgressSourceABar) {
		this.mergingProgressSourceABar = mergingProgressSourceABar;
	}
	public JProgressBar getMergingProgressSourceBBar() {
		return mergingProgressSourceBBar;
	}
	public void setMergingProgressSourceBBar(JProgressBar mergingProgressSourceBBar) {
		this.mergingProgressSourceBBar = mergingProgressSourceBBar;
	}
	public JLabel getSourceToUsbLabel() {
		return sourceToUsbLabel;
	}
	public void setSourceToUsbLabel(JLabel sourceToUsbLabel) {
		this.sourceToUsbLabel = sourceToUsbLabel;
	}
	public JLabel getUsbToTargetLabel() {
		return usbToTargetLabel;
	}
	public void setUsbToTargetLabel(JLabel usbToTargetLabel) {
		this.usbToTargetLabel = usbToTargetLabel;
	}
	public JLabel getMergingLabel() {
		return mergingLabel;
	}
	public void setMergingLabel(JLabel mergingLabel) {
		this.mergingLabel = mergingLabel;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	protected abstract void initControls();
	protected abstract void addEventHandlers();
}
