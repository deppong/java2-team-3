import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	public static void main(String[] args) throws SQLException{
		String url = "jdbc:mysql://localhost:3306/CarLot";
		String uname = "scott";
		String password = "tiger";
		String selectAll = "select * from CarLot;";
		String insertValues = "INSERT into CarLot VALUES ('test1', 50000, 20, 10000.0, 15000.0);";
		
		CarLot lot = new CarLot();
		lot.addCar("test1", 10000, 30, 12500.0D, 17500.0D);
		lot.addCar("test2", 10000, 10, 10000D, 10000D);
		lot.addCar("test3", 12000, 20, 12000D, 12000D);

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, uname, password);
			Statement statement = con.createStatement();
			statement.executeUpdate("delete from carlot;");
			for (Car car: lot.getInventory()) {
				insertValues = "INSERT into CarLot VALUES ('"+ car.getId() +"', "+ car.getMileage() +","+ car.getMpg()+", " + car.getCost() + "," + car.getSalesPrice() + ");";
				statement.executeUpdate(insertValues);
			}
			
			
			ResultSet result = statement.executeQuery(selectAll);
			while(result.next()) {
				String carLotData = "";
				for (int i = 1; i <= 5; i++) {
					switch(i) {
					case 1:
						carLotData += "ID: " + result.getString(i) + ", ";
						break;
					case 2:
						carLotData += "Mileage: " + result.getString(i) + ", ";
						break;
					case 3:
						carLotData += "MPG: " + result.getString(i) + ", ";
						break;
					case 4:
						carLotData += "Cost: " + result.getString(i) + ", ";
						break;
					case 5:
						carLotData += "Sales Price: " + result.getString(i) + ", ";
						break;
					default:
						carLotData += result.getString(i) + ", ";
					}
					
					
				}
				System.out.println(carLotData);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
