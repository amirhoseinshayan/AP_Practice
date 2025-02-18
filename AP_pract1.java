import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Command {
    void execute(String command);
}

class CommandProcessor {
    private Map<String, Command> commands = new HashMap<>();
    private Manager manager;

    public CommandProcessor(Manager manager) {
        this.manager = manager;
        commands.put("add_house", new AddHouseCommand(manager));
        commands.put("remove_house", new RemoveHouseCommand(manager));
        commands.put("get_houses", new GetHousesCommand(manager));
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");
        Command cmd = commands.get(commandParts[0]);

        if (cmd != null) {
            cmd.execute(command);
            System.out.flush();
        } else {
            System.out.println("Unknown command");
            System.out.flush();
        }
    }
}

class AddHouseCommand implements Command {
    private Manager manager;

    public AddHouseCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String command) {
        String houseRegex = "-(name|type|status|price|area|latitude|longitude|desc)=\"([^\"]+)\"|-(name|type|status|price|area|latitude|longitude|desc)=([^\s]+)";
        Pattern pattern = Pattern.compile(houseRegex);
        Matcher matcher = pattern.matcher(command);

        String houseName = null, houseType = null, houseStatus = null, houseDescription = null;
        int housePrice = 0, houseArea = 0;
        double houseLatitude = 0, houseLongitude = 0;

        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                switch (matcher.group(1)) {
                    case "type":
                        houseType = matcher.group(2);
                        break;
                    case "status":
                        houseStatus = matcher.group(2);
                        break;
                    case "desc":
                        houseDescription = matcher.group(2);
                        break;
                    case "name":
                        houseName = matcher.group(2);
                        break;
                }
            } else if (matcher.group(3) != null && matcher.group(4) != null) {
                switch (matcher.group(3)) {
                    case "price":
                        housePrice = Integer.parseInt(matcher.group(4));
                        break;
                    case "area":
                        houseArea = Integer.parseInt(matcher.group(4));
                        break;
                    case "latitude":
                        houseLatitude = Double.parseDouble(matcher.group(4));
                        break;
                    case "longitude":
                        houseLongitude = Double.parseDouble(matcher.group(4));
                        break;
                }
            }
        }

        if (!manager.checkHouse(houseName)) {
            House newHouse = new House(houseName, houseType, houseStatus, housePrice, houseArea, houseLatitude, houseLongitude, houseDescription);
            manager.addHouse(newHouse);
            System.out.println("house added successfully");
            System.out.flush();
        } else {
            System.out.println("invalid title");
            System.out.flush();
        }
    }
}

class RemoveHouseCommand implements Command {
    private Manager manager;

    public RemoveHouseCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String command) {
        String removeHouseRegex = "-name=\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(removeHouseRegex);
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String houseName = matcher.group(1);
            if (houseName != null && !houseName.trim().isEmpty()) {
                manager.removeHouse(houseName);
            } else {
                System.out.println("invalid title");
                System.out.flush();
            }
        } else {
            System.out.println("invalid title");
            System.out.flush();
        }
    }
}

class GetHousesCommand implements Command {
    private Manager manager;

