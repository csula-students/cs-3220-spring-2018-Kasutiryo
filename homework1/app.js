class PubSub {
    constructor () {
        this.subscribers = [];
    }

    // subscribe allows a new subscriber to listen for changes by providing
    // callback function in the parameter
    subscribe (fn) {
        this.subscribers.push(fn);
    }

    // one can publish any data to subscribers
    publish (data) {
        this.subscribers.forEach(subscriber => {
            subscriber(data);
        });
    }
}

const pubSub = new PubSub();

pubSub.subscribe(data => {
    window.incrementalGame.state.counter++;
    document.getElementById("coins").innerHTML = window.incrementalGame.state.counter;
});

document.querySelector('#clicker').addEventListener('click', function () {
    pubSub.publish(window.incrementalGame.state.counter);
});

// const pubSub = new PubSub();

// pubSub.subscribe(data => {
//     console.log(data);
// });

// pubSub.publish('Hello world!');
