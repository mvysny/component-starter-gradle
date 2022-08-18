import {html, LitElement} from 'lit';

class MyComponent extends LitElement {
    render() {
        return html`
            <h2>Hello</h2>
        `;
    }
}

window.customElements.define('my-component', MyComponent);
