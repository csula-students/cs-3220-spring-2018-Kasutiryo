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
			this.innerHTML = `                
						<div class="titleRow">
							<h5> ${newState.generators[this.dataset.id].name} </h5>
							<p class="rate" id="quantity">${newState.generators[this.dataset.id].quantity}</p>
						</div>
						<p class="description">${newState.generators[this.dataset.id].description}</p>
						<div class="purchaseRow">
							<p class="rate">${newState.generators[this.dataset.id].rate}</p>
							<button ID='gen'>Purchase</button>
						</div>`;
			const btns = this.querySelectorAll('#gen');

			btns.forEach(btn => {
				btn.addEventListener('click', () => {
					this.store.dispatch({
						type: 'BUY_GENERATOR',
						payload: newState.generators[this.dataset.id]
					});
				});
			});
		}

		connectedCallback () {
			console.log('GeneratorComponent#onConnectedCallback');

			this.innerHTML = `                
						<div class="titleRow">
							<h5> ${this.store.state.generators[this.dataset.id].name} </h5>
							<p class="rate" id="quantity">${this.store.state.generators[this.dataset.id].quantity}</p>
						</div>
						<p class="description">${this.store.state.generators[this.dataset.id].description}</p>
						<div class="purchaseRow">
							<p class="rate">${this.store.state.generators[this.dataset.id].rate}</p>
							<button ID='gen'>Purchase</button>
						</div>`;

			const btns = this.querySelectorAll('#gen');

			btns.forEach(btn => {
				btn.addEventListener('click', () => {
					this.store.dispatch({
						type: 'BUY_GENERATOR',
						payload: this.store.state.generators[this.dataset.id]
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
