public class DFT
{
	public double [] four1(double data[], int nn)
	{
		long starttime=System.currentTimeMillis();
	        int nn2, i,j,isign;
	        double pi;
			nn2 = 2 * nn;  
			pi=Math.PI;
	       double tmp[] = new double[nn2+1];
	   	   double dcmp[] = new double[nn2+1];
	   	   double d[] = new double[nn2+1];
	       double aaa,x=0,jj;
		      for (int xi=0; xi <=  nn-1; xi++){
		      	x=(xi*2*pi/nn);
		      	double sn=0.0;
		      	double sn2=0.0;
			      for (i = 1,j=0; i <= 2 * nn - 1; i += 2,j++)
					{					 
			        tmp[i]=Math.cos(j*x);//real
			        tmp[i+1]=Math.sin(j*x);//image
			        double l=Math.sqrt(tmp[i]*tmp[i]+tmp[i+1]*tmp[i+1]);
//Complex Multiply
			        sn+=(data[i]/nn*tmp[i]/l);//real
			        sn2+=(-data[i]/nn*tmp[i+1]/l);//image     
		         }
					//System.out.print(xi+"   "+data[2*xi+1]+"        ");
					//System.out.println(data[2*xi+2]);
			        d[2*xi+1]=sn;
			        d[2*xi+2]=sn2;
				}
		long endtime=System.currentTimeMillis();
		System.out.println("DFT Run Time:"+(endtime-starttime)+"ms");
	return d;
	}

	

}
