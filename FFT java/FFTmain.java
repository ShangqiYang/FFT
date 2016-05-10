import java.text.*;

public class FFTmain
{
    double sn[];
    double sn2[];
    double xq[];
       int num;
    FFTmain(int nnn){
    	this.num=nnn;
       	this.sn = new double[num];
     	this.sn2 = new double[num];
     	this.xq = new double[num];
     	    	
    }
    
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

		//Create Sample Points
        for (i = 1,j=0; i <= 2 * nn - 1; i += 2,j++)
		{               
            x=(j*2*pi/nn);
            data[i]=x*Math.exp(-1*x);
            dcmp[i] = data[i];
            data[i + 1]=0;
            dcmp[i + 1] = data[i + 1];
         }


		  g.four1(data, nn);
		  
		  //¼ÆËãSn(x)
		  int xi;
	      for (q=-nn+1, xi=1; q<nn+1;q++,xi++)
			{
	      	x=(q*2*pi/nn);
	      	sn[xi]=0;
	      	sn2[xi]=0;
	      	xq[xi]=x;
	                  
		    for (i = 1,j=0; i <= 2 * nn - 1; i += 2,j++)
			{					 
		        tmp[i]=Math.cos(j*x);//real
		        tmp[i+1]=Math.sin(j*x);//image		        
		        
		        //Complex Multiply
		        sn[xi]+=(data[i]*tmp[i]-data[i+1]*tmp[i+1]);//real		        
		        sn2[xi]+=(data[i+1]*tmp[i]+data[i]*tmp[i+1]);//image
	        
	         }
				//System.out.print(xi+"   "+form.format(sn[xi])+"        ");
				//System.out.println(form.format(sn2[xi]));

			}


			System.out.println();
			System.out.println("Calculate Sn(x)£º");
			System.out.println("\nXq\tk\tSn(x) Real\tSn(x) Image");
	
		for (q=-nn+1, xi=1; q<nn;q++,xi++)
		{ 
            System.out.print(form.format(q*2*pi/nn) + "\t"+q + "\t");
            System.out.println(form.format(sn[xi]) + "\t"+form.format(sn2[xi]));
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
