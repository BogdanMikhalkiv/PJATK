package zad4;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



class Derevo {
    Derevo prawo, lewo;
    String val;
    int num;

    public Derevo(String val, int num) {
        this.num = num;
        this.val  = val;
    }



    public void setPrawo (Derevo prawo ) {
        this.prawo = prawo;
    }

    public void setLewo(Derevo lewo) {
        this.lewo = lewo;
    }


    public boolean isNull () {
        boolean b = false;
        if (prawo == null && lewo == null) {
            b = true;
        }
        return b;
    }
}

class MyArr {
    Value head;
    Value tail;
    int size = 0;

    public void myCode (Derevo derevo, String s) {
        if (derevo.isNull()) {
            System.out.println(derevo.val + " " + s);
        } else {
            myCode(derevo.lewo, s + "0");
            myCode(derevo.prawo, s +"1");

        }
    }


    public static void mySort (MyArr myArr, int p) {
        if (p > 0) {
            if (myArr.get(p-1, myArr.head,0).derevo.num >= myArr.get(p, myArr.head,0).derevo.num ) {
//                String val = myArr.get(p-1, myArr.head,0).derevo.val;
//                int num = myArr.get(p-1, myArr.head,0).derevo.num;
                Derevo derevo = myArr.get(p-1, myArr.head, 0).derevo;

                myArr.get(p-1, myArr.head,0).derevo = myArr.get(p, myArr.head,0).derevo;



                myArr.get(p, myArr.head,0).derevo = derevo;
                mySort(myArr, p-1);
            }
        }
    }

    public void DELETE (int p, Value value, int counter) {
        if (p == 0 && size != 1) {
            head = head.next;
            size--;
        } else {
            if (size >= 3) {
                if (counter != p - 1) {
                    DELETE(p, value.next, ++counter);
                } else {
                    value.next = value.next.next;
                    size--;
                }
            } else if (size == 2) {
                if (p == 1) {
                    tail = null;
                    size--;
                } else if (p == 0) {
                    head = null;
                    size--;
                }
            } else if (size == 1) {
                head = null;
                size--;
            }
        }
    }

    public void add (Derevo derevo) {
        if (head == null) {
            head = new Value(derevo, null);
            mySort(this, size);
        } else {
            if (head.next == null) {
                tail = new Value(derevo, null);
                head.next = tail;
                mySort(this, size);
            }
            else {
                Value rekord = new Value(derevo, null);
                tail.next = rekord;
                tail = tail.next;
                mySort(this, size);
            }
        }

        size++;

    }

    public void ADD (int p, Derevo derevo) {
        Value value1 = get(p-1, head, 0).next;
        get(p-1, head, 0).next = new Value(derevo, value1);
        size++;
    }

    public Value get (int p, Value value, int counter) {
        Value res = value;
        if (counter != p) {
            res = get(p, value.next, ++counter);
        }
        return res;
    }
}



class Value {
    Derevo derevo;
    Value next;

    public Value (Derevo der, Value next) {
        derevo = der;
        this.next = next;
    }
}

public class s19362 {

    public static void myPrint (MyArr myArr, int p2) {
        if (p2 < myArr.size) {
            System.out.print(myArr.get(p2, myArr.head, 0).derevo.val + " ");
            p2++;
            myPrint(myArr,  p2);
        }
    }

    public static void myLoop (MyArr myArr) {
        if ( myArr.size != 1) {

            Derevo left = myArr.head.derevo;
            Derevo right = myArr.head.next.derevo;

            Derevo derevo = new Derevo(left.val + right.val, left.num + right.num );
            derevo.setLewo(myArr.head.derevo);
            derevo.setPrawo(myArr.head.next.derevo);



            myArr.DELETE(0, myArr.head, 0);
            myArr.DELETE(0, myArr.head, 0);

            myArr.add(derevo);
            myLoop(myArr);
        }
    }

    public static void main(String[] args) {
        MyArr myArr = new MyArr();

        Path path = Paths.get(args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            myArr.add(new Derevo(scanner.next(), Integer.parseInt(scanner.next())));
        }

        System.out.println();
        myLoop(myArr);

        Derevo derevo = myArr.head.derevo;
        myArr.myCode(myArr.head.derevo, "");






        // myArr.get(0, myArr.head, 0).derevo.val
    }
}
