package spirit.fitness.scanner.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;

public class PrintUIWindow implements Printable, ActionListener {

	  JFrame frameToPrint;

	  public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

	    if (page > 0) { /* We have only one page, and 'page' is zero-based */
	      return NO_SUCH_PAGE;
	    }

	    /*
	     * User (0,0) is typically outside the imageable area, so we must translate
	     * by the X and Y values in the PageFormat to avoid clipping
	     */
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.translate(pf.getImageableX(), pf.getImageableY());

	    /* Now print the window and its visible contents */
	    frameToPrint.printAll(g);

	    /* tell the caller that this page is part of the printed document */
	    return PAGE_EXISTS;
	  }

	  public void actionPerformed(ActionEvent e) {
	    PrinterJob job = PrinterJob.getPrinterJob();
	    job.setPrintable(this);
	    boolean ok = job.printDialog();
	    if (ok) {
	      try {
	        job.print();
	      } catch (PrinterException ex) {
	        /* The job did not successfully complete */
	      }
	    }
	  }

	  public PrintUIWindow(JFrame f) {
	    frameToPrint = f;
	  }
}