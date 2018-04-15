package com.rabbitforever.bulkfilerename.views.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.threads.CopySourceFileToUsbThread;
import com.rabbitforever.bulkfilerename.views.BaseView;

public class SourceToUsbActionHandler implements ActionListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private BaseView baseView;
	
	public SourceToUsbActionHandler(BaseView baseView){
		this.baseView = baseView;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		try {
			Date startTime = new Date();
			baseView.setStartTime(startTime);
			JButton processButton = baseView.getProcessButton();
			processButton.setEnabled(false);
			CopySourceFileToUsbThread copySourceFileToUsbThread = new CopySourceFileToUsbThread(baseView);
			Thread thread = new Thread(copySourceFileToUsbThread);
			thread.start();
		} catch (Exception ex) {
        	logger.error(className + ".actionPerformed() - ",ex);

		}
//		EditView editView = mainView.getEditView(MainScreenView.MODE_ADD);
//		editView.render();
	}
}
