KLASY:
DatabaseClient
DatabaseNode
Main
ListenThread
ServerThread

DatabaseClient:

	Atrybuty - int port

	Konstruktor: 
			public DatabaseClient(int port) {
        			this.port = port;
    			}
	
	Metody - runClient() - ustanawia komunikację z węzłem i przekazuje dane do 				wymiany klucza i wartości do węzła,
		runClient(int klucz, int wartosc)

DatabaseNode:

	Atrybuty :	private int klucz ;
    			private int wartosc;
    			private int port;
			HashMap<Integer,Socket> neighbours - przechowuje komunikację 					konkretnego węzła z innymi węzłami

	Konstruktor: 

			public DatabaseNode(int klucz, int wartosc, int port) {
        			this.klucz = klucz;
       			this.wartosc = wartosc;
        			this.port = port;
    			}

	Metody: addNeighbour(Integer port, Socket socket)


ListenThread:
	Metody: run() - oczekuje na połączenie klientów


ServerThread:
	Atrybut: Socket socket
	konstruktor: public ServerThread(Socket socket) {
            			super();
            			this.socket = socket;
        		}
	Metody: run() - odpowiada na odbieranie / wysyłanie komunikacji


-W moim przypadku mam klasę Database Client który jest klientem i klasy Database Node, który jest węzłami. W klasie Main na samym początku tworzony jest pierwszy węzeł Bazy Danych 1, z którym inne węzły będą się łączyć w przyszłości. Cała logika programu znajduje się w klasie Main. Następnie tworzę pętlę w którym będzie można wprowadzać polecenia takie jak łączenie węzła z innymi węzłami. Utworzono dwie ArrayList pod nazwami list i nodes. Pierwszy arkusz zapisuje wszystkie informacje do-record < klucz>:<wartosc> , a sekcję z węzłami zapisuję do innej tablicy, aby łatwiej było wyciągnąć informacje. Następnie tworzę wyrażenie IF, aby uruchomić węzeł sieciowy, w którym Szukam słów kluczowych. Jeśli warunek przejdzie test, tworzony jest nowy węzeł o nazwie databaseNode i inicjuję go, który połączy się z innym węzłem / węzłami, uruchamiam go. Następnie tworzę sockety w pętli, ponieważ może być ich kilka w poleceniu "Uruchom węzeł sieciowy". Następnie przez mój węzeł databaseNode ustawię połączenia z każdym z węzłów za pomocą metody addNeighbour w argumencie, do którego zapisuję Port węzła, do którego chcę podłączyć węzeł i jego gniazdo.  Wewnątrz metody addNeighbour, która znajduje się w klasie węzła bazy danych, sprawdza się , czy węzeł , który chce połączyć się z innym węzłem, ma już połączenie, jeśli nie, dodaje ten węzeł do "sąsiadów" za pośrednictwem HashMap. Ta metoda tworzy połączenie w obie strony , to znaczy na przykład węzeł z portem 9991 tworzy połączenie z węzłem 9090 i odwrotnie 9090 tworzy połączenie z 9991. Poprzez klasę PrintWriter wysyłam Port węzła do węzła, do którego chcemy się połączyć, drugi węzeł akceptuje ten port i tworzy ze swojej strony Połączenie , również sprawdzając istniejące połączenie między nimi , tylko z jego strony. Jeśli nie ma połączenia, tworzy go przez Socket i dodaje ten węzeł do HashMap. Następnie ponownie wracamy do klasy Main, jeśli chcesz podłączyć klienta do jakiegoś węzła , to w tym celu są 2 kontrole IF , które uruchomią klienta do węzła z możliwością użycia metody "-new-record", która zmieni klucz i wartość węzła. Realizacja odbywa się w metodzie RunClient, która znajduje się w klasie DatabaseClient, istotą tej metody jest zastąpienie tych wartości. W tej samej metodzie następuje utworzenie komunikacji klienta z węzłem za pośrednictwem Socket. Wysyłane za pośrednictwem klienta klucz z wartością W Socket. Węzeł po swojej stronie akceptuje je i natychmiast zmienia wartość wraz z wyświetleniem informacji na ten temat


-zostalo zaimplementowano - podłączanie kolejnych węzłów oraz terminację całości, podlaczenie klienta do węzła z mozliwosca zmiany klucza i wartości węzła.

-nie działają funkcjonalności : set-value <klucz>:<wartość> ,get-value <klucz> :,
find-key <klucz> : , get-max :,  get-min :, terminate :