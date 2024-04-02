package gui.tabs;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class GameTab extends BasicTabbedPaneUI {

	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {

		Component tabComponent = tabPane.getTabComponentAt(tabIndex);
		if (tabComponent != null) {
			return tabComponent.getWidth();
		}
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
	}
}
