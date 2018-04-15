package com.rabbitforever.bulkfilerename.views.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.threads.CopyUsbToTargetThread;
import com.rabbitforever.bulkfilerename.views.BaseView;

public class UsbToTargetActionHandler implements ActionListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private BaseView baseView;
	
	public UsbToTargetActionHandler(BaseView baseView){
		this.baseView = baseView;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		try {
			Date startTime = new Date();
			baseView.setStartTime(startTime);
			JButton processButton = baseView.getProcessButton();
			JProgressBar usbToTargetProgressBar = baseView.getUsbToTargetProgressBar();
			usbToTargetProgressBar.setValue(0);
			JProgressBar mergingProgressSourceABar = baseView.getMergingProgressSourceABar();
			mergingProgressSourceABar.setValue(0);
			JProgressBar mergingProgressSourceBBar = baseView.getMergingProgressSourceBBar();
			mergingProgressSourceBBar.setValue(0);
			processButton.setEnabled(false);
			CopyUsbToTargetThread copyUsbToTargetThread = new CopyUsbToTargetThread(baseView);
			Thread thread = new Thread(copyUsbToTargetThread);
			thread.start();
		} catch (Exception ex) {
        	logger.error(className + ".actionPerformed() - ",ex);

		}
//		EditView editView = mainView.getEditView(MainScreenView.MODE_ADD);
//		editView.render();
	}
}
