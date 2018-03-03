export default function reducer(state, action) {
	switch (action.type) {
		case 'EXAMPLE_MUTATION':
			state.example = action.payload;
			return state;
			break;
			
		case 'BUY_GENERATOR': 
		
			if (action.payload === 'FARMER' && state.counter > 10) {
				// state.counter = state.counter - 10;
				// window.store.__state.counter = state.counter;
				window.store.__state.counter = window.store.__state.counter - 10;
			} else if (action.payload === 'HUNTER' && state.counter > 25) {
				// state.counter = state.counter - 25;
				// window.store.__state.counter = state.counter;
				window.store.__state.counter = window.store.__state.counter - 25;
			} else if (action.payload === 'THEIVE' && state.counter > 50) {
				// state.counter = state.counter - 50;
				// window.store.__state.counter = state.counter;
				window.store.__state.counter = window.store.__state.counter - 50;
			} else {
				alert("You do not have enough funds");
			}
			return window.store;
			break;

		case 'ADD_COINS':
			// state.counter++;
			window.store.__state.counter++;
			// window.store.__state.counter = state.counter;
			return window.store;
			break;

		default:
			return state;
	}
}

