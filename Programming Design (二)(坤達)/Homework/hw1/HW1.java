import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HW1{

    static boolean IsPalindrome(String s){
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--){
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    static boolean IsContainsString(String str1, String s){
        str1 = str1.toLowerCase();
        s = s.toLowerCase();

        return s.contains(str1);

    }

    static boolean IsContainsStringTimes(int s2Count, String str2, String s){
        str2 = str2.toLowerCase();
        s = s.toLowerCase();

        return s2Count == s.length() - s.replace(str2, "").length();

    }

    static int RepeatedTimes(char chr, String s){
        s = s.toLowerCase();
        chr = Character.toLowerCase(chr);

        int maxRepeatTimes = -1, times = 0;

        for (int i = 0; i < s.length(); i++){

            if (s.charAt(i) == chr && times == 0){
                times = 1;
            }else if (s.charAt(i) == chr && times != 0){
                times += 1;
            }
            if (s.charAt(i) != chr || i == s.length() - 1){
                if (times > maxRepeatTimes){
                    maxRepeatTimes = times;
                }
                times = 0;
            }

        }
        return maxRepeatTimes;

    }

    static char BooleanToChar(boolean bool){
        return bool ? 'Y' : 'N';
    }

    public static void main(String[] args) {
        String str1 = args[1];
        String str2 = args[2];
        int s2Count = Integer.parseInt(args[3]);

        //For your testing of input correctness

        /*
        System.out.println("The input file:"+args[0]);
        System.out.println("str1="+str1);
        System.out.println("str2="+str2);
        System.out.println("num of repeated requests of str2 = "+s2Count);
        */

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {

                System.out.print(BooleanToChar(IsPalindrome(line)) + ",");
                System.out.print(BooleanToChar(IsContainsString(str1, line))) + ",";
                System.out.print(BooleanToChar(IsContainsStringTimes(s2Count, str2, line)) + ",");
                System.out.println(BooleanToChar(RepeatedTimes('a', line) * 2 == RepeatedTimes('b', line)));
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}