    public GetHousesCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String command) {
        String houseSearchRegex = "-(type|status|min_price|max_price|min_area|max_area|latitude|longitude)=\"([^\"]+)\"|-(type|status|min_price|max_price|min_area|max_area|latitude|longitude)=([^\s]+)";
        Pattern pattern = Pattern.compile(houseSearchRegex);
        Matcher matcher = pattern.matcher(command);

        String houseType = null, houseStatus = null;
        int minPrice = 0, maxPrice = 1000000000;
        int minArea = 0, maxArea = 1000000000;
        double latitude = 0, longitude = 0;

        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                switch (matcher.group(1)) {
                    case "type":
                        houseType = matcher.group(2);
                        break;
                    case "status":
                        houseStatus = matcher.group(2);
                        break;
                    case "min_price":
                        minPrice = Integer.parseInt(matcher.group(2));
                        break;
                    case "max_price":
                        maxPrice = Integer.parseInt(matcher.group(2));
                        break;
                    case "min_area":
                        minArea = Integer.parseInt(matcher.group(2));
                        break;
                    case "max_area":
                        maxArea = Integer.parseInt(matcher.group(2));
                        break;
                    case "latitude":
                        latitude = Double.parseDouble(matcher.group(2));
                        break;
                    case "longitude":
                        longitude = Double.parseDouble(matcher.group(2));
                        break;
                }
            } else if (matcher.group(3) != null && matcher.group(4) != null) {
                switch (matcher.group(3)) {
                    case "type":
                        houseType = matcher.group(4);
                        break;
                    case "status":
                        houseStatus = matcher.group(4);
                        break;
                    case "min_price":
                        minPrice = Integer.parseInt(matcher.group(4));
                        break;
                    case "max_price":
                        maxPrice = Integer.parseInt(matcher.group(4));
                        break;
                    case "min_area":
                        minArea = Integer.parseInt(matcher.group(4));
                        break;
                    case "max_area":
                        maxArea = Integer.parseInt(matcher.group(4));
                        break;
                    case "latitude":
                        latitude = Double.parseDouble(matcher.group(4));
                        break;
                    case "longitude":
                        longitude = Double.parseDouble(matcher.group(4));
                        break;
                }
            }
        }

        manager.getHouse(houseType, houseStatus, minPrice, maxPrice, minArea, maxArea, latitude, longitude);
    }
}

class House {
    private String name;
    private String type;
    private String status;
    private int price;
    private int area;
    private double latitude;
    private double longitude;
    private String description;

    public House(String name, String type, String status, int price, int area, double latitude, double longitude, String description) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    public int getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
}

class Manager {
    private List<House> housesList = new ArrayList<>();
    private List<House> foundHouses = new ArrayList<>();

    public boolean checkHouse(String houseName) {
        for (House house : housesList) {
            if (house.getName().equals(houseName)) {
                return true;
            }
        }
        return false;
    }

    public void addHouse(House house) {
        housesList.add(house);
    }

    public void removeHouse(String houseName) {
        if (houseName == null || houseName.trim().isEmpty()) {
            System.out.println("invalid title");
            System.out.flush();
            return;
        }

        boolean found = false;
        House houseToRemove = null;

        for (House house : housesList) {
            if (house != null && house.getName() != null &&
                    house.getName().trim().equals(houseName.trim())) {
                houseToRemove = house;
                found = true;
                break;
            }
        }

        if (found && houseToRemove != null) {
            housesList.remove(houseToRemove);
            foundHouses.remove(houseToRemove);
            System.out.println("house removed successfully");
            System.out.flush();
        } else {
            System.out.println("invalid title");
            System.out.flush();
        }
    }

    public void getHouse(String type, String status, int minPrice, int maxPrice, int minArea, int maxArea, double latitude, double longitude) {
        foundHouses.clear();

        for (House house : housesList) {
            if ((house.getType().equals(type) || type == null) && (house.getStatus().equals(status) || status == null)) {
                if (house.getPrice() >= minPrice && house.getPrice() <= maxPrice) {
                    if (house.getArea() >= minArea && house.getArea() <= maxArea) {
                        foundHouses.add(house);
                    }
                }
            }
        }

        if (foundHouses.isEmpty()) {
            System.out.println("no house found!");
            System.out.flush();
        } else {
            foundHouses.sort((house1, house2) -> Double.compare(distanceFromPoint(house1.getLatitude(), house1.getLongitude(), latitude, longitude),
                    distanceFromPoint(house2.getLatitude(), house2.getLongitude(), latitude, longitude)));
            for (House house : foundHouses) {
                System.out.print(house.getName() + " ");
            }
            System.out.println();
            System.out.flush();
        }
    }

    private double distanceFromPoint(double latitude1, double longitude1, double latitude2, double longitude2) {
        double distanceLat = latitude2 - latitude1;
        double distanceLong = longitude2 - longitude1;
        return Math.sqrt(distanceLat * distanceLat + distanceLong * distanceLong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Manager manager = new Manager();
        CommandProcessor commandProcessor = new CommandProcessor(manager);

        int n = input.nextInt();
        input.nextLine(); // Consuming the newline character

        for (int i = 0; i < n; i++) {
            String command = input.nextLine();
            commandProcessor.processCommand(command);
        }
    }
}