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
              
              
## com.projektowanie.ojektowe.model

*Wyjątki w Javie to specjalne obiekty, które poza standardowymi operacjami na obiektach możemy także rzucać za pomocą słowa kluczowego throws, co powoduje natychmiastowe przerwanie działania wątku (w najprostszym przypadku — aplikacji) oraz przejście do pierwszego napotkanego miejsca, które ten wyjątek jest w stanie obsłużyć. Nieobsłużony wyjątek uśmierca bieżący wątek.*

### Classes:
**Reservation**

	public class Reservation {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Integer id;
		//@NotEmpty
		private Integer room;
		//@NotEmpty
		private Integer bodies;
		//@NotBlank
		//owner = firstName+secondName;
		private Integer ownerId;
		private Double price;
		@Future
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date start;
		@Future
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date end;
	}
           
**Room**

	public class Room {
		@Id
		private Integer number;
		private Double rating;
		private Integer price;
		private Boolean wiFi;
		private Boolean conditioning;
		private Boolean petFriendly;
		private Double roomClass;
	}

## com.projektowanie.ojektowe.hotel.models.UserModels

### Classes:
**LoginUser**

	public class LoginUser {
		private String email;
		private String password;
	}
           
**User**

	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private int id;
		private String type;
		private String firstName;
		private String lastName;
		//@NotBlank
		@Email
		private String email;
		//@NotBlank
		private String password;
	}
	    
## com.projektowanie.ojektowe.hotel.models.utils

### Classes:
**ReservationFilter**

	public class ReservationFilter {
		private Integer ownerId;
	}
           
**RoomFilter**

	public class RoomFilter {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date start;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date end;
		private Double rating;
		private Integer startPrice;
		private Integer endPrice;
		private Boolean wiFi;
		private Boolean conditioning;
		private Boolean petFriendly;
		private Double roomClass;
		private Integer group;
	}
	    

### Enums:	
**DISCOUNT_PERIODS**

	public enum DISCOUNT_PERIODS {
		AUTUMN(1, 9, 1, 3, 5);

		private Integer startDay;
		private Integer startMonth;
		private Integer endDay;
		private Integer endMonth;
		private Integer discountSize;

		DISCOUNT_PERIODS(Integer startDay, Integer startMonth, Integer endDay, Integer endMonth, Integer discountSize) {
			this.startDay = startDay;
			this.startMonth = startMonth;
			this.endDay = endDay;
			this.endMonth = endMonth;
			this.discountSize = discountSize;
		}

		public Integer getDiscountSize() {
			return discountSize;
		}

		public Integer getEndDay() {
			return endDay;
		}

		public Integer getEndMonth() {
			return endMonth;
		}

		public Integer getStartDay() {
			return startDay;
		}

		public Integer getStartMonth() {
			return startMonth;
		}
	}
 
