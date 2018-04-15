package com.rabbitforever.bulkfilerename.views.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.views.EntranceView;
import com.rabbitforever.bulkfilerename.views.SourceToUsbView;

public class GoToSourceToUsbViewActionHandler implements ActionListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	private EntranceView entranceView;
	
	public GoToSourceToUsbViewActionHandler(EntranceView entranceView){
		this.entranceView = entranceView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SourceToUsbView sourceToUsbView = entranceView.getSourceToUsbView();
		sourceToUsbView.render();
	}

}
