import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        double n = 5;
        int d = 0;
        double[][] kwadrat = new double[20][3];
        double[][] treugolnik = new double[20][3];

        double[] wagi;
        double[] X;
        int T;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 't': ");
        T = scanner.nextInt();

        List<double[]> kwiatyTestKwadrat = new ArrayList<>();
        List<double[]> kwiatyTestTreugolnik = new ArrayList<>();
        List<double[]> kwiatyTrainKwadrat = new ArrayList<>();
        List<double[]> kwiatyTrainTreugolnik = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(args[0]))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] tmp = line.split(",");
                if (tmp[5].contains("setosa")) {
                    double[] a = {1, Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])};
                    kwiatyTestTreugolnik.add(a);
                } else {
                    double[] b = {1, Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])};
                    kwiatyTestKwadrat.add(b);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //тут я добавляю данные из файла в лист (tutaj ja dodaje dane z pliku do listy)
        try (BufferedReader bf = new BufferedReader(new FileReader(args[1]))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] tmp = line.split(",");
                if (tmp[5].contains("setosa")) {
                    double[] a = {1, Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])};
                    kwiatyTrainTreugolnik.add(a);
                } else {
                    double[] b = {1, Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])};
                    kwiatyTrainKwadrat.add(b);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        kwadrat = new double[kwiatyTrainKwadrat.size()][kwiatyTrainKwadrat.get(0).length];
        treugolnik = new double[kwiatyTrainTreugolnik.size()][kwiatyTrainTreugolnik.get(0).length];

        double[][] test1 = new double[kwiatyTestKwadrat.size()][kwiatyTestKwadrat.get(0).length];
        double[][] test2 = new double[kwiatyTestTreugolnik.size()][kwiatyTestTreugolnik.get(0).length];

        for (int i = 0; i < test1.length; i++) {
            for (int j = 0; j < test1[i].length; j++) {
                test1[i][j] = kwiatyTestKwadrat.get(i)[j];
            }
        }

        for (int i = 0; i < test2.length; i++) {
            for (int j = 0; j < test2[i].length; j++) {
                test2[i][j] = kwiatyTestTreugolnik.get(i)[j];
            }
        }

        for (int i = 0; i < kwadrat.length; i++) {
            for (int j = 0; j < kwadrat[i].length; j++) {
                kwadrat[i][j] = kwiatyTrainKwadrat.get(i)[j];
            }
        }

        for (int i = 0; i < treugolnik.length; i++) {
            for (int j = 0; j < treugolnik[i].length; j++) {
                treugolnik[i][j] = kwiatyTrainTreugolnik.get(i)[j];
            }
        }


        Random random = new Random();

        wagi = new double[5];
        for (int i = 0; i < wagi.length; i++) {
            wagi[i] = random.nextDouble() * (1 - 0);
            //System.out.println(wagi[i]);
        }

        int type = -1;
        double[][] currentType = treugolnik;
        boolean b = true;
        while (procent(treugolnik, wagi, -1) < T || procent(kwadrat, wagi, 1) < T) {
            for (int i = 0; i < currentType.length; i++) {
                X = currentType[i];
                while (type != skalarKlass(wagi, X)) {
                    wagi = modyficacjaWag(wagi, X, 0.2, type);
                }
                if (procent(currentType, wagi, type) >= T) {
                    i = currentType.length;
                }
            }
            b = true;
            if (currentType == treugolnik) {
                currentType = kwadrat;
                type = 1;
            } else {
                currentType = treugolnik;
                type = -1;
            }
        }
        System.out.println("procent treug (-1) - " + procent(treugolnik, wagi, -1));
        System.out.println("procent kwadtrat (1) - " + procent(kwadrat, wagi, 1));

        System.out.println("% poprawnych odpowiedzi dla danych test (1) - " + procent(test1, wagi, 1));
        System.out.println("% poprawnych odpowiedzi dla danych test (-1) - " + procent(test2, wagi, -1));

        boolean bol = false;
        double[][] test3 = null;
        //  tutaj z klawiatury
        List<double[]> list = new ArrayList<>();

        while (!bol) {
            System.out.println(" Enter '1' to start or 'l' to finish");
            String s = scanner.nextLine();
            double SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm;


            if (s.contains("1")) {
                System.out.println("Enter SepalLengthCm: ");
                SepalLengthCm = scanner.nextDouble();
                System.out.println("Enter SepalWidthCm: ");
                SepalWidthCm = scanner.nextDouble();
                System.out.println("Enter PetalLengthCm: ");
                PetalLengthCm = scanner.nextDouble();
                System.out.println("Enter PetalWidthCm: ");
                PetalWidthCm = scanner.nextDouble();
                double[] doubles = {1.0, SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm};
                list.add(doubles);
            } else if (s.contains("l")) {
                bol = true;


                test3 = new double[list.size()][5];

                for (int i = 0; i < test3.length; i++) {
                    for (int j = 0; j < test3[i].length; j++) {
                        test3[i][j] = list.get(i)[j];
                    }
                }




            }

        }
        for (int i = 0; i < test3.length; i++) {
            double [] tabX = test3[i];
            System.out.println(skalarKlass(wagi, tabX));

        }
//        System.out.println(test3.length);
//        for (int i = 0; i < test3.length; i++) {
//            for (int j = 0; j < test3[i].length; j++) {
//                System.out.print(test3[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

  static   int skalarKlass (double[] wagi, double[] daneX) {
        int klasyficator = 0;
        double scalar = (wagi[0] * daneX[0]) +(wagi[1] * daneX[1]) + (wagi[2] * daneX[2]) + (wagi[3] * daneX[3]) + (wagi[4] + daneX[4]);
        if (scalar >= 0) {
            klasyficator = 1;
        } else if (scalar < 0){
            klasyficator = -1;
        }
        return klasyficator;
    }

   static int procent(double[][] daneX, double[] wagi, int type) {
        int counter = 0;
        for (int i = 0; i < daneX.length; i++) {
            if (skalarKlass(wagi, daneX[i]) == type) {
                counter++;
            }
        }
        int result = 100*counter/ daneX.length;
        return  result;
    }

   static double[] modyficacjaWag(double[] wagi, double[] daneX, double n, double d) {
        double[] newWagi = new double[wagi.length];
        for (int i = 0; i < wagi.length; i++) {
            newWagi[i] = wagi[i] + (n * d * daneX[i]);
        }
        return newWagi;
    }
}
