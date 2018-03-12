import Generator from "./models/generator";
import Story from "./models/story";

export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;
	case 'BUTTON_CLICK':
		state.counter++;
		return state;
	case 'BUY_GENERATOR':

		for (var i = 0; i < state.generators.length; i++) {
				const generator = new Generator(state.generators[i]);
				var newCost = generator.getCost();
			if (action.payload.name === state.generators[i].name && state.counter >= newCost) {
				state.counter -= newCost;
				state.generators[i].quantity++;
				return state;
			}
		}
		alert('You do not have enough cash');
		return state;

	case 'INCREMENT':

		state.counter += action.payload;
		return state;
	
	case 'CHECK_STORY':

		for (var i = 0; i < state.story.length; i++) {
			if(state.counter >= state.story[i].triggeredAt && state.story[i].state === 'hidden') {
				state.story[i].state = 'visible';
			}
		}

		return state;

	default:
		return state;
	}
}

