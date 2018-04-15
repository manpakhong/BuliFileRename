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
import com.rabbitforever.bulkfilerename.views.handlers.SourceToUsbActionHandler;

public class SourceToUsbView extends BaseView{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String className = this.getClass().getName();
	
	public SourceToUsbView(){
		try {
			init();
			initControls();
		} catch (Exception e) {
        	logger.error(className + ".SourceToUsbView() - ",e);
        	throw e;
		}
	}

	private void init() {
		try {

		} catch (Exception e) {
			logger.error(className + ".init() - ", e);
			throw e;
		}
	}
	@Override
	protected void initControls() {
		try {
			mainFrame = new JFrame("Source To Usb Screen");

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

			JPanel upperPanel = new JPanel();
			JPanel middlePanel = new JPanel();
			JPanel bottomPanel = new JPanel();


			sourceToUsbLabel = new JLabel();
			sourceToUsbLabel.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
			sourceToUsbLabel.setText("Source to USB");
			middlePanel.add(sourceToUsbLabel);

			sourceToUsbProgressBar = new JProgressBar();
			sourceToUsbProgressBar.setStringPainted(true);
			middlePanel.add(sourceToUsbProgressBar);
			
			processButton = new JButton();
			sourceToUsbLabel.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
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
			SourceToUsbActionHandler sourceToUsbActionHandler = new SourceToUsbActionHandler(this);
			processButton.addActionListener(sourceToUsbActionHandler);
		} catch (Exception e) {
        	logger.error(className + ".addEventHandlers() - ",e);
        	throw e;
		}
	}

}
