export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;

			// TODO: render generator initial view

			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		handleStateChange(newState) {
			console.log('test', this, newState);
		}

		connectedCallback() {
			console.log('Element connection in progress...');
			const message = this.dataset.message;
			switch (message) {
				case '0':
					this.innerHTML = `
						<h5> Farm Worker </h5>
						<p class="description">You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people.
							But they don't sell for much.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button>Purchase</button>
						</div>
					`; break;
				case '1':
					this.innerHTML = `
						<h5> Hunter </h5>
						<p class="description">You hire a experienced hunter to go out and kill monsters and other wild entities. The hunter will gather
							their spoils and sell them in village for you. They are work a reasonable amount of coins.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button> {COINS} </button>
						</div>
					`; break;
				case '2':
					this.innerHTML = `
						<h5> Theive </h5>
						<p>You hire a theive to go out to villages and steal from any civilian they can find. A big risk for a big win.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button> {COINS} </button>
						</div>
					`; break;
				default: console.log('Someone wrong happened');
			}
			console.log('Element connected');
		}

		disconnectedCallback() {
			console.log('Element disconnection in progress...');
			console.log('Element disconnected');
		}
	};
}
