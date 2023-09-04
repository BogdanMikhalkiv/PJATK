package zad1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class s19362 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(args[0]);
        int c;
        int index1 = 0;
        int index2 = 0;

        int sum = 0;
        int counter = 1;
        int counter2 = 1;
        int sum2 = 0;

        int sumB = 0;
        int counterB = 1;
        int counter2B = 1;
        int sum2B = 0;
        int counterFirstB = 1;


        String s = "";
        String s2 = "";
        int i = 0;
        int counterFirst = 1;

        boolean first = true;
        boolean isEmpty = false;
        while ((c = fr.read()) != -1) {

            if (first == true) {
                if (Character.isDigit((char) c)) {
                    s += (char) c;
                } else {
                    first = false;
                }

            } else {
                if (Character.isDigit((char) c)) {
                    s2 += (char) c;

                } else {
                    isEmpty = true;
                }
            }
            if (isEmpty == true) {
                //dla rosnace
                if (Integer.parseInt(s) <= Integer.parseInt(s2)) {
                    counter++;
                    if (counterFirst == 1) {
                        sum = Integer.parseInt(s);
                        counterFirst = 0;
                    }
                    sum += Integer.parseInt(s2);
                    if (counter > counter2) {
                        counter2 = counter;
                        sum2 = sum;
                    }
                } else {
                    if (counter > counter2) {
                        counter2 = counter;
                        sum2 = sum;
                        index1 = i - counter2 + 1;
                    }
                    counter = 1;
                    sum = Integer.parseInt(s2);
                }
                //dla malejace
                if (Integer.parseInt(s) >= Integer.parseInt(s2)) {
                    counterB++;
                    if (counterFirstB == 1) {
                        sumB = Integer.parseInt(s);
                        counterFirstB = 0;
                    }
                    sumB += Integer.parseInt(s2);
                    if (counterB > counter2B) {
                        counter2B = counterB;
                        sum2B = sumB;
                    }
                } else {
                    if (counterB > counter2B) {
                        counter2B = counterB;
                        sum2B = sumB;
                        index2 = i - counter2B + 1;

                    }
                    counterB = 1;
                    sumB = Integer.parseInt(s2);
                }


                isEmpty = false;
                s = s2;
                s2 = "";
                i++;
            }
        }
        if (counter2 <= counter2B) {
            if (counter2 == counter2B) {
                if (index1 > index2) {
                    counter2 = counter2B;
                    sum2 = sum2B;
                }
            } else {
                counter2 = counter2B;
                sum2 = sum2B;
            }
        }
        System.out.println(counter2 + " " + sum2);
    }
}





