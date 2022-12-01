import java.util.ArrayList;
import java.util.Collections;

public class CarLot {
	private ArrayList<Car> inventory = new ArrayList<Car>();
	
	public CarLot() { 
		this(100); 
	}
	
	public CarLot(int capacity) {
		inventory.ensureCapacity(capacity);
	}
	
	public void addCar(String id, int mileage, int mpg, double cost, double salesPrice) {
        Car car = new Car(id, mileage, mpg, cost, salesPrice);
		inventory.add(car);
	}
	
	public Car[] getInventory() {
		Car[] allCars = new Car[inventory.size()];
		for (int i = 0; i < inventory.size(); i++) {
			allCars[i] = inventory.get(i);
		}
		return allCars;
	}
	
	public Car findCarByIdentifier(String identifier) {
		for (int x = 0; x < inventory.size(); x++) {
			Car aCar = inventory.get(x);
			if (aCar.getId().equals(identifier)) {
				return aCar;
			}
		}
		return null;
	}
	
	public void sellCar(String identifier, double priceSold) throws IllegalArgumentException {
		Car aCar = this.findCarByIdentifier(identifier);
		if (aCar != null) {
			aCar.sellCar(priceSold);
		} else {
			throw new IllegalArgumentException("No car with identifier " + identifier);
		}
	}
	
	public ArrayList<Car> getCarsInOrderOfEntry() { return this.inventory; }
	
	public ArrayList<Car> getCarsSortedByMPG() {
		ArrayList<Car> allCars = new ArrayList<>();
		for (int i = 0; i < inventory.size(); i++) {
			allCars.add(inventory.get(i));
		}
		Collections.sort(allCars, (Car c1, Car c2) -> c2.compareMPG(c1));
		return allCars;
	}
	
	public Car getCarWithBestMPG() {
		Car rtn = null;
		int bestMpg = -1;
		for (int i = 0; i < inventory.size(); i++) {
			Car aCar = inventory.get(i);
			if (aCar.getMpg() > bestMpg) {
				bestMpg = aCar.getMpg();
				rtn = aCar;
			}
		}
		return rtn;
	}
	
	public Car getCarWithHighestMileage() {
		Car rtn = null;
		int highestMileage = -1;
		for (int i = 0; i < inventory.size(); i++) {
			Car aCar = inventory.get(i);
			if (aCar.getMileage() > highestMileage) {
				highestMileage = aCar.getMileage();
				rtn = aCar;
			}
		}
		return rtn;
	}
	
	public double getAverageMpg() {
		double totalMpg = 0D;
		for (int i = 0; i < inventory.size(); i++) {
			Car aCar = inventory.get(i);
			totalMpg += aCar.getMpg();
		}
		return totalMpg / inventory.size();
	}
	
	public double getTotalProfit() {
		double profit = 0D;
		for (int i = 0; i < inventory.size(); i++) {
			Car aCar = inventory.get(i);
			profit += (aCar.isSold()?aCar.getProfit():0);
		}
		return profit;
	}
	
}
