export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			
			// TODO: render generator initial view

			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		handleStateChange (newState) {
			console.log('test', this, newState);
		}

		connectedCallback () {
			console.log('Element connection in progress...');
			this.innerHTML = `
			<div class="generators_1">
				<h5> Farm Worker </h5>
				<p>You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people.
					But they don't sell for much.</p>
				<div class="row">
					<p class="rate">{RATE}</p>
					<button> {COINS} </button>
				</div>
			</div>
			`;
			console.log('Element connected');
		}

		disconnectedCallback () {
			console.log('Element disconnection in progress...');
			console.log('Element disconnected');
		}
	};
}
