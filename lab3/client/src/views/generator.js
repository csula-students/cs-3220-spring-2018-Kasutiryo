export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			this.onStateChange = this.handleStateChange.bind(this);
			// TODO: render generator initial view

			// TODO: subscribe to store on change event

			// TODO: add click event

		}

		handleStateChange (newState) {
			console.log('GeneratorComponent#stateChange', newState.generators);

		}

		connectedCallback () {
			console.log('GeneratorComponent#onConnectedCallback');
			switch (this.dataset.id) {
				case "0":
					this.innerHTML = `                
						<div class="titleRow">
							<h5> Farm Worker </h5>
							<p class="rate">0</p>
						</div>
						<p class="description">You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people. Butthey don't sell for much.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button ID='gen'>Purchase</button>
						</div>`;
						break;
				case "1":					
					this.innerHTML = `
						<div class="titleRow">
							<h5> Hunter </h5>
							<p class="rate">0</p>
						</div>
						<p class="description">You hire a experienced hunter to go out and kill monsters and other wild entities. The hunter will gather
							their spoils and sell them in village for you. They are work a reasonable amount of coins.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button ID='gen'>Purchase</button>
						</div>
					`;
					break;
				case "2":
						this.innerHTML = `
						<div class="titleRow">
							<h5> Theive </h5>
							<p class="rate">0</p>
						</div>
						<p>You hire a theive to go out to villages and steal from any civilian they can find. A big risk for a big win.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button ID='gen'>Purchase</button>
						</div>
					`;
					break;
				default: console.log('Something went wrong');
			}

			const btns = this.querySelectorAll('#gen');

			btns.forEach(btn => {
				btn.addEventListener('click', () => {
					this.store.dispatch({
						type: 'BUY_GENERATOR',
						payload: {
							name: `${this.store.state.generators[this.dataset.id].name}`,
							quantity: `${this.store.state.generators[this.dataset.id].quantity}`,
						}
					});
				});
			});

			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallBack () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}
