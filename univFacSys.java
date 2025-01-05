import java.util.*;
class member {
	private String userid;
	private String password;
	static Library lib1=new Library();
	member(String id,String pass){
		userid=id;
		password=pass;
	}
	public static void AddBook(String name , String id){
		lib1.AddBook(name,id);
	}
	int Canteen(){
		int Amount=0;
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.println("Choose your order :");
			System.out.println("\t1.Sandwich   -Rs.40");
			System.out.println("\t2.Noodles    -Rs.50");
			System.out.println("\t3.Lunch      -Rs.45");
			System.out.println("\t4.Manchuria  -Rs.60");
			System.out.println("\t5.FriedRice  -Rs.50");
			System.out.println("\t6.Tea        -Rs.05");
			System.out.println("\t7.Coffee     -Rs.07");
			System.out.println("\t8.Cooldrink  -Rs.35");
			System.out.println("\t9.Pizza      -Rs.75");
			System.out.println("\t10.Chocolate -Rs.20");
			System.out.println("\t11.Lays      -Rs.10");
			System.out.println("\tType 0 to get Bill");
			int a=in.nextInt();
			switch(a){
				case 0: return Amount;
				case 1:Amount+=40;
						System.out.println("\t\n\n--- You Purchased Sandwich---\n\n");
				break;
				case 2:Amount+=50;
						System.out.println("\n\n--- You Purchased Noodles---\n\n");
				break;
				case 3:Amount+=45;
						System.out.println("\n\n--- You Purchased Lunch---\n\n");
				break;
				case 4:Amount+=60;
						System.out.println("\n\n--- You Purchased Manchuria---\n\n");
				break;
				case 5:Amount+=50;
						System.out.println("\n\n--- You Purchased FriedRice---\n\n");
				break;
				case 6:Amount+=5;
						System.out.println("\n\n--- You Purchased Tea---\n\n");
				break;
				case 7:Amount+=7;
						System.out.println("\n\n--- You Purchased Coffee---\n\n");
				break;
				case 8:Amount+=35;
						System.out.println("\n\n--- You Purchased Cooldrink---\n\n");
				break;
				case 9:Amount+=75;
						System.out.println("\n\n--- You Purchased Pizza---\n\n");
				break;
				case 10:Amount+=20;
						System.out.println("\n\n--- You Purchased Chocolate---\n\n");
				break;
				case 11:Amount+=10;
						System.out.println("\n\n--- You Purchased Lays---\n\n");
				break;
				default: System.out.println("\n\n--------NOT VALID ITEM | RETRY-----\n");
			}
		}
	}
	void Library(){
		Scanner in=new Scanner(System.in);
		lib1.ShowAllBooks();
		while(true){
			System.out.println("\n\n----------");
			System.out.println("Enter an option:");
			System.out.println("\t1.Borrow Book");
			System.out.println("\t2.Return Book");
			System.out.println("\t3.Exit\n");
			int a=in.nextInt();
			switch(a){
				case 1:lib1.BorrowBook(userid);
				break;
				case 2:lib1.ReturnBook(userid);
				break;
				case 3:return;
				default:System.out.println("\n------WRONG OPTION | RETRY-------\n");
			}
		}
	}
	void menu(){
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.println("\n\n---------MENU---------\n\n");
			System.out.println(" 1.Canteen\n 2.Library\n 3.Exit\n");
			int a=in.nextInt();
			switch(a){
				case 1: int Amount=Canteen();
						System.out.println("Your bill="+Amount);
				break;
				case 2: Library();
				break;
				case 3: return;
				default: System.out.println("\n\n-----Wrong option , retry-----\n");
			}
		}
	}
	boolean getmember(String Id,String Password){
		if(Id.equals(userid)==true && Password.equals(password)==true)return true;
		else return false;
	}
}

