
import java.text.*;

public class doFFT
{
    double sn[];
    double sn2[];
    double xq[];
       int num;

    
    public void fftmain ( )
	{
		//program d12r1
		//driver for routine four1
        int nn, nn2, i,j,isign;
        double pi;
		nn = 1024;
		nn2 = 2 * nn; 
		int q=0;
		pi=Math.PI;
       double data[] = new double[nn2+1];
       double d[] = new double[nn2+1];
        double tmp[] = new double[nn2+1];
   	   double dcmp[] = new double[nn2+1];
        double aaa,x=0,jj;
        FFT g = new FFT();
        DecimalFormat form = new DecimalFormat("0.000000");

		//Create Sample points
        for (i = 1,j=0; i <= 2 * nn - 1; i += 2,j++)
		{               
            x=(j*2*pi/nn);
            data[i]=x*Math.exp(-1*x);
            dcmp[i] = data[i];
            data[i + 1]=0;
            dcmp[i + 1] = data[i + 1];
         }

		g.four1(data, nn);		  

		System.out.println();
		System.out.println("FFT£º");
		System.out.println("\nk\tSmaple Point Real\tSample Point Image\tFFT Real\tFFT Image");
		 for (i = 1,j=0; i <= 2 * nn - 1; i += 2,j++)
		{
		    
            System.out.print(j + "\t");
            System.out.print(form.format(dcmp[i]) + "\t"+form.format(dcmp[i+1])+"\t");
            System.out.println(form.format(data[i]) + "\t"+form.format(data[i+1]));
       }	
	}
    
    
	/**
	 * @return Returns the num.
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num The num to set.
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return Returns the sn.
	 */
	public double[] getSn() {
		return sn;
	}
	/**
	 * @param sn The sn to set.
	 */
	public void setSn(double[] sn) {
		this.sn = sn;
	}
	/**
	 * @return Returns the sn2.
	 */
	public double[] getSn2() {
		return sn2;
	}
	/**
	 * @param sn2 The sn2 to set.
	 */
	public void setSn2(double[] sn2) {
		this.sn2 = sn2;
	}
	
	/**
	 * @return Returns the xq.
	 */
	public double[] getXq() {
		return xq;
	}
	/**
	 * @param xq The xq to set.
	 */
	public void setXq(double[] xq) {
		this.xq = xq;
	}
}
