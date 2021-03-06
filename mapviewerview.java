package com.renesas.cdt.build.mapviewer.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.SelectionStatusDialog;
import org.eclipse.ui.internal.dialogs.ViewContentProvider;
import org.eclipse.ui.part.ViewPart;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import com.renesas.cdt.build.controllers.AddressController;
import com.renesas.cdt.build.controllers.MapViewerController;
import com.renesas.cdt.build.mapviewer.models.MemoryArea;
import com.renesas.cdt.build.mapviewer.models.Section;

public class MapViewerView extends ViewPart {
	ScrolledComposite scrolledComposite;
	Composite container;
	List<MemoryArea> memoryAreas;

	private class LabelPaintListener implements PaintListener {

		private int coloredwidth;
		private Color background;
		private String text;

		LabelPaintListener(int coloredwidth, Color background, String text) {
			this.coloredwidth = coloredwidth;
			this.background = background;
			this.text = text;
		}

		@Override
		public void paintControl(PaintEvent e) {
			Label widget = (Label) e.widget;
			int coloredwidth1 = Math.min(widget.getBounds().width, coloredwidth - widget.getBounds().x);
			int coloredheight1 = widget.getBounds().height;
			e.gc.setBackground(background);
			e.gc.fillRoundRectangle(0, 0, coloredwidth1, coloredheight1, 0, 0);
			e.gc.setBackground(widget.getBackground());
			e.gc.fillRoundRectangle(coloredwidth1, 0, widget.getBounds().width - coloredwidth1, coloredheight1, 0, 0);
			e.gc.drawString(text, 0, 0, true);
		}

	}

	private class BarPaintListener implements PaintListener {

		private MemoryArea memoryArea;
		private Label nameLabel;
		private Label statusLabel;

		BarPaintListener(MemoryArea memoryArea, Label nameLabel, Label statusLabel) {
			this.memoryArea = memoryArea;
			this.nameLabel = nameLabel;
			this.statusLabel = statusLabel;
		}

		@Override
		public void paintControl(PaintEvent e) {
			double usedRatio = ((double) memoryArea.usedSize) / memoryArea.size;
			Composite widget = (Composite) e.widget;
			int coloredwidth = (int) ((widget.getBounds().width - widget.getBorderWidth()) * usedRatio);
			int coloredheight = widget.getBounds().height - widget.getBorderWidth();
			Color background;
			if (usedRatio < 0.75) {
				background = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			} else {
				background = usedRatio < 0.9 ? new Color(Display.getCurrent(), 255, 128, 64)
						: Display.getCurrent().getSystemColor(SWT.COLOR_RED);
			}
			e.gc.setBackground(background);
			widget.drawBackground(e.gc, 0, 0, coloredwidth, coloredheight, 0, 0);
//			e.gc.setBackground(background);
//			e.gc.fillRoundRectangle(0, 0, coloredwidth, coloredheight, 0, 0);
//			nameLabel.addPaintListener(new LabelPaintListener(coloredwidth, background, memoryArea.name));
//			statusLabel.addPaintListener(new LabelPaintListener(coloredwidth, background,
//					String.format("%d/%d bytes or %.2f%%", memoryArea.usedSize, memoryArea.size, usedRatio * 100)));
		}
	}
	

	@Override
	public void createPartControl(Composite parent) {	
		memoryAreas = MapViewerController.getInstance().getDummyData();
		scrolledComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite container = new Composite(scrolledComposite, SWT.NONE);
		container.setLayout(new GridLayout());
		for (MemoryArea memoryArea : memoryAreas) {
			// composite container
			Composite memoryAreaComposite = new Composite(container, SWT.BORDER);
			memoryAreaComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			memoryAreaComposite.setLayout(new GridLayout(1, false));
			Composite aboveComposite = new Composite(memoryAreaComposite, SWT.BORDER);
			aboveComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			aboveComposite.setLayout(new GridLayout(3, false));
			Button button = new Button(aboveComposite, SWT.NONE);
			button.setLayoutData(new GridData(SWT.WRAP, SWT.TOP, false, false));
			button.setText("+");
			
			Label startAdressLabel = new Label(aboveComposite, SWT.NONE);
			startAdressLabel.setLayoutData(new GridData(GridData.FILL_VERTICAL));
			startAdressLabel.setText(AddressController.convertLongNumberToHexAddress(memoryArea.startAddress));
			
			Composite barComposite = new Composite(aboveComposite, SWT.BORDER);
			barComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			barComposite.setLayout(new GridLayout());
			Label nameLabel = new Label(barComposite, SWT.NONE);
			nameLabel.setText(memoryArea.name);
			Label statusLabel = new Label(barComposite, SWT.CENTER);
			statusLabel.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
			if (memoryArea.size != -1) {
				statusLabel.setText(String.format("%d/%d bytes or %.2f%%", memoryArea.usedSize, memoryArea.size,
						100.0 * memoryArea.usedSize / memoryArea.size));
				barComposite.addPaintListener(new BarPaintListener(memoryArea, nameLabel, statusLabel));
			}
			Composite belowComposite = new Composite(memoryAreaComposite, SWT.BORDER);
			belowComposite.setLayout(new GridLayout(1, false));
			GridData gridDataBelow = new GridData(GridData.FILL_HORIZONTAL);
			gridDataBelow.heightHint = 0;
			belowComposite.setLayoutData(gridDataBelow);
			
			belowComposite.setLayout(new GridLayout());
			button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (button.getText() == "-") {
						belowComposite.dispose();
						button.setText("+");
					} else {
						createSection(belowComposite, memoryArea);
						button.setText("-");
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								belowComposite.redraw();								
							}
						});
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
				}
			});
		}
		scrolledComposite.setContent(container);
		scrolledComposite.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
		parent.redraw();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void createSection(Composite parent, MemoryArea memoryArea) {
		List<Section> sections = memoryArea.getSection();
		if(!sections.isEmpty()) {
			for(Section s : sections) {
				Composite sectionComposite = new Composite(parent, SWT.NONE);
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
				gridData.horizontalAlignment = 10;
				sectionComposite.setLayoutData(gridData);
				sectionComposite.setLayout(new GridLayout(4, false));
				
				Label color = new Label(sectionComposite, SWT.BORDER);
				color.setLayoutData(new GridData());
//				color.setBackground(SWT.BACKGROUND);
				
				Label address = new Label(sectionComposite, SWT.NONE);
				address.setLayoutData(new GridData());
				address.setText(AddressController.convertLongNumberToHexAddress(s.startAddress));
				
				Label sectionName = new Label(sectionComposite, SWT.NONE);
				sectionName.setLayoutData(new GridData());
				sectionName.setText(s.name);
				
				Label size = new Label(sectionComposite, SWT.NONE);
				size.setLayoutData(new GridData());
				size.setText(String.valueOf(s.size));
				
				
			}
		}
	}

}