class Library{
	ArrayList<Book> rack=new ArrayList<Book>();
	void AddBook(String name,String id){
		rack.add(new Book(name,id));
	}
	void ShowAllBooks(){
		Book obj1;
		System.out.println("\n\n------ALL BOOKS IN LIBRARY-----\n\n");
		for(int i=0;i<rack.size();i++){
			obj1=rack.get(i);
			System.out.print("\n"+(i+1)+".Book= "+obj1.Book_Name+"\t Book ID= "+obj1.Book_ID+"\t Book Availability= ");
			if(obj1.Book_Availability==true)System.out.print("AVAILABLE");
			else System.out.print("NOT AVAILABLE");
		}
	}
	void BorrowBook(String id){
		Scanner in=new Scanner(System.in);
		System.out.print("Enter book id=");
		String BId=in.nextLine();
		Book obj1;
		for(int i=0;i<rack.size();i++){
			obj1=rack.get(i);
			if(BId.equals(obj1.Book_ID)==true){
				if(obj1.Book_Availability==true){
					obj1.TakeBook(id);
					System.out.println("\n\n----BOOK BORROWED----\n\n");
					return;
				}
				else {
					System.out.println("\n\n-----BOOK NOT AVAILABLE------\n\n");
					return;
				}
			}
		}
		System.out.println("\n\n----BOOK NOT FOUND-----\n\n");
		return;
	}
	void ReturnBook(String id){
		Scanner in=new Scanner(System.in);
		System.out.print("Enter book id=");
		String BId=in.nextLine();
		Book obj1;
		for(int i=0;i<rack.size();i++){
			obj1=rack.get(i);
			if(BId.equals(obj1.Book_ID)==true){
				if(obj1.Book_Availability==true || obj1.Book_Taken_By.equals(id)==false){
					System.out.println("\n\n----BOOK NOT BORROWED BY THIS USER ----\n\n");
					return;
				}
				else {
					obj1.ReturnBook();
					System.out.println("\n\n-----BOOK RETURNED------\n\n");
					return;
				}
			}
		}
		System.out.println("\n\n----BOOK NOT FOUND-----\n\n");
		return;
	}
}
class Book{
	String Book_Name;
	boolean Book_Availability;
	String Book_ID;
	String Book_Taken_By;
	Book(String name,String id){
		Book_Name=name;
		Book_ID=id;
		Book_Availability=true;
	}
	void TakeBook(String id){
		Book_Availability=false;
		Book_Taken_By=id;
	}
	void ReturnBook(){
		Book_Taken_By="";
		Book_Availability=true;
	}
}



class project{
	ArrayList<member> students=new ArrayList<member>();
	ArrayList<member> teachers=new ArrayList<member>();
	void StudentLogin(String UserId,String Password){
		member obj1;
		for(int i=0;i<students.size();i++){
			obj1=students.get(i);
			if(obj1.getmember(UserId,Password)==true){
				obj1.menu();
				return;
			}
		}
		System.out.println("Wrong username or password , retry");
	}

	void TeacherLogin(String UserId,String Password){
		member obj1;
		for(int i=0;i<teachers.size();i++){
			obj1=teachers.get(i);
			if(obj1.getmember(UserId,Password)==true){
				obj1.menu();
				return;
			}
		}
		System.out.println("Wrong username or password , retry");
	}

	void login(){
		String UserId,pass;
		int a;
		Scanner in=new Scanner(System.in);
		while(true){
			System.out.println("\n\n-------LOGIN-----------\n\n");
			System.out.print("Enter \n 1.Student \n 2.Teacher \n 3.Exit \n=");
			a=in.nextInt();
			in.nextLine();
			if(a==3)return;
			System.out.println("Enter User ID:");
			UserId=in.nextLine();
			System.out.println("Enter password:");
			pass=in.nextLine();
			switch(a){
				case 1:StudentLogin(UserId,pass);
				break;
				case 2:TeacherLogin(UserId,pass);
				break;
				default:System.out.println("Wrong input , retry");
			}
		}
	}
	public static void main(String[] args) {
		project obj1=new project();
		obj1.students.add(new member("160030529","abc"));
		obj1.students.add(new member("160030","def"));
		obj1.students.add(new member("160031","gab"));
		obj1.teachers.add(new member("30001","abc"));
		obj1.teachers.add(new member("30002","def"));
		obj1.teachers.add(new member("30003","gab"));
		member.AddBook("Complete Java Reference","X01");
		member.AddBook("Batman the Killer joker","X02");
		member.AddBook("Original Sin","X03");
		member.AddBook("Harry Potter : Deathly Hallows","X04");
		obj1.login();
		System.out.println("------------THANK YOU FOR VISITING------------");
	}
}

