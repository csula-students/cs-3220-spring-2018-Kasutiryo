import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';
import Story from './models/story';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		// example: 'Hello custom element',
		counter: 0,
		generators: [
			{
				type: "something goes here",
				name: "FARMER",
				description: "You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people. Butthey don't sell for much.",
				rate: 1,
				quantity: 0,
				baseCost: 10,
				unlockValue: 10,
			},
			{
				type: "something goes here",
				name: "HUNTER",
				description: "You hire a experienced hunter to go out and kill monsters and other wild entities. The hunter will gather their spoils and sell them in village for you. They are work a reasonable amount of coins.",
				rate: 2,
				quantity: 0,
				baseCost: 15,
				unlockValue: 20,
			},
			{
				type: "something goes here",
				name: "THEIVE",
				description: "You hire a theive to go out to villages and steal from any civilian they can find. A big risk for a big win.",
				rate: 3,
				quantity: 0,
				baseCost: 25,
				unlockValue: 30,
			}
		],
		story: [
			{
				name: "FARMER",
				description: 'Picking crops...',
				triggeredAt: 10,
				state: `hidden`
			},
			{
				name: "HUNTER",
				description: 'Hunting for wild animals...',
				triggeredAt: 15,
				state: `hidden`
			},
			{
				name: "THEIVE",
				description: 'Pickpocketing random people with heavy pockets...',
				triggeredAt: 25,
				state: 'hidden'
			}
		]
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
