export default function reducer (state, action) {
	switch (action.type) {
	case 'EXAMPLE_MUTATION':
		state.example = action.payload;
		return state;
	case 'BUTTON_CLICK':
		console.log('Incremented coin by 1');
		state.counter++;
		return state;
	case 'BUY_GENERATOR':

		for (var i = 0; i < state.generators.length; i++) {

			var newCost = Math.round((state.generators[i].baseCost*Math.pow(1 + 0.05, state.generators[i].quantity)).toFixed(2) * 100)/100;

			if (action.payload.name === state.generators[i].name && state.counter >= newCost) {
				console.log(action.payload.name + ' = ' + state.generators[i].name);
				console.log(state.counter + ' >= ' + newCost);
				state.counter -= newCost;
				state.generators[i].quantity++;
				return state;
			}
		}
		alert('You do not have enough cash');
		return state;
	default:
		return state;
	}
}

