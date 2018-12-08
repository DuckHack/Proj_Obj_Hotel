# Proj_Obj_Hotel

Aby miec możliwośc uruchomienia projektu w Intellij:
	Otworzyc projek – jako maven project
	Zainstalowac plugin Lombok dla Intellij
	W ustawienia wlaczyc opcje – annotation processing
	Stworzyc nowy DataSource na podstawie “identifier.sqlite” pliku
Intellij automatycznie znajdzie plik z main metoda, po czym project jest gotowy do uruchomienia

Aby uruchomić program w środowisku Intellij należy:
	
* Otworzyć projekt jako Maven Project
* Zainstalować wtyczkę Lombok
* W ustawieniach środowiska włączyć opcję Annotation Processing
* Stworzyć nowy DataSource na podstawie pliku identifier.sqlite

Środowisko Intellij automatycznie znajdzie plik z metodą main, po czym projekt będzie gotowy do uruchomienia.

Można też uruchomić projekt z gotowego pliku .jar, do tego będzie potrzebny wolny port 8080.
Wtedy projekt uruchamiamy poleceniem java -jar <nazwa pliku>


## com.projektowanie.ojektowe.controllers

*Przyjmuje dane wejściowe od użytkownika i reaguje na jego poczynania, zarządzając aktualizacje modelu oraz odświeżenie widoków.*

### Classes:
**ReservationController:**

        Konstruktor: 
        
            ReservationController(ReservationRepository reservationRepository) 
        
        Metody:
        
            addReservation (Metoda pozwalająca na dodanie rezerwacji)
              @PostMapping(value="/add")
              public org.springframework.http.ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation)
              throws ReservationEndBeforeStartException
            
            getByFilter (Metoda, która pozwala otrzymać przez filtrowanie listę wykonanych rezerwacji)
              @PostMapping(value="/filterSearch")
              public java.util.List<Reservation> getByFilter(@RequestBody ReservationFilter reservationFilter)
              throws EmptyReservationFilterException
            
            delete (Metoda, którą usuwamy wybraną rezerwację)
              @DeleteMapping(value="/delete/{id}")
              public void delete(@PathVariablem java.lang.String id)


**RoomController:**  
        
        Konstruktor: 
            
            RoomController(RoomRepository roomRepository, ReservationRepository reservationRepository) 
        
        Metody:
            
            getRooms (Metoda pozwalająca na pobranie wszystkich pokoi)
              @GetMapping(value="/getAll")
              public java.util.List<Room> getRooms()
            
            addRoom (Metoda pozwalająca na dodanie pokoju)
              @PostMapping(value="/add")
              public org.springframework.http.ResponseEntity<Room> addRoom(@RequestBody Room room)
            
            deleteRoom (Metoda pozwalająca na usunięcie pokoju)
              @DeleteMapping(value="/delete/{number}")
              public void deleteRoom(@PathVariable(value="number") java.lang.String number)
            
            getFreeRooms (Metoda pozwalająca na pobranie pokoi bez rezerwacji)
              @PostMapping(value="/getFree")
              ublic java.util.List<Room> getFreeRooms(@RequestBody RoomFilter roomFilter)
            
            getFreeRoomsForGroup (Metoda pozwalająca na pobranie pokoi bez rezerwacji dla grupy)
              @PostMapping(value="/getFreeGrouped")
              public java.util.List<java.util.List<Room>> getFreeRoomsForGroup(@RequestBody RoomFilter roomFilter)

**UserController:**
        
        Konstruktor: 
            
            UserController() 
        
        Metody:
            
            register
              @PostMapping(value="/register")
              public org.springframework.http.ResponseEntity<User> register(@RequestBody User user)
              throws UserAlreadyExistException
            
            login
              @PostMapping(value="/email")
              public org.springframework.http.ResponseEntity<User> login(@RequestBody LoginUser loginUser)
              throws UserDoesentExistException
            
            getAll
              @GetMapping(value="/all")
              public java.util.List<User> getAll()
              
            deleteRoom
              @DeleteMapping(value="/delete/{id}")
              public void deleteRoom(@PathVariable(value="id") java.lang.Integer id)
              
              
## com.projektowanie.ojektowe.exceptions

*Wyjątki w Javie to specjalne obiekty, które poza standardowymi operacjami na obiektach możemy także rzucać za pomocą słowa kluczowego throws, co powoduje natychmiastowe przerwanie działania wątku (w najprostszym przypadku — aplikacji) oraz przejście do pierwszego napotkanego miejsca, które ten wyjątek jest w stanie obsłużyć. Nieobsłużony wyjątek uśmierca bieżący wątek.*

### Exceptions:
**EmptyReservationFilterException**

        Konstruktor: 
        
            public EmptyReservationFilterException(java.lang.String msg)
            
**NoReservationFoundException**

        Konstruktor: 
        
            public NoReservationFoundException(java.lang.String msg)
   
**ReservationEndBeforeStartException**

        Konstruktor: 
        
            public ReservationEndBeforeStartException(java.lang.String msg)
   
**RoomAlreadyReservedException**

        Konstruktor: 
        
            public RoomAlreadyReservedException(java.lang.String msg)
   
**UnableToAddReservationException**

        Konstruktor: 
        
            public UnableToAddReservationException(java.lang.String msg)
   
**UserAlreadyExistException**

        Konstruktor: 
        
            public UserAlreadyExistException(java.lang.String msg)
   
**UserDoesentExistException** 

        Konstruktor: 
        
            public UserDoesentExistException(java.lang.String msg)
 
