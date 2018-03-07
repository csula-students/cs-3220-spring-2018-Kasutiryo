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
			if (action.payload.name === state.generators[i].name && state.counter >= state.generators[i].baseCost) {
				state.counter -= state.generators[i].baseCost;
				state.generators[i].quantity++;
				return state;
			} else {
				alert('You do not have enough cash');
				return state;
			}
		}
	default:
		return state;
	}
}

