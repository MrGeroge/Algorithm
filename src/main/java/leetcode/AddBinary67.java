package leetcode;

/**
 * Created by George on 2017/11/25.
 */
public class AddBinary67 {
    public static void main(String[] args){
        AddBinary67 ab=new AddBinary67();

        System.out.println(ab.addBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101","110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
       //System.out.println('1'-48);
        //Integer.parseInt("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101")
    }
    public String addBinary(String a, String b) {
        if(a=="" || b==""){
            return "";
        }
       //long aInt=Long.parseLong(a);
        // long bInt=Long.parseLong(b);

        int[] aNum=new int[a.length()];
        int[] bNum=new int[b.length()];

        //long atemp=aInt;
        //long btemp=bInt;

        int aIndex=a.length()-1;
        int bIndex=b.length()-1;

        while(aIndex>=0){
            aNum[a.length()-1-aIndex]=a.charAt(aIndex)-48;
            aIndex--;
        }

        while(bIndex>=0){
            bNum[b.length()-1-bIndex]=b.charAt(bIndex)-48;
            bIndex--;
        }

        int cLen;
        if(a.length()>=b.length()){
            cLen=a.length()+1;
        }
        else{
            cLen=b.length()+1;
        }
        int[] cNum=new int[cLen];

        int plus=0;
        int cIndex=0;
        for(;cIndex<Math.min(a.length(),b.length());cIndex++){
            if(aNum[cIndex]+bNum[cIndex]+plus<2){
                cNum[cIndex]=aNum[cIndex]+bNum[cIndex]+plus;
                plus=0;
            }
            else{
                cNum[cIndex]=aNum[cIndex]+bNum[cIndex]+plus-2;
                plus=1;
            }
        }
        if(a.length()>=b.length()){ //a还有剩余元素
            for(int j=cIndex;j<a.length();j++){
                if(plus==0){
                    cNum[j]=aNum[j];
                }
                else{
                    if(aNum[j]+plus>=2){
                        cNum[j]=0;
                        plus=1;
                    }
                    else{
                        cNum[j]=aNum[j]+plus;
                        plus=0;
                    }
                }
            }
        }
        else{
            for(int j=cIndex;j<b.length();j++){
                if(plus==0){
                    cNum[j]=bNum[j];
                }
                else{
                    if(bNum[j]+plus>=2){
                        cNum[j]=0;
                        plus=1;
                    }
                    else{
                        cNum[j]=bNum[j]+plus;
                        plus=0;
                    }
                }
            }
        }
        if(plus==1){
            cNum[cLen-1]=1;
        }
        else{

        }
        StringBuilder sb=new StringBuilder();
        if(cNum[cLen-1]==1){
            sb.append(1);
        }
        for(int i=cLen-2;i>=0;i--){
            sb.append(cNum[i]);
        }

        return sb.toString();

    }
}
