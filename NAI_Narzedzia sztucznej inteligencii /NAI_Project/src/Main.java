import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Flower> listTrain = new ArrayList<Flower>();
        List<Flower> listTest = new ArrayList<Flower>();

        //тут я добавляю данные из файла в лист (tutaj ja dodaje dane z pliku do listy)
        try (BufferedReader bf = new BufferedReader(new FileReader(args[0]))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] tmp = line.split(",");
                listTest.add(new Flower(Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4]), tmp[5]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //тут я добавляю данные из файла в лист (tutaj ja dodaje dane z pliku do listy)
        try (BufferedReader bf = new BufferedReader(new FileReader(args[1]))) {
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] tmp = line.split(",");
                 listTrain.add(new Flower(Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]),Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4]), tmp[5]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //тут я добавляю значение в поле "dlugosc" (tutaj ja dodaje wartosc w pole dlugosc)
        for (int i = 0; i < listTest.size() ; i++) {
            for (int j = 0; j < listTrain.size(); j++) {
                listTrain.get(j).dlugosc = Math.sqrt(Math.pow(listTest.get(i).SepalLengthCm - listTrain.get(j).SepalLengthCm, 2)
                        + Math.pow(listTest.get(i).SepalWidthCm - listTrain.get(j).SepalWidthCm, 2)
                        + Math.pow(listTest.get(i).PetalLengthCm - listTrain.get(j).PetalLengthCm, 2)
                        + Math.pow(listTest.get(i).PetalWidthCm - listTrain.get(j).PetalWidthCm, 2)
                );
            }


            //тут я сортирую (tutaj ja sortuje)
            for (int q = 0; q <= listTrain.size() - 2; q++) {
                for (int j = q + 1; j <= listTrain.size() - 1; j++) {
                    if (listTrain.get(q).dlugosc > listTrain.get(j).dlugosc) {
                        Collections.swap(listTrain, q, j);
                    }
                }
            }


            int countSetosa = 0;
            int countVersicolor = 0;
            int countVirginica = 0;



            //тут я считаю сколько раз появился определенный тип цветка (tutaj ja licze ile raz wystapil kwiat)
            for (int w = 0; w < Integer.parseInt(args[2]); w++) {
                if (listTrain.get(w).Species.contains("Iris-setosa")) {
                    countSetosa = countSetosa + 1;
                }
                if (listTrain.get(w).Species.contains("Iris-versicolor")) {
                    countVersicolor++;
                }
                if (listTrain.get(w).Species.contains("Iris-virginica")) {
                    countVirginica++;
                }
            }

            String max = "";

            //тут я сравниваю кто больше появился (tu porównuję kto bardziej się pojawił)
            if (countSetosa > countVersicolor && countSetosa > countVirginica) {
                max = "Iris-setosa";
            }

            if (countVirginica > countSetosa && countVirginica > countVersicolor) {
                max = "Iris-virginica";
            }

            if (countVersicolor > countSetosa && countVersicolor > countVirginica) {
                max = "Iris-versicolor";
            }

            //тут я присваиваю значения цветка который больше всего раз появился
            listTest.get(i).newSpecies = max;



        }

        int counter = 0;

        for (int i = 0; i < listTest.size(); i++) {
            if (listTest.get(i).Species.contains(listTest.get(i).newSpecies)) {
                counter++;
            }
        }

        System.out.println(100*counter/listTest.size() + "%");



//        for (int i = 0; i < listTest.size(); i++) {
//                System.out.println(listTest.get(i) + " ");
//        }

        Scanner scanner = new Scanner(System.in);

        boolean b = false;

        List<Flower> flowerList = new ArrayList<>();

        while (!b) {
            System.out.println(" Enter '1' to start or 'l' to finish");
            String s = scanner.nextLine();
            double SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm;

            if (s.contains("1")) {

                String Species;
                System.out.println("Enter SepalLengthCm: ");
                SepalLengthCm = scanner.nextDouble();
                System.out.println(SepalLengthCm);
                System.out.println("Enter SepalWidthCm: ");
                SepalWidthCm = scanner.nextDouble();
                System.out.println("Enter PetalLengthCm: ");
                PetalLengthCm = scanner.nextDouble();
                System.out.println("Enter PetalWidthCm: ");
                PetalWidthCm = scanner.nextDouble();


                flowerList.add(new Flower(SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm));
            } else  if (s.contains("l")) {
                b = true;
                System.out.println("Enter k");
                int k = scanner.nextInt();
                    for (int i = 0; i < flowerList.size() ; i++) {
                        for (int j = 0; j < listTrain.size(); j++) {
                            listTrain.get(j).dlugosc = Math.sqrt(Math.pow(flowerList.get(i).SepalLengthCm - listTrain.get(j).SepalLengthCm, 2)
                                    + Math.pow(flowerList.get(i).SepalWidthCm - listTrain.get(j).SepalWidthCm, 2)
                                    + Math.pow(flowerList.get(i).PetalLengthCm - listTrain.get(j).PetalLengthCm, 2)
                                    + Math.pow(flowerList.get(i).PetalWidthCm - listTrain.get(j).PetalWidthCm, 2)
                            );
                        }


                        //тут я сортирую (tutaj ja sortuje)
                        for (int q = 0; q <= listTrain.size() - 2; q++) {
                            for (int j = q + 1; j <= listTrain.size() - 1; j++) {
                                if (listTrain.get(q).dlugosc > listTrain.get(j).dlugosc) {
                                    Collections.swap(listTrain, q, j);
                                }
                            }
                        }


                        int countSetosa = 0;
                        int countVersicolor = 0;
                        int countVirginica = 0;



                        //тут я считаю сколько раз появился определенный тип цветка (tutaj ja licze ile raz wystapil kwiat)
                        for (int w = 0; w < k; w++) {
                            if (listTrain.get(w).Species.contains("Iris-setosa")) {
                                countSetosa = countSetosa + 1;
                            }
                            if (listTrain.get(w).Species.contains("Iris-versicolor")) {
                                countVersicolor++;
                            }
                            if (listTrain.get(w).Species.contains("Iris-virginica")) {
                                countVirginica++;
                            }
                        }

                        String max = "";

                        //тут я сравниваю кто больше появился (tu porównuję kto bardziej się pojawił)
                        if (countSetosa > countVersicolor && countSetosa > countVirginica) {
                            max = "Iris-setosa";
                        }

                        if (countVirginica > countSetosa && countVirginica > countVersicolor) {
                            max = "Iris-virginica";
                        }

                        if (countVersicolor > countSetosa && countVersicolor > countVirginica) {
                            max = "Iris-versicolor";
                        }

                        //тут я присваиваю значения цветка который больше всего раз появился
                        flowerList.get(i).Species = max;



                    }


            }

        }
        for (int j = 0; j < flowerList.size(); j++) {
            System.out.println(flowerList.get(j).Species);
        }


    }
}
