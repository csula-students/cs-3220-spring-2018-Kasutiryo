package edu.csula.models;

import java.util.List;

public class State {    

    private Integer counter;
    private Generator[] generators;
    private Event[] story;

    public State(Integer counter, Generator[] generators, Event[] story) {

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

    public void setGenerators(Generator[] generators) {
        this.generators = generators;
    }

    public Generator[] getGenerators() {
        return generators;
    }

    public void setstory(Event[] story) {
        this.story = story;
    }

    public Event[] getstory() {
        return story;
    }
}