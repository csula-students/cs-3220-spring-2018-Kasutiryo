import generator from "./generator";

export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;
			//TODO: initial DOM rendering of story itself
			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			// TODO: display story based on the state "resource" and "stories"
			console.log('StorybookComponent#stateChange');
		}

		connectedCallback () {
			console.log('StorybookComponent#onConnectedCallback');
			this.innerHTML = `
				Welcome to the World of Coins!

			`;
			this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

