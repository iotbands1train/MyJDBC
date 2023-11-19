package com.tools;

import java.sql.*; 
import java.util.*;  

import com.model.Person;

public class MyJDBC {

	private static String currentTable = "person";
	private static String currentDatabase = "persondb";
	private static Connection con;
	private static PreparedStatement ps;
	private Person p;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String USER = "root";
	private final static String PASSWORD = "Sun9password#";
	private final static String URL = "jdbc:mysql://localhost:3306/" + currentDatabase; 
	private static int count;

	public static int update(Person u) {
		int status = 0;
		try {
			con();
			PreparedStatement ps = con.prepareStatement(
					"update " + currentTable + " set fname=?,lname=?,street=?,city=?,state=?,zip=?,phone=? where id=?");

			ps.setString(1, u.getFname());
			ps.setString(2, u.getLname());
			ps.setString(3, u.getStreet());
			ps.setString(4, u.getCity());
			ps.setString(5, u.getState());
			ps.setString(6, u.getZip());
			ps.setString(7, u.getPhone());
			ps.setLong(8, u.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;

	} 
	


	public static List<Person> getAllRecordsFById(Long id) {
		List<Person> list = new ArrayList<>();
		id=id>1L?id:1;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + " where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}


	public static Person getRecordById(Long id) {
		Person u = null;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + " where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return u;
	}

	public static List<Person> getAllRecordsBatch(int x) {
		 
		List<Person> list = new ArrayList<Person>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		List<Person> randList = listBatch(x);
		list.addAll(randList);

		return list;
	}
	
	public static int count() {
		return count;
	}

	public static List<Person> getAllRecordsBatch(String xx) {
		int x = 0;
		if (xx == null)
			x = 1;
		count=x;  
		List<Person> list = new ArrayList<Person>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		List<Person> randList = listBatch(x);
		list.addAll(randList);

		return list;
	}

	public static List<Person> getAllRecordsClear() {
		List<Person> list = new ArrayList<Person>();

		try {

			List<Person> randList = listBatch();
			list.addAll(randList);

			Connection con = getConnection();
			Statement stm = con.createStatement();
			stm.addBatch("TRUNCATE  " + currentTable + "");
			randList.clear();
			randList.add(new Person(2L,"NoName","NoName2","NoStreet","NoCity","NoState","NoZip","NoPhone"));
			for (Iterator iterator = randList.iterator(); iterator.hasNext();) {
				Person person = (Person) iterator.next();
				String query = stringPerson(person);
				stm.addBatch(query);
			}
			int count[] = stm.executeBatch();
			con.close();
			con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public static List<Person> getAllRecordsBatch() {
		List<Person> list = new ArrayList<Person>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		List<Person> randList = listBatch();
		list.addAll(randList);

		return list;
	}

	private static List<Person> listBatch() {
		List<Person> list = null;

		try {
			con();
			Statement statement = con.createStatement();
			list = getRandPerson();
			String query = null;
			for (int i = 0; i < list.size(); i++) {
				Person person = list.get(i);
				query = stringPerson(person);
				statement.addBatch(query);
			}
			int count[] = statement.executeBatch();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private static List<Person> listBatch(int x) {
		List<Person> list = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection con = null;
		Statement statement = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			list = getRandPerson(x);
			String query = null;
			for (int i = 0; i < list.size(); i++) {
				Person person = list.get(i);
				query = stringPerson(person);
				statement.addBatch(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int count[] = null;
		try {
			count = statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#ofpersons stored => " + count.length);

		return list;
	}

	// addBatch("insert into T values (100, 'Smith')");
	private static String stringPerson(Person person) {
		String query = "insert into " + currentTable + " ";
		query += "values (" + person.getId();
		query += ", '" + person.getFname() + "', '" + person.getLname();
		query += "', '" + person.getStreet() + "', '" + person.getCity();
		query += "', '" + person.getState() + "', '" + person.getZip();
		query += "', '" + person.getPhone();
		query += "') ";
		return query;
	}

	private static List<Person> getRandPerson() {
		int next20 = 20;
		List<Person> people = new ArrayList<>();
		for (int i = 0; i < next20; i++) {
			Person person = randomPerson();
			people.add(person);
		}
		return people;
	}

	private static List<Person> getRandPerson(int next20) {
		List<Person> people = new ArrayList<>();
		for (int i = 0; i < next20; i++) {
			Person person = randomPerson();
			people.add(person);
		}
		return people;
	}

	private static Person randomPerson() {
		Long id = new Random().nextLong(333388821, 339988821);
		String fname = randomFirst();
		String lname = randomLast();
		String street = randomStret();
		String city = randomCity();
		String state = "Tx";
		String zip = "77" + new Random().nextInt(199, 998) + "";
		String phone = new Random().nextInt(199, 998) + "-" + new Random().nextInt(199, 998) + "-"
				+ new Random().nextInt(1991, 9989);
		Person person = new Person(id, fname, lname, street, city, state, zip, phone);
		return person;
	}

	private static String randomFirst() {
		String[] n = { "Sue", "Mike", "Nick", "Julie", "Janice", "Josh", "Fred" };
		return n[new Random().nextInt(0, n.length)];
	}

	private static String randomLast() {
		String[] n = { "William", "Stone", "Yale", "Brown", "Gomez", "Reyes", "Zho" };
		return n[new Random().nextInt(0, n.length)];
	}

	private static String randomStret() {
		String[] n = { "Main St.", "Wall Blvd.", "West Ln.", "Dulles Rd.", "Griggs Rd.", "Lone St.", "2nd St." };
		return new Random().nextInt(1254, 9562) + " " + n[new Random().nextInt(0, n.length)];
	}

	private static String randomCity() {
		String[] n = { "Houston", "SugaLand", "Bellaire", "West U", "SoHo", "Kemah", "Conroe" };
		return n[new Random().nextInt(0, n.length)];
	}
	public static List<Person> getAllRecordsByID_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareById = 
				(Person o1, Person o2) -> o1.getId().compareTo( o2.getId() );
		Collections.sort(list,compareById); 
		return list;
	}

	public static List<Person> getAllRecordsByLName_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getLname().compareTo( o2.getLname() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}


	public static List<Person> getAllRecordsByLName_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getLname().compareTo( o2.getLname() );
		Collections.sort(list,compareByF); 
		return list;
	}

	public static List<Person> getAllRecordsByState_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getState().compareTo( o2.getState() );
		Collections.sort(list,compareByF); 
		return list;
	}

	public static List<Person> getAllRecordsByState_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getState().compareTo( o2.getState() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}



	public static List<Person> getAllRecordsByStreet_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getStreet().compareTo( o2.getStreet() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}




	public static List<Person> getAllRecordsByZ_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getZip().compareTo( o2.getZip() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}




	public static List<Person> getAllRecordsByZ_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getZip().compareTo( o2.getZip() );
		Collections.sort(list,compareByF); 
		return list;
	}


	public static List<Person> getAllRecordsByCity_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getCity().compareTo( o2.getCity() );
		Collections.sort(list,compareByF); 
		return list;
	}


	public static List<Person> getAllRecordsByCity_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getCity().compareTo( o2.getCity() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}


	public static List<Person> getAllRecordsByPhone_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getPhone().compareTo( o2.getPhone() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}



	public static List<Person> getAllRecordsByPhone_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getPhone().compareTo( o2.getPhone() );
		Collections.sort(list,compareByF); 
		return list;
	}

	public static List<Person> getAllRecordsByFName_Asc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getFname().compareTo( o2.getFname() );
		Collections.sort(list,compareByF); 
		return list;
	}


	public static List<Person> getAllRecordsByFName_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareByF = 
				(Person o1, Person o2) -> o1.getFname().compareTo( o2.getFname() );
		Collections.sort(list,compareByF.reversed()); 
		return list;
	}
	public static List<Person> getAllRecordsByID_Dsc() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		Comparator<Person> compareById = 
				(Person o1, Person o2) -> o1.getId().compareTo( o2.getId() );
		Collections.sort(list,compareById.reversed()); 
		return list;
	}
 

	public static List<Person> getAllRecordsF() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public static List<Person> getAllRecordsR() {
		List<Person> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from " + currentTable);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person u = new Person();
				u.setId(rs.getLong("id"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setStreet(rs.getString("street"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setZip(rs.getString("zip"));
				u.setPhone(rs.getString("phone"));
				list.add(u);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		list = reverse(list);
		return list;
	}

	private static List<Person> reverse(List<Person> list) {

		Person[] arr = new Person[list.size()];
		int k = 0;

		for (int i = list.size() - 1; i > -1; i--) {
			Person person = list.get(i);
			arr[k++] = person;
		}
		List<Person> alst = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			Person person = arr[i];
			alst.add(person);
		}

		return alst;
	}

	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/persondb", "root", "Sun9password#");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int delete(Person u) {
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("delete from " + currentTable + " where id=?");
			ps.setLong(1, u.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	private static void con() {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected");
	}

	public MyJDBC(Person p) {
		loadDriver();
		con();
		psInsert();
		insertTable(p);
	}

	public static int register(Person p) {
		int status = 0;
		try {
			status = insertTable(p);
		} catch (Exception e) {
		}

		return status;
	}

	public MyJDBC() {
		loadDriver();
		con();

	}

	private void loadDriver() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("load driver");

	}

	private static int insertTable(Person person) {
		psInsert();

		try {
			ps.setLong(1, person.getId());
			ps.setString(2, person.getFname());
			ps.setString(3, person.getLname());
			ps.setString(4, person.getStreet());
			ps.setString(5, person.getCity());
			ps.setString(6, person.getState());
			ps.setString(7, person.getZip());
			ps.setString(8, person.getPhone());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private static void psInsert() {
		con();
		String q = "insert into " + currentDatabase + "." + currentTable;
		q += " ( ";
		q += " id, fname, lname, street, city, state, zip, phone  ";
		q += " ) values ( ";
		q += " ?, ?, ?, ?, ?, ?, ?, ?  ";
		q += " ) ";
		try {
			ps = con.prepareStatement(q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}