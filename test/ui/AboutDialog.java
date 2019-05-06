
package test.ui;


class AboutDialog extends java.awt.Dialog implements java.awt.event.ActionListener, java.awt.event.WindowListener {
	private java.awt.Button ivjButton1 = null;
	private java.awt.Label ivjLabel1 = null;
	private java.awt.Label ivjLabel2 = null;
	private Logo ivjLogo1 = null;


public AboutDialog(java.awt.Frame parent) {
	super(parent);
	initialize();
}


public AboutDialog(java.awt.Frame parent, String title) {
	super(parent, title);
}

public AboutDialog(java.awt.Frame parent, String title, boolean modal) {
	super(parent, title, modal);
}

public AboutDialog(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
}

public void actionPerformed(java.awt.event.ActionEvent e) {
	// user code begin {1}
	// user code end
	if ((e.getSource() == getButton1()) ) {
		conn1(e);
	}
	// user code begin {2}
	// user code end
}


private void conn0(java.awt.event.WindowEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}

private void conn1(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}

private java.awt.Button getButton1() {
	if (ivjButton1 == null) {
		try {
			ivjButton1 = new java.awt.Button();
			ivjButton1.setName("Button1");
			ivjButton1.setLabel("Close");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjButton1;
}

private java.awt.Label getLabel1() {
	if (ivjLabel1 == null) {
		try {
			ivjLabel1 = new java.awt.Label();
			ivjLabel1.setName("Label1");
			ivjLabel1.setFont(new java.awt.Font("dialog", 1, 36));
			ivjLabel1.setText("JUnit");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjLabel1;
}

private java.awt.Label getLabel2() {
	if (ivjLabel2 == null) {
		try {
			ivjLabel2 = new java.awt.Label();
			ivjLabel2.setName("Label2");
			ivjLabel2.setFont(new java.awt.Font("dialog", 2, 14));
			ivjLabel2.setText("By Kent Beck and Erich Gamma");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjLabel2;
}

private Logo getLogo1() {
	if (ivjLogo1 == null) {
		try {
			ivjLogo1 = new test.ui.Logo();
			ivjLogo1.setName("Logo1");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	return ivjLogo1;
}

private void handleException(Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}

private void initConnections() {
	// user code begin {1}
	// user code end
	this.addWindowListener(this);
	getButton1().addActionListener(this);
}

/**
 * Initialize class
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	// user code begin {1}
	// user code end
	java.awt.GridBagConstraints constraintsLabel1 = new java.awt.GridBagConstraints();
	java.awt.GridBagConstraints constraintsLabel2 = new java.awt.GridBagConstraints();
	java.awt.GridBagConstraints constraintsButton1 = new java.awt.GridBagConstraints();
	java.awt.GridBagConstraints constraintsLogo1 = new java.awt.GridBagConstraints();
	setName("AboutDialog");
	setName("AboutDialog");
	setResizable(false);
	setLayout(new java.awt.GridBagLayout());
	setSize(296, 138);
	setTitle("About");

	constraintsLabel1.gridx = 3; constraintsLabel1.gridy = 0;
	constraintsLabel1.gridwidth = 1; constraintsLabel1.gridheight = 1;
	constraintsLabel1.anchor = java.awt.GridBagConstraints.CENTER;
	constraintsLabel1.weightx = 0.0;
	constraintsLabel1.weighty = 0.0;
	((java.awt.GridBagLayout) this.getLayout()).setConstraints(getLabel1(), constraintsLabel1);
	this.add(getLabel1());

	constraintsLabel2.gridx = 2; constraintsLabel2.gridy = 1;
	constraintsLabel2.gridwidth = 2; constraintsLabel2.gridheight = 1;
	constraintsLabel2.anchor = java.awt.GridBagConstraints.CENTER;
	constraintsLabel2.weightx = 0.0;
	constraintsLabel2.weighty = 0.0;
	((java.awt.GridBagLayout) this.getLayout()).setConstraints(getLabel2(), constraintsLabel2);
	this.add(getLabel2());

	constraintsButton1.gridx = 2; constraintsButton1.gridy = 2;
	constraintsButton1.gridwidth = 2; constraintsButton1.gridheight = 1;
	constraintsButton1.anchor = java.awt.GridBagConstraints.CENTER;
	constraintsButton1.weightx = 0.0;
	constraintsButton1.weighty = 0.0;
	constraintsButton1.insets = new java.awt.Insets(8, 0, 8, 0);
	((java.awt.GridBagLayout) this.getLayout()).setConstraints(getButton1(), constraintsButton1);
	this.add(getButton1());

	constraintsLogo1.gridx = 2; constraintsLogo1.gridy = 0;
	constraintsLogo1.gridwidth = 1; constraintsLogo1.gridheight = 1;
	constraintsLogo1.anchor = java.awt.GridBagConstraints.CENTER;
	constraintsLogo1.weightx = 0.0;
	constraintsLogo1.weighty = 0.0;
	((java.awt.GridBagLayout) this.getLayout()).setConstraints(getLogo1(), constraintsLogo1);
	this.add(getLogo1());
	initConnections();
	// user code begin {2}
	// user code end
}

/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		test.ui.AboutDialog aAboutDialog = new test.ui.AboutDialog(new java.awt.Frame());
		aAboutDialog.setModal(true);
		try {
			Class aCloserClass = Class.forName("uvm.abt.edit.WindowCloser");
			Class parmTypes[] = { java.awt.Window.class };
			Object parms[] = { aAboutDialog };
			java.lang.reflect.Constructor aCtor = aCloserClass.getConstructor(parmTypes);
			aCtor.newInstance(parms);
		} catch (java.lang.Throwable exc) {};
		aAboutDialog.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of java.awt.Dialog");
	}
}

public void windowActivated(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

public void windowClosed(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

public void windowClosing(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	if ((e.getSource() == this) ) {
		conn0(e);
	}
	// user code begin {2}
	// user code end
}

public void windowDeactivated(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

public void windowDeiconified(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

public void windowIconified(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

public void windowOpened(java.awt.event.WindowEvent e) {
	// user code begin {1}
	// user code end
	// user code begin {2}
	// user code end
}

}