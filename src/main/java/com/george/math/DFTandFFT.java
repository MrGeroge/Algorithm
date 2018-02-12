package com.george.math;

/**
 * Created by George on 2017/10/11.
 */
public class DFTandFFT { //傅立叶变换

    public static void main(String[] args){
        double[] a=new double[5];
        DFTandFFT df=new DFTandFFT();
        System.out.print(df.converse(4));
    }

    public double[] recursiveFFT(double[] a){//

        int n=a.length;
        if(n==1){
            return a;
        }

        double wn=Math.pow(Math.E,2*Math.PI/n);
        double w=1;
        double[] a0=new double[n/2];
        double[] a1=new double[n/2];
        int i0=0;
        for(int i=0;i<=n-2;i=i+2){
            a0[i0]=a[i];
            i0++;
        }
        int i1=0;
        for(int i=1;i<=n-1;i=i+2){
            a1[i1]=a[i];
            i1++;
        }
        double[] y0=recursiveFFT(a0);
        double[] y1=recursiveFFT(a1);

        double[] y=new double[n];

        for(int k=0;k<=n/2-1;k++){
            y[k]=y0[k]+w*y1[k];
            y[k+n/2]=y0[k]-w*y1[k];
            w=w*wn;
        }
        return y;//y是a的离散傅立叶变换DFT
    }

    public double[] iterativeFFT(double[] a){ //迭代快速傅立叶变换
        double[] A=new double[a.length];
        bitReverseCopy(a,A);
        int n=a.length;
        for(int s=1;s<=Math.log10(n);s++){
            double m=Math.pow(2,s);
            double wm=Math.pow(Math.E,2*Math.PI/m);
            for(int k=0;k<=n-1;k=k+(int)m){
                double w=1;
                for(int j=0;j<=m/2-1;j++){
                    double t=w*A[k+j+(int)m/2];
                    double u=A[k+j];
                    A[k+j]=u+t;
                    A[k+j+(int)m/2]=u-t;
                    w=w*wm;
                }
            }
        }
        return A;
    }

    public void bitReverseCopy(double[] a,double[] A){ //对整数数组反转置换
        int n=a.length;
        for(int i=0;i<n;i++){
            A[converse(i)]=a[i];
        }
    }

    public int converse(int tmp){
        Byte c=Byte.valueOf(""+tmp);
        String binaryString = null;
        StringBuffer needAdd = new StringBuffer();
        binaryString = Integer.toBinaryString(Math.abs(c));
            if (binaryString.length() < 8)
            {
                needAdd.append("0");
            }

        for (int i = 0; i < (7 - binaryString.length()); i++)
        {
            needAdd.append("0");
        }
        binaryString = needAdd.append(binaryString).toString();
        System.out.println("原数二进制为: " + binaryString);
        String reverseString = needAdd.reverse().toString();
        System.out.println("反转后二进制为: " + reverseString);
        return Byte.valueOf(reverseString,2);
    }
}


