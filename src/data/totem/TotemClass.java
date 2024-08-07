package data.totem;

import data.airport.model.FlightData;
import data.airport.states.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class TotemClass implements Totem {
    private Collection<FlightData> flights;
    private Collection<State> states;
    private JFrame frame;
    private JPanel panel;

    @Override
    public void init(Integer x, Integer y,String title, State... statesObserved) {
        flights = new Vector<>();

        states = new ArrayList<State>(List.of(statesObserved));
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(x,y);
        frame.setSize(400, 300);
        panel = new JPanel();
        frame.setAlwaysOnTop(true);
        panel.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    @Override
    public void exit() {
        frame.dispose();
    }

    @Override
    public void update(FlightData flight) {
        if (states.contains(flight.getState())) {
            if(!flights.contains(flight)) {
                flights.add(flight);
            }
        }
        flights.removeIf(f -> !states.contains(f.getState()));
        JScrollPane rolagem = new JScrollPane(new JList<>((Vector<FlightData>) flights));
        panel.add(rolagem, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

    }
}
