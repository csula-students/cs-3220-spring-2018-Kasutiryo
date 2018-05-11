package edu.csula.models;

import java.util.List;

public class State {    

    private Integer counter;
    private List<Generator> generators;
    private List<Event> story;

    public State(Integer counter, List<Generator> generators, List<Event> story) {

        this.counter = counter;
        this.generators = generators;
        this.story = story;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setGenerators(List<Generator> generators) {
        this.generators = generators;
    }

    public List<Generator> getGenerators() {
        return generators;
    }

    public void setstory(List<Event> story) {
        this.story = story;
    }

    public List<Event> getstory() {
        return story;
    }
}