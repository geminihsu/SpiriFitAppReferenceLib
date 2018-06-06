package spirit.fitness.scanner.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;

public class PrintPreviewUitl {

	private JTextPane mTextPane;
	private PrintPreview preview;
	private PageFormat pf;

	public PrintPreviewUitl(String content) {

		mTextPane = new JTextPane();
		mTextPane.setContentType("text");
		Font font = new Font("Consolas", Font.PLAIN, 8);
		mTextPane.setFont(font);
		// StringBuilder builder = new StringBuilder();
		// builder.append("<h1>Header</h1><table width=\"100%\">");
		// for(int i=0;i<200;i++)
		// builder.append("<tr><td>row"+i+", column 1</td><td> column 2</td></tr>");
		// builder.append("</table>");

		mTextPane.setText(content);

		/*
		 * JButton previewButton = new JButton("Preview");
		 * previewButton.addActionListener(this);
		 * 
		 * panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);
		 * panel.add(previewButton, BorderLayout.SOUTH); getContentPane().add(panel);
		 * 
		 * JDialog dialog = new JDialog(); dialog.setModal(true); dialog.setSize(700,
		 * 400); dialog.setLayout(new BorderLayout());
		 */

		//HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
		//set.add(MediaSizeName.JIS_B4);
		//set.add(OrientationRequested.PORTRAIT);
		pf = PrinterJob.getPrinterJob().defaultPage();
		// PageFormat can be also prompted from user with PrinterJob.pageDialog()

	}

	public void printContent() {
		if (preview == null) {
			long startTime = System.nanoTime();
			SwingWorker worker = new SwingWorker<PrintPreview, Void>() {
				@Override
				public PrintPreview doInBackground() {
					preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);

					return preview;
				}

				@Override
				public void done() {
					long endTime = System.nanoTime();
					long duration = (endTime - startTime) / 1000000;
					System.out.println(duration);
					preview.print();
				}
			};

			worker.execute();

		} else
			preview.print();
	}

}
