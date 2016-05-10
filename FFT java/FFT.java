public class FFT
{
	void four1(double data[], int nn)
	{
		long starttime=System.currentTimeMillis();

		
        int n, j, i, m, mmax, istep, isign=1;
        double tempr, tempi, theta, wpr, wpi, wr, wi, wtemp;
        n = 2 * nn;
        j = 1;
        for (i = 1; i <= n ; i = i + 2)
		{
            if (j > i)
			{
                tempr = data[j];
                tempi = data[j + 1];
                data[j] = data[i];
                data[j + 1] = data[i + 1];
                data[i] = tempr;
                data[i + 1] = tempi;
            }
            m = n / 2;
            while (m >= 2 && j > m)
			{
                j = j - m;
                m = m / 2;
            }
            j = j + m;
        }
        mmax = 2;
        while (n > mmax )
		{
            istep = 2 * mmax;
            theta = 6.28318530717959 / (isign * mmax);
            wpr = -2.0 * Math.sin(0.5 * theta) * Math.sin(0.5 * theta);
            wpi = Math.sin(theta);
            wr = 1.0;
            wi = 0.0;
            for (m = 1; m <= mmax; m = m + 2)
			{
                for (i = m; i <= n; i = i + istep)
				{
                    j = i + mmax;
                    tempr = (double)wr * data[j] - (double)wi * data[j + 1];
                    tempi = (double)wr * data[j + 1] + (double)wi * data[j];
                    data[j] = data[i] - tempr;
                    data[j + 1] = data[i + 1] - tempi;
                    data[i] = data[i] + tempr;
                    data[i + 1] = data[i + 1] + tempi;
                }
                wtemp = wr;
                wr = wr * wpr - wi * wpi + wr;
                wi = wi * wpr + wtemp * wpi + wi;
            }
            mmax = istep;
        }
        
        for (i = 1; i <= n;i++)
		{
           	data[i]=data[i]/nn;	
           	
           	if(i%2==0)  data[i]=-1*data[i];	
		
		}
        
		long endtime=System.currentTimeMillis();
		System.out.println("FFT Run Time:"+(endtime-starttime)+"ms");
    
        
	}


}
