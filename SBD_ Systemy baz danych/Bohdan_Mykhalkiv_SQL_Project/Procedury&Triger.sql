

--dla PL SQL----

---1----
--ZNALESC SUME WYPLAT WEDLUG MIESACA I ROKU(PODAC W ARGUMENCIE)
CREATE PROCEDURE sumSalaryForMonth (myMonth in varchar2, myYear in varchar2)
is
cnumber number;

BEGIN
SELECT SUM(Wyplata.KwotaWyplaty) into cnumber
FROM Wyplata
WHERE EXTRACT(MONTH FROM Wyplata.Data) like myMonth and EXTRACT(year FROM Wyplata.Data) like myYear ;
DBMS_OUTPUT.PUT_LINE(cnumber);
END;

--PRZYKLAD
exec sumSalaryForMonth ('7' , '2020');

--2--
--PROCEDURA KTORA  ZWOLNIE WYBRANEGO PRACOWNIKA WEDLUG IMIENIU I NAZWISKU(PODAC W ARGUMENCIE)
CREATE PROCEDURE zwolnij_Pracownika (MyImie in varchar2, MyNazwisko in varchar2, data_zwolnienia in date)
is
begin
UPDATE Kadry SET Kadry.DataZwolnienia = data_zwolnienia WHERE KADRY.Imie LIKE MyImie AND KADRY.Nazwisko LIKE MyNazwisko;
END;

--PRZYKLAD
exec zwolnij_Pracownika ('Nikolai' , 'Pushkin', '05-SEP-20');

--3--
--TRIGER KTORY NIE POZWALA DODAWAC PREMIJE WIECEJ NIZ 250
create trigger myTrigger 
after insert OR UPDATE on Premia
for each row
begin 
if :new.Kwota > 250 then
raise_application_error(-20000, 'premia nie moze byc wiecej niz 250!');
end if;
end;

--PRZYKLAD
insert into Premia values (8, 251, '03-AUG-20', 8); 

--4--
--TRIGER KTORY NIE POZWALA DODAC NUMER PASPORTU , JESLI DLUGOSC BEDZIE WIECEJ NIZ 6 ZNAKOW
create trigger MyTrig
after insert OR UPDATE on Kadry
for each row
begin 
if (LENGTH(:new.NumerPaszportu)  > 6 ) then
raise_application_error(-20000, 'Dlugosc numera pasportu musze posiadac nie wiecej niz 6 znakow');
elsif (LENGTH(:new.NumerPaszportu)  < 6) then
raise_application_error(-20000, 'Dlugosc numera pasportu musze posiadac nie mniej niz 6 znakow');
end if;
end;


--PRZYKLAD

insert into Kadry values (9, 'Vladislav','Petrov', '08-MAY-01', 'g16rfff', '02-JUN-20', null, 4, 2);

--DLA T-SQL----
---1-----
--PROCEDURA KTORA WYPISUJE PRACOWNIK Z WYRZSZYM/SREDNIM WYKSZTALCENIEM(PODAC W ARGUMENCIE)
go
create procedure myFunc345345 @nazwa varchar(100)
as
begin

select * 
from kadry
join Wyksztalcenie on Wyksztalcenie.KodWyksztalcenie = Kadry.KodWyksztalcenie
where Wyksztalcenie.Nazwa = @nazwa


end;

--PRZYKLAD


exec myFunc345345 'wykształcenie wyższe';

-2---
--PROCEDURA KTORA ZMINIE STANOWISKO PODAC W ARGUMENCIE
go
create procedure ZmienStanowisko4 @Stanowisko_Z varchar(30), @Stanowisko_na varchar(50)
as
 declare @v_nazwa varchar(50);
declare my_cur cursor for 
select  Stanowisko.Nazwa
from kadry k
join Stanowisko on Stanowisko.KodStanowiska = k.KodStanowiska
where Stanowisko.Nazwa like @Stanowisko_Z;
begin
open my_cur;


fetch next from my_cur into @v_nazwa
while @@FETCH_STATUS = 0
begin

Update Kadry set Kadry.KodStanowiska = (select Stanowisko.KodStanowiska from Stanowisko  where Stanowisko.Nazwa like @Stanowisko_na)
where kadry.KodStanowiska = (select Stanowisko.KodStanowiska from Stanowisko  where Stanowisko.Nazwa like @Stanowisko_Z)
fetch next from my_cur into @v_nazwa

end ;
CLOSE my_cur
DEALLOCATE my_cur
end;

--PRZYKLAD

exec ZmienStanowisko4 'Kierowca' , 'Księgowy';


--3--
TRIGER NIE POZWALA MODYFICOWAC TABELE WYPLATA
go
create trigger trigger1 on Wyplata
for update 
as
print('nie mozna modyfikowac tabele');
rollback

--4--
TRIGER NIE POZWOLI WPISYWAC  WYNAGRODZENIE MNIEJ NIZ MINIMALNE WYNAGRODZENIE 
go
create trigger trigger2 on Wyplata
after insert

as
declare @MinVar int;
set @MinVar = (select MIN(Stanowisko.Wynagrodzenie) from Stanowisko);
if (select KwotaWyplaty from inserted) < @MinVar
print ('Kwota muse byc wiecej niz - ' + cast(@MinVar as varchar(20)));
rollback