package com.rabbitforever.bulkfilerename.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.helpers.UiHelper;
import com.rabbitforever.bulkfilerename.threads.CopySourceFileToUsbThread;
import com.rabbitforever.bulkfilerename.threads.MergeSourceAToMergingTargetThread;
import com.rabbitforever.bulkfilerename.threads.MergeSourceBToMergingTargetThread;
import com.rabbitforever.bulkfilerename.views.handlers.SourceToUsbActionHandler;
import com.rabbitforever.bulkfilerename.views.handlers.UsbToTargetActionHandler;

public class UsbToTargetView extends BaseView{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	
	
	public UsbToTargetView(){
		try {
			init();
			initControls();
		} catch (Exception e) {
        	logger.error(className + ".UsbToTargetView() - ",e);
        	throw e;
		}

	}
	
	private void init(){
		try {

		} catch (Exception e) {
        	logger.error(className + ".init() - ",e);
        	throw e;
		}
	}
	@Override
	protected void initControls() {
		try {
			mainFrame = new JFrame("Usb To Target Screen");

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

			JPanel upperPanel = new JPanel();
			JPanel middlePanel = new JPanel();
			JPanel bottomPanel = new JPanel();


			usbToTargetLabel = new JLabel();
			usbToTargetLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
			usbToTargetLabel.setText("Backup, Merging...");
			upperPanel.add(usbToTargetLabel);

			mergingLabel = new JLabel();
			mergingLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
			mergingLabel.setText("");
			upperPanel.add(mergingLabel);
			
			usbToTargetProgressBar = new JProgressBar();
			usbToTargetProgressBar.setStringPainted(true);
			middlePanel.add(usbToTargetProgressBar);
			

			
			mergingProgressSourceABar = new JProgressBar();
			mergingProgressSourceABar.setStringPainted(true);
			middlePanel.add(mergingProgressSourceABar);
			
			mergingProgressSourceBBar = new JProgressBar();
			mergingProgressSourceBBar.setStringPainted(true);
			middlePanel.add(mergingProgressSourceBBar);
			
			processButton = new JButton();
			processButton.setText("Process");
			
			bottomPanel.add(processButton);



			mainFrame.add(mainPanel);
			mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			UiHelper.setColor(mainPanel);
			UiHelper.setColor(upperPanel);
			UiHelper.setColor(middlePanel);
			UiHelper.setColor(bottomPanel);

			mainPanel.add(upperPanel);
			mainPanel.add(middlePanel);
			mainPanel.add(bottomPanel);
			addEventHandlers();
		} catch (Exception e) {
        	logger.error(className + ".initControls() - ",e);
        	throw e;
		}
	}
	public void render() {
		mainFrame.setVisible(true);
	}
	@Override
	protected void addEventHandlers() {
		try {
			UsbToTargetActionHandler usbToTargetActionHandler = new UsbToTargetActionHandler(this);
			processButton.addActionListener(usbToTargetActionHandler);
		} catch (Exception e) {
        	logger.error(className + ".addEventHandlers() - ",e);
        	throw e;
		}
	}


}
