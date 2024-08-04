package data.totem;

import data.airport.listener.FlightDataObserver;
import data.airport.states.State;

public interface Totem extends FlightDataObserver {

    void init(Integer x, Integer y,String title,State... statesObserved);
    void exit();

}
