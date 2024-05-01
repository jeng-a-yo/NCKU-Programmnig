public class Main{

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

        int count = 0, lastIndex = 0;

        str2 = str2.toLowerCase();
        s = s.toLowerCase();

        while((lastIndex = s.indexOf(str2, lastIndex)) != -1){
            count++;
            lastIndex += str2.length() - 1;
        }

        return count >= s2Count;

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
    

    public static void main(String[] args){
        System.out.println("Starting");
        System.out.println(false);

        System.out.println(IsPalindrome("Ss"));
        System.out.println(RepeatedTimes('a', "Abmxabcbbc"));
        System.out.println(RepeatedTimes('b', "Abmxabcbbc"));
    }
}

