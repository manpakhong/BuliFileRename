package com.rabbitforever.bulkfilerename.views.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.views.EntranceView;
import com.rabbitforever.bulkfilerename.views.UsbToTargetView;

public class GoToUsbToTargetViewActionHandler implements ActionListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private EntranceView entranceView;
	
	public GoToUsbToTargetViewActionHandler(EntranceView entranceView){
		this.entranceView = entranceView;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		UsbToTargetView usbToTargetView = entranceView.getUsbToTargetView();
		usbToTargetView.render();
	}

}
