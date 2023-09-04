

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


class MyArr {
    Value head;
    Value tail;
    int size = 0;

    public void add (int val) {
        if (head == null) {
            head = new Value(val, null);
        }else {
            if (head.next == null) {
                tail = new Value(val, null);
                head.next = tail;
            }
            else {
                Value rekord = new Value(val, null);
                tail.next = rekord;
                tail = tail.next;
            }
        }

        size++;

    }

    public void ADD (int p, int value) {
        Value value1 = get(p-1, head, 0).next;
        get(p-1, head, 0).next = new Value(value, value1);

        size++;
    }

    public Value get (int p, Value value, int counter) {
        Value res = value;
        if (counter != p) {
            res = get(p, value.next, ++counter);
        }
        return res;
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
}

class Value {
    int val;
    Value next;

    public Value (int val, Value next) {
        this.val = val;
        this.next = next;
    }
}



public class s19362 {



    public static void myPrint (MyArr myArr, int p2, int prevIndex, int counter) {
        if (p2 <= myArr.size && counter == 1) {
            if (p2 == myArr.size) {
                p2 = 0;
                myArr.size = prevIndex;
                counter++;
                myPrint(myArr,  p2, prevIndex, counter);
                counter++;
            }
            if (p2 < myArr.size && counter == 1){
                System.out.print(myArr.get(p2, myArr.head, 0).val + " ");
                p2++;
                myPrint(myArr,  p2, prevIndex, counter);
            }
        }
        if (p2 < myArr.size && counter == 2) {

            System.out.print(myArr.get(p2, myArr.head, 0).val + " ");
            p2++;
            myPrint(myArr,  p2, prevIndex, counter);
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
        int counter3 = 1;
        int predel = 0;
        int i = 0;
        String s = "";
        predel = Integer.parseInt(scanner.next());

        while (scanner.hasNext()) {
            myArr.add(Integer.parseInt(scanner.next()));
        }






        int p = 0;

        int p2 = 0;
        int prevIndex;

        int counter = 1;
        for (int count = 1; count <= predel; count++) {
            if (myArr.size == 1) {
                if (myArr.get(p, myArr.head, 0).val % 2 == 0) {
                    myArr.DELETE(0,myArr.head, 0);
                } else {
                    if ((p + 1) >= myArr.size) {
                        int w = myArr.get(p, myArr.head, 0).val ;
                        myArr.add(myArr.get(p, myArr.head, 0).val  - 1);
                        p = myArr.size-1;
                        p += w;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);
                            if (p2 > p) {
                                p++;
                            }
                        }
                    } else {
                        int g = myArr.get(p, myArr.head, 0).val ;
                        myArr.ADD(p + 1, g-1);
                        p += g;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);
                        }
                    }
                }
            } else if (myArr.size > 1){
                if (myArr.get(p, myArr.head, 0).val  % 2 != 0) {
                    if ((p + 1) >= myArr.size) {
                        int w = myArr.get(p, myArr.head, 0).val ;
                        myArr.add( myArr.get(p, myArr.head, 0).val  - 1);
                        p += w;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);
                        }
                    } else {
                        int g = myArr.get(p, myArr.head, 0).val ;
                        myArr.ADD(p + 1, g-1);
                        p += g;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);
                        }
                    }
                } else {
                    if ((p + 1) >= myArr.size) {
                        int q = myArr.get(0, myArr.head, 0).val ;
                        myArr.DELETE(0, myArr.head, 0);
                        p--;
                        p += q;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);

                        }
                    } else {
                        int x = myArr.get(p+1, myArr.head, 0).val;
                        myArr.DELETE(p+1, myArr.head, 0);
                        p += x;
                        if (p >= myArr.size) {
                            p = ((p) % myArr.size);
                        }
                    }
                }
            }
            p2 = p;
        }
        prevIndex = p2;

        myPrint(myArr,  p2,prevIndex,counter);

    }


}
