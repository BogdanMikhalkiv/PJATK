package com.example.diplom;

import com.example.diplom.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;

public class Controller implements Initializable {

    static String sqlQuery;


    static Connection connection = null;
    StatusPage page;

    @FXML
    private Button export;


    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;


    @FXML
    private TabPane tp;



    //sklad
    @FXML
    private Button groupBtn;

    @FXML
    private TableColumn<Sklad, String> nazwa;

    @FXML
    private TableColumn<Sklad, Double> cena;

    @FXML
    private TableColumn<Sklad, LocalDate> data;

    @FXML
    private TableColumn<Sklad, String> typ;

    @FXML
    private TableColumn<Sklad, String> rozmiar;

    @FXML
    private TableView<Sklad> skladTable;

    static ObservableList<Sklad> listSklad = FXCollections.observableArrayList();

    //klient
    @FXML
    private TableColumn<Klient, LocalDate> klientData;

    @FXML
    private TableColumn<Klient, String> klientImie;

    @FXML
    private TableColumn<Klient, String> klientNazwisko;

    @FXML
    private TableView<Klient> klientTable;

    @FXML
    private TableColumn<Klient, String> klientTelefon;

    static ObservableList<Klient> listKlient = FXCollections.observableArrayList();



    @FXML
    private Button filtrButtn;


    //pracownik

    @FXML
    private TableColumn<Worker, String> imie;

    @FXML
    private TableColumn<Worker, String> nazwisko;

    @FXML
    private TableColumn<Worker, Double> pensja;

    @FXML
    private TableColumn<Worker, LocalDate> dataPracownik;

    @FXML
    private TableColumn<Worker, String> telefonPracownik;

    @FXML
    private TableView<Worker> workersTable;

    static ObservableList<Worker> listWorkers = FXCollections.observableArrayList();

    //wyplata

    @FXML
    private TableColumn<Wyplata, LocalDate> wyplataData;

    @FXML
    private TableColumn<Wyplata, String> wyplataImie;

    @FXML
    private TableColumn<Wyplata, Double> wyplataKwota;

    @FXML
    private TableColumn<Wyplata, String> wyplataNazwisko;

    @FXML
    private TableView<Wyplata> wyplataTable;

    static ObservableList<Wyplata> listWyplata = FXCollections.observableArrayList();

    //zamowienie

    @FXML
    private TableColumn<Zamowienie, LocalDate> zamData;

    @FXML
    private TableColumn<Zamowienie, Integer> zamIdKlient;

    @FXML
    private TableColumn<Zamowienie, Integer> zamIdPrac;

    @FXML
    private TableColumn<Zamowienie, String> zamNumer;

    @FXML
    private TableColumn<Zamowienie, Double> zamSuma;

    @FXML
    private TableView<Zamowienie> zamowienieTable;

    static ObservableList<Zamowienie> listZamowienie = FXCollections.observableArrayList();

    @FXML
    private ImageView iconProfile;






    public Controller() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllData();

        //pracownik
        imie.setCellValueFactory (new PropertyValueFactory<Worker, String>("imie"));
        nazwisko.setCellValueFactory (new PropertyValueFactory<Worker, String>("nazwisko"));
        pensja.setCellValueFactory (new PropertyValueFactory<Worker, Double>("wynagrodzenie"));
        dataPracownik.setCellValueFactory (new PropertyValueFactory<Worker, LocalDate>("dataPrzyjecia"));
        telefonPracownik.setCellValueFactory (new PropertyValueFactory<Worker, String>("telefon"));
        workersTable.setItems(listWorkers);

        //sklad
        nazwa.setCellValueFactory (new PropertyValueFactory<Sklad, String>("nazwa"));
        typ.setCellValueFactory (new PropertyValueFactory<Sklad, String>("typ"));
        cena.setCellValueFactory (new PropertyValueFactory<Sklad, Double>("cena"));
        data.setCellValueFactory (new PropertyValueFactory<Sklad, LocalDate>("data"));
        rozmiar.setCellValueFactory (new PropertyValueFactory<Sklad, String>("rozmiar"));
        skladTable.setItems(listSklad);

        //klient

        klientImie.setCellValueFactory (new PropertyValueFactory<Klient, String>("imie"));
        klientNazwisko.setCellValueFactory (new PropertyValueFactory<Klient, String>("nazwisko"));
        klientTelefon.setCellValueFactory (new PropertyValueFactory<Klient, String>("telefon"));
        klientData.setCellValueFactory (new PropertyValueFactory<Klient, LocalDate>("dataRejestracji"));
        klientTable.setItems(listKlient);

