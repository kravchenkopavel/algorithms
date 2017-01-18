package first_practice;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;

import java.math.BigInteger;

/**
 * Created by 1 on 17.01.2017.
 */
public class Main {

    static BigInteger etalon1_ad_bc = new BigInteger("18");
    static BigInteger etalon2_ad_bc = new BigInteger("9");
    static BigInteger etalon3_ad_bc = new BigInteger("8");
    static BigInteger etalon4_ad_bc = new BigInteger("8");
    static int countEtalon1 = 0;
    static int countEtalon2 = 0;
    static int countEtalon4 = 0;
    static int countEtalon3 = 0;

    public static void main (String[] args){

        String X = "9313685456934674";
        String Y = "7658898761837539";
        String rightAnswer = "71332574014261268360454523927286";
        BigInteger num = new BigInteger(rightAnswer);

        BigInteger result = multipleKarathuba(X,Y);

        System.out.println("Multiple " + X + " on " + Y + " is: \n" + result);
        System.out.println("Result is right: " + (result.equals(num)));
        System.out.println(etalon1_ad_bc + " -> [" + countEtalon1 + "]");
        System.out.println(etalon2_ad_bc + " -> [" + countEtalon2 + "]");
        System.out.println(etalon3_ad_bc + " -> [" + countEtalon3 + "]");
        System.out.println(etalon4_ad_bc + " -> [" + countEtalon4 + "]");
    }

    public static BigInteger multipleKarathuba(String X, String Y){
        int X_width = X.length();
        int Y_width = Y.length();

        if (X_width == 1 && Y_width == 1){
            return BigInteger.valueOf(Integer.parseInt(X)*Integer.parseInt(Y));
        }

        else {
            int MAX_width = 0;

            //определить максимальную разрядность
            if (X_width !=Y_width){
                MAX_width = X_width > Y_width ? X_width : Y_width;
            }
            else MAX_width = X_width;

            //проверить на четность
            if (MAX_width % 2 !=0) MAX_width ++;

            //выровнять числа
            while (MAX_width - X_width > 0){
                X = "0" + X;
                X_width ++;
            }
            while (MAX_width - Y_width > 0){
                Y = "0" + Y;
                Y_width ++;
            }

            String a = X.substring(0, X_width/2);
            String b = X.substring(X_width/2, X_width);
            String c = Y.substring(0, Y_width/2);
            String d = Y.substring(Y_width/2, Y_width);
            //вернуть 10^n*ac + 10^(n/2)*((a+b)*(c+d)-ac-bd) + bd

            BigInteger ac = multipleKarathuba(a, c);
            BigInteger bd = multipleKarathuba(b, d);
            BigInteger ad_bc = multipleKarathuba(((new BigInteger(a)).add(new BigInteger(b))).toString(),
                                                                ((new BigInteger(c)).add(new BigInteger(d))).toString()).
                                                subtract(ac).subtract(bd);

            if (ad_bc.equals(etalon1_ad_bc)){
                countEtalon1 ++;
            }

            if (ad_bc.equals(etalon2_ad_bc)){
                countEtalon2 ++;
            }

            if (ad_bc.equals(etalon3_ad_bc)){
                countEtalon3 ++;
            }

            return (BigInteger.valueOf(10).pow(MAX_width).multiply(ac)).
                    add(BigInteger.valueOf(10).pow(MAX_width/2).multiply(ad_bc)).add(bd);
        }

    }

    public static String addValue(String S1, String S2){

        return null;
    }

    public static String differenceValue(String S1, String S2){
        return null;
    }

    public static String writeZeros(int n){
        String zeros = "";
        for (int i = 0; i < n; i++){
            zeros = zeros + "0";
        }
        return zeros;
    }

    public static String simpleMultiple(String X, String Y){
        return String.valueOf(Integer.parseInt(X)*Integer.parseInt(Y));
    }

    public static int getWidth(String string){
        return string.length();
    }

    public static String getFirstHalf(String string){
        int N = getWidth(string);
        if (N%2==0) return string.substring(0, (N/2 -1 ));
        else return string.substring(0, N/2);
    }

    public String getSecondHalf(String string){
        int N = getWidth(string);
        if (N%2==0) return string.substring(N/2, N-1);
        else return string.substring(N/2 + 1, N -1);
    }

}
