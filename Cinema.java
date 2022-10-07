package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {
    public static void cinemaSeats() {
        byte rows;
        rows = 7;
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        // visualize sitting environments
        for (int r=1; r<rows+1; r++) {
            System.out.println(r + " S S S S S S S S");
        }
    }
        // ticket sold!
    public static void sold() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rowNum = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatNum = input.nextInt();
        int totalSeat = rowNum * seatNum;
        int totalMoney;
        if (totalSeat < 60) {
            totalMoney = totalSeat * 10;
        } else {
//            int frontSeats = (rowNum/2)*seatNum;
//            int backSeats = (rowNum%2 == 0)? (rowNum/2)*seatNum:(rowNum/2+1)*seatNum;
//            totalMoney = 10*frontSeats + 8*backSeats;
            int frontRow = (rowNum / 2);
            int backRow = (rowNum - frontRow);
            totalMoney = (10*frontRow + 8*backRow) * seatNum;
        }
        System.out.println("Total income:");
        System.out.println("$" + totalMoney);

    }
    public static void checkTickets() {
//        SEATS ARRANGEMENT
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int columns = input.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("Cinema: ");
        char [][] cinema = new char[rows][columns];
        for (char[] c: cinema) {
            Arrays.fill(c, 'S');
        }
        // print the first row
        System.out.print(" ");
        for (int i=0; i<columns; i++) {
            System.out.printf(" %d", i+1);
        }
        // print the row number + S
        System.out.println();
        for (int i=0; i<rows; i++) {
            System.out.print(i+1);
            for (int j=0;j<columns;j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }

        // CHECK PRICE TICKET
        System.out.println("Enter a row number:");
        int rowNum = input.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seatNum = input.nextInt();
        double frontRow = (int) Math.floor(rows/2.0);

        int totalSeat = rows*columns;
//        if (totalSeat <= 60) {
//            ticketPrice = 10;
//        } else {
//            ticketPrice = (rowNum <= frontRow)? 10: 8;
//        }
        int ticketPrice =  (totalSeat < 60 || rowNum <= frontRow)? 10: 8;
        System.out.printf("Ticket price: $%d", ticketPrice);

        // SEAT ARRANGEMENT AGAIN
        // print the first row
        System.out.println();
        System.out.println();
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i=0; i<columns; i++) {
            System.out.printf(" %d", i+1);
        }
        // print the row number + S
        System.out.println();
        for (int i=0; i<rows; i++) {
            System.out.print(i+1);
            for (int j=0;j<columns;j++) {
                cinema[rowNum-1][seatNum-1] = 'B';
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }


    }
    public static void menuPlease() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seats = input.nextInt();

        // SHOW THE SEAT

        char[][] cinema = new char[rows][seats];
        for (char[] row: cinema) {
            Arrays.fill(row, 'S');
        }
        while(true) {
            // Inform what to do next:
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");
            int request = input.nextInt();

            if (request==0) {
                break;
            }
            if (request==1) {
                System.out.println("Cinema: ");
                showTheSeats(rows, seats, cinema);
            }
            if (request==2) {
                System.out.println("Enter a row number: ");
                int rowNum = input.nextInt();

                System.out.println("Enter a seat number in that row: ");
                int seatNum = input.nextInt();

                buyATickets(rows, seats, rowNum, seatNum, cinema);
            }
        }

    }
    public static void errors() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = input.nextInt();
        char[][] cinema = new char[rows][seats];
        for (char[] row : cinema) {
            Arrays.fill(row, 'S');
        }
        int totalSeat = rows * seats;
        double frontRow = (int) rows / 2;
        double backRow = (int) (rows - frontRow);
        int purchaseTicket = 0;
        int currentIncome = 0;
        int totalIncome = (totalSeat < 60) ? totalSeat * 10 : (int) ((10 * frontRow + 8 * backRow) * seats);
        
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int request = input.nextInt();
            
            if (request == 0) {
                break;
            }

            if (request == 1) {
                System.out.print("Cinema: ");
                showTheSeats(rows, seats, cinema);
            }
            if (request == 2) {
                while (true) {
                    System.out.println("Enter a row number:");
                    int rowNum = input.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seatNum = input.nextInt();
                    // ticket price
                    boolean overload = ((rowNum < 1) || (rowNum > rows)) || ((seatNum < 1) || (seatNum > seats));
                    if (overload) {
                        System.out.println("Wrong input!");
                    } else if (cinema[rowNum - 1][seatNum - 1] == 'B') {
                        System.out.println("That ticket has already been purchased!");

                    } else {
                        cinema[rowNum - 1][seatNum - 1] = 'B';
                        int ticketPrice = ((totalSeat <= 60) || (rowNum <= frontRow)) ? 10 : 8;
                        System.out.println("Ticket price: $" + ticketPrice);
                        purchaseTicket++;
                        currentIncome += ticketPrice;
                        break;
                    }
                }
                // rowNum and seatNum input


            }
            if (request == 3) {
                double percentage = (100.00 * purchaseTicket) / totalSeat;
                System.out.println("Number of purchased tickets: " + purchaseTicket);
                System.out.printf("Percentage: %.2f%%\n", percentage);
                System.out.println("Current income: $" + currentIncome);
                System.out.println("Total income: $" + totalIncome);
            }

        }

    }

    private static void showTheSeats(int r, int s, char[][] cnm) {
        System.out.println();
        // first row: number of seats
        // number of each rows + 'S';
        System.out.print(" ");
        for (int i=0; i<s; i++) {
            System.out.print(" " + (i+1));
        }
        System.out.println();
        for (int i=0; i<r; i++) {
            System.out.print(i+1);
            for (int j=0; j<s; j++) {
                System.out.print(" " + cnm[i][j]);
            }
            System.out.println();
        }
    };
    private static void buyATickets(int r, int s, int rs, int ss, char[][] cnm) {
        int totalRow = r * s;
        double frontRow = (int) (r/2);
        int ticketPrice = ((totalRow <= 60) || (rs <= frontRow))? 10: 8;

        System.out.printf("Ticket price: $%d", ticketPrice);
        System.out.println();
        System.out.println("Cinema: ");
        System.out.print(" ");

        for (int i=0; i<s; i++) {
            System.out.printf(" %d", i+1);
        }
        System.out.println();
        for (int i=0; i<r; i++) {
            System.out.print(i+1);
            for(int j=0; j<s; j++) {
                cnm[rs-1][ss-1] = 'B';
                System.out.print(" "+cnm[i][j]);
            }
            System.out.println();
        }

    }

    public static void main (String[]args){
        errors();
    }
}