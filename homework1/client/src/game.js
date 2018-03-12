// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	// TODO: increment counter based on the generators in the state
	// hint: read how many "generators" in store and iterate through them to
	//       count how many value to increment to "resource"
	// hint: remember to change event through `store.dispatch`
	var value = 0;
	for (var i = 0; i < store.state.generators.length; i++) {
		value += store.state.generators[i].quantity * store.state.generators[i].rate;
	}	

	store.dispatch({
		type: 'INCREMENT',
		payload: value
	});
	
	for (var i = 0; i < store.state.generators.length; i++) {

		if (store.state.generators[i].quantity > 0) {
			var currentConsole = document.querySelector('.terminal').innerHTML;
			document.querySelector('.terminal').innerHTML = `
				${currentConsole} <br/>
				${store.state.story[i].description}
			`;
		}
	}



	// TODO: triggers stories from story to display state if they are passed
	//       the `triggeredAt` points
	// hint: user store.dispatch to send event for changing events states
	store.dispatch({
		type: 'CHECK_STORY'
	});
	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
}
// DELETED BY PROF. ERIC FOR SOME REASON...
// export function increment (state, modifier = 1) {
// 	return state.counter + 1 * modifier;
// }
