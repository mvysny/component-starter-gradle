import {html, LitElement} from 'lit';
import { customElement } from 'lit/decorators.js';

@customElement("my-component")
export class MyComponent extends LitElement {
    render() {
        return html`
            <h2>Hello</h2>
        `;
    }
}
