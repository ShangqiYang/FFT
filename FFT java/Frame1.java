import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

public class Frame1 extends javax.swing.JFrame {
    double x[];
    double y[];
    double z1;
    double z2;
    int q;
	int m;
	private javax.swing.JButton ivjJButton1 = null;
	private javax.swing.JButton ivjJButton2 = null;
	private javax.swing.JButton ivjJButton3 = null;
	private javax.swing.JPanel ivjJFrameContentPane = null;

	public Frame1() {
		super();
		initialize();
		repaint();
	}

	/**
	 * Return the JButton1 property value.
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton1() {
		if (ivjJButton1 == null) {
			ivjJButton1 = new javax.swing.JButton();
			ivjJButton1.setName("JButton1");
			ivjJButton1.setText("calculate Sn(x)");
			ivjJButton1.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					
					 try{
					 		m=2049;
			                x = new double[2049];
			                y = new double[2049];
		
			            }
			            catch(Exception exception)
			            { ;   }			        
					q=1;
					repaint();

				}
			});
		}
		return ivjJButton1;
	}


	private javax.swing.JButton getJButton2() {
		if (ivjJButton2 == null) {
			ivjJButton2 = new javax.swing.JButton();
			ivjJButton2.setName("JButton2");
			ivjJButton2.setText("FFT[Console Outup]");
			ivjJButton2.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  					
					 try{
					 	doFFT dofft=new doFFT();
					 	dofft.fftmain();
				
			         }
			            catch(Exception exception)
			            { ;}			        
					q=0;
					}
			});
		}
		return ivjJButton2;
	}


	private javax.swing.JButton getJButton3() {
		if (ivjJButton3 == null) {
			ivjJButton3 = new javax.swing.JButton();
			ivjJButton3.setName("JButton3");
			ivjJButton3.setText("DFT[Console Output]");
			ivjJButton3.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  					
					 try{
					 	doDFT dodft=new doDFT();
					 	dodft.fftmain();
				
			         }
			            catch(Exception exception)
			            { ;}			        
					q=0;
					}
			});
		}
		return ivjJButton3;
	}
	/**
	 * Return the JFrameContentPane property value.
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {
			GridLayout gridLayout2 = new GridLayout();
			ivjJFrameContentPane = new javax.swing.JPanel();
			//ivjJFrameContentPane.setLayout(gridLayout2);
			ivjJFrameContentPane.setName("JFrameContentPane");

			ivjJFrameContentPane.add(getJButton1(), null);
			ivjJFrameContentPane.add(getJButton2(), null);
			ivjJFrameContentPane.add(getJButton3(), null);
			
		}
		return ivjJFrameContentPane;
	}

	/**
	 * Initialize the class.
	 */
	private void initialize() {
		//this.setSize(800,600);

		this.setName("FFT Test");
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(45, 25, 800, 600);
		this.setTitle("FFT Test");
		this.setContentPane(getJFrameContentPane());
		repaint();

	}
	
	public void paint(Graphics  g) {
		super.paint(g) ;

		//this.setSize(800,600);

        int x1 = 0;
        int y1 = 0;
        int x2 = getSize().width - 1;
        int y2 = getSize().height - 1;
        g.drawRect(x1, y1, x2, y2);
        x1 = x2/4;
        y1 = y2/4;
        int wide = x2/2;
        int high = y2/2;
        g.clearRect(0,100,x2,y2);
       
        if(q == 1)
        {
           	int scale=50;  
  
        
            
        	FFTmain fft=null;
			double ty[]=null;
			try{
				fft=new FFTmain(m);
				fft.fftmain();
				x=fft.getXq();
				y=fft.getSn();
				
				double xxx,xxx0=0;
				double dd,dd0=0;
				
		        for (int jj=-1023; jj <=1023; jj++)
				{               
		            xxx=(jj*2*Math.PI/1023);
		            dd=xxx*Math.exp(-1*xxx);

		          	g.drawLine(wide+(int)(xxx*scale),high-(int)(dd*scale),wide+(int)(xxx*scale),high-(int)(dd*scale));
		          	dd0=dd;
		          	xxx0=xxx;
		          	
		          	
		         }
				
			}
			catch(Exception e) {
		        e.printStackTrace();
		      }
			
			
	//	System.out.println(ty[1]);
	//		System.out.print(ty[2]);

	        
          Rectangle rectangle3 = getBounds();
          int l11 = rectangle3.height;
          int i12 = rectangle3.width;
          g.setColor(Color.black);
          
          g.drawLine(0, high, x2, high);
          g.drawLine(wide, 100, wide, y2);
 
          g.drawString("|", wide+(int)(x[1]*scale),high);
          g.drawString("-2¦Ð", -5+wide+(int)(x[1]*scale),14+high);
          g.drawString("|", wide+(int)(x[512]*scale),high);
          g.drawString("-¦Ð", -5+wide+(int)(x[512]*scale),14+high);
          g.drawString("0", 2+wide,14+high);
          g.drawString("|", wide+(int)(x[1535]*scale),high);
          g.drawString("¦Ð", -5+wide+(int)(x[1535]*scale),14+high);
          g.drawString("|", wide+(int)(x[2047]*scale),high);
          g.drawString("2¦Ð", -5+wide+(int)(x[2047]*scale),14+high);
 
          g.drawString("f(x)=x*exp(-x)", wide-136,high+220);
                 
          g.setColor(Color.RED);


          double xx=0,yy=0;
         xx=(x[1]);
         yy=(y[1]);
    
          for(int i1 = 1; i1 <m; i1++)
          {
 
          	g.drawLine(wide+(int)(xx*scale),high-(int)(yy*scale),wide+(int)(x[i1]*scale),high-(int)(y[i1]*scale));
           	xx=x[i1];
           	yy=y[i1];
          	
       //     g.drawOval(30+((int)(x[i1])*scale)-4, l11 - 100-(int)(y[i1]*scale)-4,8,8);
         //   g.drawString("(" + x[i1] + "," + y[i1] + ")", 30 + (int)(x[i1]*scale), l11 - 80 - (int)(y[i1]*scale));
            
			 // drawString("¡ñ", 30+(int)x[i1], l11 - 100-(int)y[i1]+3);
             // g.drawString("(" + x[i1] + "," + y[i1] + ")", 30 + (int)x[i1], l11 - 120 - (int)y[i1]);
          }
          
              
          
          java.text.DecimalFormat f1 = new java.text.DecimalFormat("0.00");      
         
       
/*	  String str[]=sp.getStr();
	       for(int i=1;i<str.length;i++) {	       	
			  g.drawString(str[i],240,310+i*18);    
			  System.out.println(str[i]);
         	
         }*/
	       
		  
                }
 
      }
	
	
}  //  @jve:decl-index=0:visual-constraint="9,36"
