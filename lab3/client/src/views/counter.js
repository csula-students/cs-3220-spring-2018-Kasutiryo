export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: render counter inner HTML based on the store state
			this.innerHTML = `
					<div class="coinPouch">COINS: </div>
					<output class="coinPouch" id="coins">0</output>
					<button class="coinPouch" id="clicker"> BEG FOR MONEY </button>

			`;
			this.onStateChange = this.handleStateChange.bind(this);

			this.querySelector('#clicker').addEventListener('click', () => {
				this.store.dispatch({
					type: 'ADD_COINS',
				});
			});
		}

		handleStateChange (newState) {
			console.log('CounterComponent#stateChange', this, newState);
			// TODO: update inner HTML based on the new state
			document.getElementById("coins").innerHTML = window.store.__state.counter;
		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

