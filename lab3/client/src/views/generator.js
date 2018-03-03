export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor() {
			super();
			this.store = store;
			this.init();
			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);
			// TODO: add click event

			// document.querySelector('#FARMER').addEventListener('click', () =>{
			// 	this.store.dispatch({
			// 		type: 'BUY_GENERATOR',
			// 		payload: 'FARMER',
			// 	});
			// });

			// document.querySelector('#HUNTER').addEventListener('click1', () =>{
			// 	this.store.dispatch({
			// 		type: 'BUY_GENERATOR',
			// 		payload: 'HUNTER',
			// 	});
			// });

			// document.querySelector('#THEIVE').addEventListener('click2', () =>{
			// 	this.store.dispatch({
			// 		type: 'BUY_GENERATOR',
			// 		payload: 'THEIVE'
			// 	});
			// });

			const btns = this.querySelectorAll('#gen');

			btns.forEach(btn => {
				btn.addEventListener('click', () => {
					this.store.dispatch({
						type: 'BUY_GENERATOR',
						payload: this.dataset.payload,
					});
				});
			});
		}

		handleStateChange(newState) {
			console.log('GeneratorComponent#stateChange', this, newState);
			this.store.subscribe(newState);

		}

		connectedCallback() {
			console.log('GeneratorComponent#onConnectedCallback');
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback() {
			console.log('GeneratorComponent#onDisconnectedCallback');
			this.store.unsubscribe(this.onStateChange);
		}

		init() {
			const ID = this.dataset.id;
			// TODO: render generator initial view
			switch (ID) {
				case '10':
					this.innerHTML = `
						<div class="titleRow">
							<h5> Farm Worker </h5>
							<p class="rate">0</p>
						</div>
						<p class="description">You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people.
							But they don't sell for much.</p>
						<div class="purchaseRow">
							<p class="rate">{RATE}</p>
							<button ID='gen'>Purchase</button>
						</div>
					`; break;
				case '25':
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
					`; break;
				case '50':
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
					`; break;
				default: console.log('Someone wrong happened');
			}
		}
	};
}