        //wyplata

        wyplataImie.setCellValueFactory (new PropertyValueFactory<Wyplata, String>("imie"));
        wyplataNazwisko.setCellValueFactory (new PropertyValueFactory<Wyplata, String>("nazwisko"));
        wyplataKwota.setCellValueFactory (new PropertyValueFactory<Wyplata, Double>("kwota"));
        wyplataData.setCellValueFactory (new PropertyValueFactory<Wyplata, LocalDate>("data"));
        wyplataTable.setItems(listWyplata);

        //zamowienie

        zamData.setCellValueFactory (new PropertyValueFactory<Zamowienie, LocalDate>("data"));
        zamIdKlient.setCellValueFactory (new PropertyValueFactory<Zamowienie, Integer>("idKlient"));
        zamNumer.setCellValueFactory (new PropertyValueFactory<Zamowienie, String>("numer"));
        zamIdPrac.setCellValueFactory (new PropertyValueFactory<Zamowienie, Integer>("idPracownik"));
        zamSuma.setCellValueFactory (new PropertyValueFactory<Zamowienie, Double>("suma"));
        zamowienieTable.setItems(listZamowienie);


        groupBtn.setDisable(true);





    }

    @FXML
    void profileInfo(MouseEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load((getClass().getResource("profileInfo.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void groupHide(MouseEvent event) {
        if (tp.getSelectionModel().getSelectedIndex()==1){
            groupBtn.setDisable(false);
            filtrButtn.setDisable(true);

        }else{
            filtrButtn.setDisable(false);
            groupBtn.setDisable(true);
        }
    }

    @FXML
    void filtr(MouseEvent event) {
        String path = "";
        switch (tp.getSelectionModel().getSelectedIndex()){
            case 0:{
                path = "Filtr.fxml";
                break;
            }
            case 1: {
                break;
            }

            case 2:{
                path = "filtrKlient.fxml";
                break;
            }
            case 3:{
                path = "Filtr.fxml";
                break;
            }

            case 4:{
                path = "Filtr.fxml";
                break;
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource(path));
            loader.load();
            Parent parent = loader.getRoot();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            switch (tp.getSelectionModel().getSelectedIndex()){
                case 0:{
                    FiltrController filtrController = loader.getController();
                    filtrController.setTitleFiltr("Pracownik filtr");
                    filtrController.setType("Pracownik");
                    break;

                }
                case 3:{
                    FiltrController filtrController = loader.getController();
                    filtrController.setTitleFiltr("Wyplata filtr");
                    filtrController.setType("Wyplata");
                    break;
                }
                case 4:{
                    FiltrController filtrController = loader.getController();
                    filtrController.setTitleFiltr("Zamowienie filtr");
                    filtrController.setType("Zamowienie");
                    filtrController.setParameters("Suma",50000);
                    break;
                }
            }


            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch(IOException ex){
            ex.printStackTrace();
            //Logger.getlogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @FXML
    void edit(MouseEvent event) throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        boolean isSelected = false;
        String path = "";
        switch (tp.getSelectionModel().getSelectedIndex()){
            case 0:{
                path = "addPracownik.fxml";
                if (workersTable.getSelectionModel().getSelectedItem() != null) {
                    isSelected = true;
                }
                break;

            }
            case 1:{
                path = "addTowar.fxml";
                if (skladTable.getSelectionModel().getSelectedItem() != null) {
                    isSelected = true;
                }
                break;
            }
            case 2:{
                path = "addKlient.fxml";
                if (klientTable.getSelectionModel().getSelectedItem() != null) {
                    isSelected = true;
                }
                break;
            }
            case 3:{
                path = "addWyplata.fxml";
                if (wyplataTable.getSelectionModel().getSelectedItem() != null) {
                    isSelected = true;
                }
                break;
            }
            case 4:{
                path = "addZamowienie.fxml";
                if (zamowienieTable.getSelectionModel().getSelectedItem() != null) {
                    isSelected = true;
                }
                break;
            }
        }
        if (!isSelected){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze wybrac rekord");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        Parent parent = loader.getRoot();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        switch (tp.getSelectionModel().getSelectedIndex()){
            case 0:{
                AddPracownikController controller = loader.getController();
                Worker worker = workersTable.getSelectionModel().getSelectedItem();
                controller.setTextField(
                        worker.getId(),
                        worker.getImie(),
                        worker.getNazwisko(),
                        worker.getWynagrodzenie(),
                        worker.getDataPrzyjecia(),
                        worker.getTelefon()
                );
                break;
            }
            case 1:{
                AddTowarController controller = loader.getController();
                Sklad sklad = skladTable.getSelectionModel().getSelectedItem();
                controller.setTextField(sklad.getIdSklad(),sklad.getNazwa(),sklad.getTyp(),sklad.getCena(),sklad.getData(),sklad.getRozmiar());
                break;

            }
            case 2:{
                AddKlientController controller = loader.getController();
                Klient klient = klientTable.getSelectionModel().getSelectedItem();
                controller.setTextField(klient.getId(),klient.getImie(),klient.getNazwisko(),klient.getTelefon(),klient.getDataRejestracji());
                break;
            }
            case 3:{
                AddWyplataController controller = loader.getController();
                Wyplata wyplata = wyplataTable.getSelectionModel().getSelectedItem();
                controller.setTextField(wyplata.getIdPracownik(),wyplata.getIdWyplata(),wyplata.getImie(),wyplata.getNazwisko(),wyplata.getKwota(),wyplata.getData());
                break;
            }
            case 4:{
                AddZamowienieController controller = loader.getController();
                Zamowienie zamowienie = zamowienieTable.getSelectionModel().getSelectedItem();
                controller.setTextField(zamowienie);
                break;
            }
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }

    @FXML
    void delete(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        boolean isSelected = false;
        String sqlQuery2 = "";
        switch (tp.getSelectionModel().getSelectedIndex()){
            case 0:{
                Worker worker = workersTable.getSelectionModel().getSelectedItem();
                if (worker != null) {
                    isSelected = true;
                    sqlQuery = "Delete from Pracownik WHERE idPracownik = " + worker.getId();
                }
                break;
            }
            case 1:{
                Sklad sklad = skladTable.getSelectionModel().getSelectedItem();
                if (sklad != null) {
                    isSelected = true;
                    sqlQuery = "Delete from Sklad WHERE idTowar = " + sklad.getIdPanel();
                    sqlQuery2 = "Delete from Panel WHERE idTowar = " + sklad.getIdPanel();
                }
                break;

            }
            case 2:{
                Klient klient = klientTable.getSelectionModel().getSelectedItem();
                if (klient != null) {
                    isSelected = true;
                    sqlQuery = "Delete from Klient WHERE idKlient = " + klient.getId();
                }
                break;
            }
            case 3:{
                Wyplata wyplata = wyplataTable.getSelectionModel().getSelectedItem();
                if (wyplata != null) {
                    isSelected = true;
                    sqlQuery = "Delete from Wyplata WHERE idWyplata = " + wyplata.getIdWyplata();
                }
                break;
            }
            case 4:{
                Zamowienie zamowienie = zamowienieTable.getSelectionModel().getSelectedItem();
                if (zamowienie != null) {
                    isSelected = true;
                    sqlQuery = "Delete from TowarZamowienie WHERE idZamowienie = " + zamowienie.getIdZamowienie();
                    sqlQuery2 = "Delete from Zamowienie WHERE idZamowienie = " + zamowienie.getIdZamowienie();
                }
                break;
            }
        }
        if (!isSelected){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze wybrac rekord!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Podtwierdzasz?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            connection = DbConnect.getDbConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.execute();
            if(!sqlQuery2.equals("")){
                preparedStatement = connection.prepareStatement(sqlQuery2);
                preparedStatement.execute();
            }
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setHeaderText(null);
            alert3.setContentText("Dane zostały pomyślnie usunięte");
            alert.showAndWait();
            loadAllData();
        }
    }


    @FXML
    void groupSklad(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (data.isVisible() == true) {
            listSklad.clear();
            listSklad.addAll(groupSklad());
            cena.setText("Ilosc");
            data.setVisible(false);
            groupBtn.setText("Rozgrupuj");
        } else {
            loadDataSklad();
            cena.setText("Cena");
            data.setVisible(true);
            groupBtn.setText("Grupuj");
        }
    }

    public static void loadAllData(){
        try {
            connection = DbConnect.getDbConnection();
            loadDataSklad();
            loadDataPracownik();
            loadDataKlient();
            loadDataWyplata();
            loadDataZamowienie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void add(MouseEvent event) {
        String path = "";
        int index = tp.getSelectionModel().getSelectedIndex();
        switch (index){
            case 0:
                path = "addPracownik.fxml";
                break;
            case 1:
                path = "addTowar.fxml";
                break;
            case 2:
                path = "addKlient.fxml";
                break;
            case 3:
                path = "addWyplata.fxml";
                break;
            case 4:
                path = "addZamowienie.fxml";
                break;
        }
        try {
            Parent parent = FXMLLoader.load((getClass().getResource(path)));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void loadDataSklad() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listSklad.clear();
        sqlQuery = "Select idSklad, p.idTowar, p.nazwa, p.typ, p.cena, p.dataProdukcji, p.rozmiar from Sklad inner join Panel p on Sklad.idTowar = p.idTowar";


        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            listSklad.add(new Sklad(
                    resultSet.getInt("idSklad"),
                    resultSet.getInt("p.idTowar"),
                    resultSet.getString("p.nazwa"),
                    resultSet.getString("p.typ"),
                    resultSet.getDouble("p.cena"),
                    resultSet.getDate("p.dataProdukcji").toLocalDate(),
                    resultSet.getString("p.rozmiar")
            ));

        }

    }

    public static void filtr(Double pensjaOd, Double pensjaDo, LocalDate dataOd, LocalDate dataDo, String type) {
        switch (type) {
            case "Pracownik":
                List<Worker> filtrPracownik = new ArrayList<>();
                filtrPracownik = listWorkers.stream().
                        filter(s -> (
                                s.getWynagrodzenie() >= pensjaOd && s.getWynagrodzenie() <= pensjaDo) &&
                                (dataOd == null ? s.getDataPrzyjecia().isBefore(dataDo.plusDays(1)) :
                                        (s.getDataPrzyjecia().isAfter(dataOd.minusDays(1))  &&
                                                s.getDataPrzyjecia().isBefore(dataDo.plusDays(1)))))
                        .collect(Collectors.toList());
                listWorkers.clear();
                listWorkers.addAll(filtrPracownik);
                break;

            case "Wyplata":
                List<Wyplata> filtrWyplata = new ArrayList<>();
                filtrWyplata = listWyplata.stream().
                        filter(s -> (
                                s.getKwota() >= pensjaOd && s.getKwota() <= pensjaDo) &&
                                (dataOd == null ? s.getData().isBefore(dataDo.plusDays(1)) :
                                        (s.getData().isAfter(dataOd.minusDays(1))  &&
                                                s.getData().isBefore(dataDo.plusDays(1)))))
                        .collect(Collectors.toList());
                listWyplata.clear();
                listWyplata.addAll(filtrWyplata);
                break;

            case "Zamowienie":
                List<Zamowienie> filtrZamowienie = new ArrayList<>();
                filtrZamowienie = listZamowienie.stream().
                        filter(s -> (
                                s.getSuma() >= pensjaOd && s.getSuma() <= pensjaDo) &&
                                (dataOd == null ? s.getData().isBefore(dataDo.plusDays(1)) :
                                        (s.getData().isAfter(dataOd.minusDays(1))  &&
                                                s.getData().isBefore(dataDo.plusDays(1)))))
                        .collect(Collectors.toList());
                listZamowienie.clear();
                listZamowienie.addAll(filtrZamowienie);
                break;
        }
    }

    public static void filtrKLient( LocalDate dataOd,  LocalDate dataDo) {
                List<Klient> filtrKlient = new ArrayList<>();
                filtrKlient = listKlient.stream().
                        filter(s -> (
                                (dataOd == null ? s.getDataRejestracji().isBefore(dataDo.plusDays(1)) :
                                        (s.getDataRejestracji().isAfter(dataOd.minusDays(1))  &&
                                                s.getDataRejestracji().isBefore(dataDo.plusDays(1))))))
                        .collect(Collectors.toList());
                listKlient.clear();
                listKlient.addAll(filtrKlient);
    }




    public static List<Sklad> groupSklad() throws SQLException{
        List<Sklad> skladList = new ArrayList<>();
        sqlQuery = "Select count(p.nazwa) as ilosc,  p.nazwa, p.typ, p.rozmiar" +
                " from Sklad s inner join Panel p on s.idTowar = p.idTowar" +
                " group by p.nazwa,p.typ,p.rozmiar";


        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            skladList.add(new Sklad(
                    1,
                    1,
                    resultSet.getString("p.nazwa"),
                    resultSet.getString("p.typ"),
                    resultSet.getInt("ilosc"),
                    LocalDate.now(),
                    resultSet.getString("p.rozmiar"),
                    resultSet.getInt("ilosc")
            ));

        }

        return skladList;

    }



    public static void loadDataWyplata() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listWyplata.clear();
        sqlQuery = "Select w.idWyplata,  w.idPracownik, p.Imie , p.Nazwisko, w.kwota, w.data from Wyplata w " +
                "inner join Pracownik p on w.idPracownik = p.idPracownik " +
                "order by w.idWyplata";


        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            listWyplata.add(new Wyplata(
                    resultSet.getInt("w.idWyplata"),
                    resultSet.getInt("w.idPracownik"),
                    resultSet.getString("p.Imie"),
                    resultSet.getString("p.Nazwisko"),
                    resultSet.getDouble("w.kwota"),
                    resultSet.getDate("w.data").toLocalDate()
            ));

        }
    }

    public static void loadDataZamowienie() throws SQLException {
        listZamowienie.clear();
        sqlQuery = "Select *  from Zamowienie ";


        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            listZamowienie.add(new Zamowienie(
                    resultSet.getInt("idKlient"),
                    resultSet.getInt("idPracownik"),
                    resultSet.getInt("idZamowienie"),
                    resultSet.getString("numer"),
                    resultSet.getDouble("suma"),
                    resultSet.getDate("data").toLocalDate()
            ));

        }
    }

    public static void loadDataKlient() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listKlient.clear();
        sqlQuery = "Select * from Klient";


        preparedStatement = connection.prepareStatement(sqlQuery);

        resultSet = preparedStatement.executeQuery();
        //System.out.println(resultSet + "---");

        while (resultSet.next()){
            listKlient.add(new Klient(
                    resultSet.getInt("idKlient"),
                    resultSet.getString("Imie"),
                    resultSet.getString("Nazwisko"),
                    resultSet.getString("Telefon"),
                    resultSet.getDate("Data_Rejestracji").toLocalDate()
            ));

        }
        System.out.println(listKlient);
    }


    public static void loadDataPracownik() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listWorkers.clear();

        sqlQuery = "Select * from Pracownik";


        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();


        while (resultSet.next()){
            listWorkers.add(new Worker(
                    resultSet.getInt("idPracownik"),
                    resultSet.getString("Imie"),
                    resultSet.getString("Nazwisko"),
                    resultSet.getDouble("Wynagrodzenie"),
                    resultSet.getDate("Data_Przyjecia").toLocalDate(),
                    resultSet.getString("Telefon")
            ));
        }
    }

    public static boolean isNumeric(String num, String fieldName){
        if (!num.matches("\\d+(\\.\\d+)*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"" + fieldName + "\" pole musze byc liczba(np. 12,12.5)");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumber(String number, String fieldName){
        if (!number.matches("\\d{9}")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("\"" + fieldName + "\" pole musze byc numerem telofonu o dlugosci 9(np. 123456789)");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    void export(MouseEvent event) throws IOException {
        TableView table;
        switch (tp.getSelectionModel().getSelectedIndex()){
            case 0:{
                table = workersTable;
                break;
            }
            case 1: {
                table = skladTable;

                break;
            }
            case 2:{
                table = klientTable;
                break;
            }
            case 3:{
                table = wyplataTable;
                break;
            }
            case 4:{
                table = zamowienieTable;
                break;
            }
        }
//        Workbook workbook = new HSSFWorkbook();
//        Sheet spreadsheet = workbook.createSheet("sample");
//
//        Row row = spreadsheet.createRow(0);

//        for (int j = 0; j < table.getColumns().size(); j++) {
//            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
//        }
//
//        for (int i = 0; i < table.getItems().size(); i++) {
//            row = spreadsheet.createRow(i + 1);
//            for (int j = 0; j < table.getColumns().size(); j++) {
//                if(table.getColumns().get(j).getCellData(i) != null) {
//                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
//                }
//                else {
//                    row.createCell(j).setCellValue("");
//                }
//            }
//        }

        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//        workbook.write(fileOut);
        fileOut.close();

    }

}
