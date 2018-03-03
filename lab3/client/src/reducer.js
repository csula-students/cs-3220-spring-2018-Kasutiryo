export default function reducer(state, action) {
	switch (action.type) {
		case 'EXAMPLE_MUTATION':
			state.example = action.payload;
			return state;
			break;
			
		case 'BUY_GENERATOR': 
		
			if (action.payload === 'FARMER') {
				state.counter = state.counter - 10;
				store.state.quantity++;
				window.store.__state.counter = state.counter;
			} else if (action.payload === 'HUNTER') {
				state.counter = state.counter - 25;
				window.store.__state.counter = state.counter;
			} else if (action.payload === 'THEIVE') {
				state.counter = state.counter - 50;
				window.store.__state.counter = state.counter;
			}
			return state;
			break;

		case 'ADD_COINS':
			state.counter++;
			window.store.__state.counter = state.counter;
			return state;
			break;

		default:
			return state;
	}
}

