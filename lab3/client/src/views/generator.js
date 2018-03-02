export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;
			const message = this.dataset.message;
			// TODO: render generator initial view

			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);
			// TODO: add click event
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'EXAMPLE_MUTATION',
					payload: 'You clicked this element'
				});
			});
		}

		handleStateChange(newState) {
			console.log('GeneratorComponent#stateChange', this);
			this.textContent = newState.example;
		}

		connectedCallback() {
			console.log('Element connection in progress...');
			switch (message) {
				case '0':
					this.innerHTML = `
						<div class="titleRow">
							<h5> Farm Worker </h5>
							<p class="rate">0</p>
						</div>
						<p class="description">You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people.
							But they don't sell for much.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button>Purchase</button>
						</div>
					`; break;
				case '1':
					this.innerHTML = `
						<div class="titleRow">
							<h5> Hunter </h5>
							<p class="rate">0</p>
						</div>
						<p class="description">You hire a experienced hunter to go out and kill monsters and other wild entities. The hunter will gather
							their spoils and sell them in village for you. They are work a reasonable amount of coins.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button>Purchase</button>
						</div>
					`; break;
				case '2':
					this.innerHTML = `
						<div class="titleRow">
							<h5> Theive </h5>
							<p class="rate">0</p>
						</div>
						<p>You hire a theive to go out to villages and steal from any civilian they can find. A big risk for a big win.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button>Purchase</button>
						</div>
					`; break;
				default: console.log('Someone wrong happened');
			}
			console.log('GeneratorComponent#onConnectedCallback');
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback() {
			console.log('Element disconnection in progress...');
			console.log('Element disconnected');
		}
	};
}
