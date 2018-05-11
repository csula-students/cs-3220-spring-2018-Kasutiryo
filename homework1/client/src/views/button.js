export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;


			this.onStateChange = this.handleStateChange.bind(this);


			// TODO: add click event to increment counter
			// hint: use "store.dispatch" method (see example component)
		}

		handleStateChange (newState) {
			console.log('ExampleComponent#stateChange');
		}
		
		connectedCallback () {
			console.log('ExampleComponent#onConnectedCallback', this);
			this.innerHTML = `<button>BEG FOR MONEY</button>`;
			this.addEventListener('click', () => {
				//console.log("Hello world");
				this.store.dispatch({
					type: 'BUTTON_CLICK'
				});
			});
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			console.log('ExampleComponent#onDisconnectedCallback', this)
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
