package com.rabbitforever.bulkfilerename.views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.bulkfilerename.bundles.SysProperties;
import com.rabbitforever.bulkfilerename.factories.PropertiesFactory;
import com.rabbitforever.bulkfilerename.helpers.UiHelper;
import com.rabbitforever.bulkfilerename.views.handlers.GoToSourceToUsbViewActionHandler;
import com.rabbitforever.bulkfilerename.views.handlers.GoToUsbToTargetViewActionHandler;

public class EntranceView {
	private static final Logger logger = LoggerFactory.getLogger(EntranceView.class);
	private static String className = EntranceView.class.getName();
	protected JFrame entranceFrame = null;
	protected JPanel entrancePanel = null;
	protected JPanel northEntrancePanel = null;
	protected JPanel southEntrancePanel = null;
	protected JButton gotoSourceToUsbViewButton = null;
	protected JButton gotoUsbToTargetViewButton = null;
	protected SourceToUsbView sourceToUsbView;
	protected UsbToTargetView usbToTargetView;


	public static final int FILE_SELECTION_FRAME_WIDTH = 1000;
	public static final int FILE_SELECTION_FRAME_HEIGHT = 500;
	private SysProperties sysProperties = null;
	public SourceToUsbView getSourceToUsbView() {
		if (sourceToUsbView == null){
			sourceToUsbView = new SourceToUsbView();
		}
		return sourceToUsbView;
	}

	public void setSourceToUsbView(SourceToUsbView sourceToUsbView) {
		this.sourceToUsbView = sourceToUsbView;
	}

	public UsbToTargetView getUsbToTargetView() {
		if (usbToTargetView == null){
			usbToTargetView = new UsbToTargetView();
		}
		return usbToTargetView;
	}

	public void setUsbToTargetView(UsbToTargetView usbToTargetView) {
		this.usbToTargetView = usbToTargetView;
	}







	public EntranceView() {
		initParams();
		init();
	}

	private void initParams() {
		try {
			sysProperties = PropertiesFactory.getInstanceOfSysProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render() {
		entranceFrame.setVisible(true);
	}

	private void init() {
		entranceFrame = new JFrame("Entrance Screen");

		entrancePanel = new JPanel();

		entrancePanel.setLayout(new GridLayout());
		//
		// northEntrancePanel = new JPanel();
		// southEntrancePanel = new JPanel();

		gotoSourceToUsbViewButton = new JButton();
		gotoSourceToUsbViewButton.setText("Source to USB");
		gotoSourceToUsbViewButton.setBackground(Color.pink);
		entrancePanel.add(gotoSourceToUsbViewButton);

		gotoUsbToTargetViewButton = new JButton();
		gotoUsbToTargetViewButton.setText("USB to Target");
		gotoUsbToTargetViewButton.setBackground(Color.pink);
		entrancePanel.add(gotoUsbToTargetViewButton);



		UiHelper.setColor(entrancePanel);
		
		addEventHandlers();

		entranceFrame.add(entrancePanel);
		entranceFrame.setSize(FILE_SELECTION_FRAME_WIDTH, FILE_SELECTION_FRAME_HEIGHT);
		entranceFrame.setLocationRelativeTo(null);
		entranceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		entranceFrame.setBackground(Color.pink);
		// entrancePanel.add(northEntrancePanel, BorderLayout.NORTH);
		// entrancePanel.add(southEntrancePanel, BorderLayout.CENTER);

	}

	private void addEventHandlers() {
		try {
			GoToSourceToUsbViewActionHandler goToSourceToUsbViewActionHandler = new GoToSourceToUsbViewActionHandler(this);
			gotoSourceToUsbViewButton.addActionListener(goToSourceToUsbViewActionHandler);

			GoToUsbToTargetViewActionHandler goToUsbToTargetViewActionHandler = new GoToUsbToTargetViewActionHandler(this);
			gotoUsbToTargetViewButton.addActionListener(goToUsbToTargetViewActionHandler);

		} catch (Exception e) {
			logger.error(className + ".addEventHandlers()", e);
		}
	}

	public static void main(String[] args) {
		try {
//			Properties logProperties = new Properties();
//			String current = System.getProperty("user.dir");
//			System.out.println("Current working directory in Java : " + current);
//
//			logProperties.load(new FileInputStream(current + "/log4j.properties"));
//			PropertyConfigurator.configure(logProperties);
			System.out.println("logger initialized...");
			if (logger.isDebugEnabled()){
				logger.debug("logger test ok!");
			}
			EntranceView entranceView = new EntranceView();
			entranceView.render();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
