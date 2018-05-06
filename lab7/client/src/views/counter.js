export default function (store) {
	return class CounterComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			// TODO: render counter inner HTML based on the store state

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			console.log('CounterComponent#stateChange', newState.counter);
			this.innerHTML = `<output>${newState.counter}</output>`;
			// TODO: update inner HTML based on the new state
		}

		connectedCallback () {
			this.innerHTML = `<output>0</output>`;
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

