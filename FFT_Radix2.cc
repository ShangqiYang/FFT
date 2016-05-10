#include <stdio.h>   
#include <stdlib.h>   
#include <iostream>  
#include "math.h"  

using namespace std;

int N=1024;   
double x[1024],y[1024];   

void  srfft(double x[],double y[],int n)   
{   
    int i,j,k,m,i1,i2,i3,n1,n2,n4,id,is;   
    double a, e, a3, r1,r2;   
    double s1,s2,s3,cc1,cc3,ss1,ss3; 

//Cooley Tukey Series   
  for (j=1,i=1;i<N;i++) {   
          m=i;             //2 to the power of m equals to n. Calculate m here.   
          j=2*j;   
          if(j==n) break;   
      }   
     n2=2*n;   
   
    for(k=1;k<m;k++)          //This m is a loop variable. 
      {n2=n2/2;               //The length of FFT
       n4=n2/4;               //Half of the length of FFT
       e=6.28318530718/n2;    //2π divided by n
       a=0;   
   
       for(j=0;j<n4;j++)     //Do loop until the half length of FFT.  
       {a3=3*a;   
       cc1=cos(a);   
       ss1=sin(a);   
       cc3=cos(a3);   
       ss3=sin(a3);   
       a=(j+1)*e;           //0,1,2,3,4...n-1/2   
       is=j;   
       id=2*n2;   
    do{   
      for ( i=is;i<(n-1);i=i+id)   
        {i1=i+n4;   
         i2=i1+n4;   
         i3=i2+n4;   
         r1=x[i]-x[i2];    //x[n]-x[n+N]   
         x[i]=x[i]+x[i2];   
         r2=x[i1]-x[i3];   
         x[i1]=x[i1]+x[i3]; //3n/4   
         s1=y[i]-y[i2];   
         y[i]=y[i]+y[i2];   
         s2=y[i1]-y[i3];   
         y[i1]=y[i1]+y[i3] ;   
         s3=r1-s2;   
         r1=r1+s2;   
         s2=r2-s1;   
         r2=r2+s1;   
         x[i2]=r1*cc1-s2*ss1;   
         y[i2]=-s2*cc1-r1*ss1;   
         x[i3]=s1*cc3-r2*ss3;   
         y[i3]=r2*cc3-s3*ss3;   
      }   
      is=2*id-n2+j;   
      id=4*id;   
     }   
   while(is<(n-1));   
    }   
}   
   
 is=0;   
 id=4;   
do   
{   
  for(i=is;i<n;i=i+id)   
{   i1=i+1;   
    r1=x[i];   
    r2=y[i];   
  x[i]=r1+x[i1];   
  y[i]=r2+y[i1];   
  x[i1]=r1-x[i1];   
  y[i1]=r2-y[i1];   
   }   
 is=2*id-2;   
 id=4*id;   
}   
while(is<(n-1));   
n1=n-1;

//Index Calculation   
for(j=0,i=0;i<n1;i++)  { 
  if(i<j)   
   {r1=x[j];   
    s1=y[j];   
   x[j]=x[i];   
   y[j]=y[i];   
    x[i]=r1;   
    y[i]=s1;   
}   
k=n/2;   
while(k<(j+1))  {
  j=j-k;   
  k=k/2;   
}   
j=j+k;   
}   
}   
   
   
   
int main()   {
  for (int i=0;i<N;i++){
  x[i] = i;
}
    srfft( x,  y,  N);   
    for(int i=0;i<N;i++)
    cout << i<<'\t'<<y[i]<<endl;
    return 0;   
}   