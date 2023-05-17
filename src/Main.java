import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = -1;
        int option2 = -1;
        String city1 = "";
        String city2 = "";
        Scanner scan = new Scanner(System.in);

        Filereader filereader = new Filereader();
        WeightedDirectionalGraph graph = filereader.generateGraphFromFile();

        while(option != 4){
            showMenu();
            option = scan.nextInt();

            scan.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter the name of the initial city:");
                    city1 = scan.nextLine();
                    System.out.println("Enter the name of the destiny city:");
                    city2 = scan.nextLine();

                    System.out.println(city1 + city2);

                    break;

                case 2:
                    System.out.println("The city at the center is: \n");

                    break;

                case 3:

                    option2 = showChangeMenu(scan);
                    scan.nextLine();

                    switch (option2) {
                        case 1:

                            System.out.println("Enter the name of the initial city:");
                            city1 = scan.nextLine();
                            System.out.println("Enter the name of the destiny city:");
                            city2 = scan.nextLine();
                            System.out.println(city1 + city2);

                            graph.removeConnection(city1,city2);
                            break;

                        case 2:
                            System.out.println("Enter the name of the initial city:");
                            city1 = scan.nextLine();
                            System.out.println("Enter the name of the destiny city:");
                            city2 = scan.nextLine();
                            System.out.println("What is the normal travel time?");
                            int nTime = scan.nextInt();
                            System.out.println("What is the rain travel time?");
                            int rTime = scan.nextInt();
                            System.out.println("What is the snow travel time?");
                            int snTime = scan.nextInt();
                            System.out.println("What is the storm travel time?");
                            int stTime = scan.nextInt();

                            // Print the entered variables
                            System.out.println("Entered Variables:");
                            System.out.println("City 1: " + city1);
                            System.out.println("City 2: " + city2);
                            System.out.println("Normal Travel Time: " + nTime);
                            System.out.println("Rain Travel Time: " + rTime);
                            System.out.println("Snow Travel Time: " + snTime);
                            System.out.println("Storm Travel Time: " + stTime);

                            graph.addConnection(city1,city2,nTime,rTime,snTime,stTime);
                            break;

                        case 3:
                            System.out.println("Enter the name of the first city:");
                            city1 = scan.nextLine();
                            System.out.println("Enter the name of the second city:");
                            city2 = scan.nextLine();
                            System.out.println("What is the new weather condition (type the condition without the number):\n" +
                                                "|1| Normal\n" +
                                                "|2| Rain\n" +
                                                "|3| Snow\n" +
                                                "|4| Storm");
                            String climateCond = scan.nextLine();
                            System.out.println(city1 + city2 + "  " + climateCond);
                            graph.changeConnectionWight(city1,city2,climateCond);
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }

                    break;
                case 4:

                    System.out.println("Have a nice day!");
                    break;
                default:

                    System.out.println("Invalid option");
                    break;
            }
        }

    }
    public static void showMenu(){
        System.out.println("What do you want to do:\n" +
                            "|1| Find the shortest route between 2 cities\n" +
                            "|2| Find the city in the center (Center of the graph)\n" +
                            "|3| Make a change in the graph\n" +
                            "|4| Exit");
    }
    public static int showChangeMenu(Scanner scan){
        System.out.println("Which change do you wish to make:\n" +
                "|1| There is an obstruction between 2 cities (remove connection between 2 cities\n" +
                "|2| A new road was made between 2 cities (Make a connection between 2 cities)\n" +
                "|3| Update the climate between 2 cities (change the wight of the connection)");

        return scan.nextInt();
    }
}
