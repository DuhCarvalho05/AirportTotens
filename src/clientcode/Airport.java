package clientcode;

import data.airport.model.FlightData;
import data.airport.model.FlightDataCollection;
import data.airport.states.Arriving;
import data.airport.states.Boarding;
import data.airport.states.TakingOff;
import data.airport.states.TookOff;
import data.totem.Totem;
import data.totem.TotemClass;

import java.util.Scanner;

public class Airport {
    private static Scanner scanner = new Scanner(System.in);
    private FlightDataCollection collection = new FlightDataCollection();

    public void run() {
        Totem totemBoarding = new TotemClass();
        Totem totemTookOff = new TotemClass();
        Totem totemBoardingTookOff = new TotemClass();
        totemBoarding.init(100, 100,"SALA DE EMBARQUE - ARRIVING", Arriving.getInstance());
        totemTookOff.init(500, 100,"SALA DE DESEMBARQUE - TAKINGOFF", TakingOff.getInstance());
        totemBoardingTookOff.init(900, 100,"EMBARQUE E DESEMBARQUE - ARRIVING/TAKINGOFF",Arriving.getInstance() , TakingOff.getInstance());

        collection.register(totemBoarding);
        collection.register(totemTookOff);
        collection.register(totemBoardingTookOff);



        int option;
        do{
            System.out.println("1 - Novo voo");
            System.out.println("2 - Alterar estado");
            System.out.println("3 - Lista de Voos");
            System.out.println("0 - Encerrar");
            System.out.println("Opção: ");
            option = scanner.nextInt();

            switch (option){
                case 1:
                    collection.insertFlight(readFlight());
                    System.out.println("Voo adicionado.");
                    break;

                case 2:
                    updateFlight();
                    break;

                case 3:
                    for (FlightData fligth : collection.allFlights()){
                        System.out.println(fligth);
                    }
                    break;

                default:
                    totemBoarding.exit();
                    totemTookOff.exit();
                    totemBoardingTookOff.exit();
                    System.out.println("Aeroporto fechado!");
                    break;
            }
        }while (option != 0);
    }

    private void updateFlight(){
        Long number;
        System.out.println("Número do voo: ");
        number = scanner.nextLong();
        collection.updateFlight(number);
        System.out.println("Estado do voo editado.");
    }

    private FlightData readFlight(){
        Long number;
        String company;
        String time;

        System.out.println("Número do voo.: ");
        number = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Companhia.....: ");
        company = scanner.nextLine();
        System.out.println("Horário.......: ");
        time = scanner.nextLine();

        return new FlightData(number, company, time);
    }

    public static void main(String[] args) {
        new Airport().run();
    }
}
