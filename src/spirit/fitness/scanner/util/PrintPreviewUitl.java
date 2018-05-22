package spirit.fitness.scanner.util;
import java.awt.BorderLayout;
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

public class PrintPreviewUitl extends JFrame implements ActionListener {

	private static String content = "";
	
	
	public static void main(String[] args){
		new PrintPreviewUitl(content).setVisible(true);
	}
	
	private JTextPane mTextPane;
	
	public PrintPreviewUitl(String _content){
		content = _content;
		setTitle("Printer preview demo");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		
		mTextPane = new JTextPane();
		mTextPane.setContentType("text");
		Font font = new Font("Consolas", Font.PLAIN, 9);
		mTextPane.setFont(font);
		//StringBuilder builder = new StringBuilder();
		//builder.append("<h1>Header</h1><table width=\"100%\">");
		//for(int i=0;i<200;i++)
		//	builder.append("<tr><td>row"+i+", column 1</td><td> column 2</td></tr>");
		//builder.append("</table>");
		
		mTextPane.setText(_content);
		
		/*JButton previewButton = new JButton("Preview");
		previewButton.addActionListener(this);
		
		panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);
		panel.add(previewButton, BorderLayout.SOUTH);
		getContentPane().add(panel);
		
		JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(700, 400);
		dialog.setLayout(new BorderLayout());*/
		
		HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
		set.add(MediaSizeName.JIS_B4);
		set.add(OrientationRequested.PORTRAIT);
		PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);
        //PageFormat can be also prompted from user with PrinterJob.pageDialog()
		final PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
		preview.print();
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setSize(700, 400);
		dialog.setLayout(new BorderLayout());
		
		HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
		set.add(MediaSizeName.JIS_B4);
		set.add(OrientationRequested.PORTRAIT);
		PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);
        //PageFormat can be also prompted from user with PrinterJob.pageDialog()
		final PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);
		
		JButton printButton = new JButton("Print!");
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				preview.print();
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(printButton);
		buttonsPanel.add(preview.getControls());
		
		dialog.getContentPane().add(preview, BorderLayout.CENTER);
		dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		dialog.setVisible(true);
	}
	
}
