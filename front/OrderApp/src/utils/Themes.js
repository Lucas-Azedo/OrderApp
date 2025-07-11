import { ref } from 'vue';
import iconAutoDetect from '@/assets/icons/auto-detect.svg?raw';
import iconSun from '@/assets/icons/sun.svg?raw';
import iconMoon from '@/assets/icons/moon.svg?raw';

export class Themes {
	static #storageKey = 'theme';
	static defaultTheme = 'system';
	static availableThemes = [
		{name: 'system', icon: iconAutoDetect, description: 'Sistema'},
		{name: 'light', icon: iconSun, description: 'Claro'},
		{name: 'dark', icon: iconMoon, description: 'Escuro'},
		{name: 'business', description: 'Empresarial'},
		{name: 'nord', description: 'Nórdico'},
	];
	static currentTheme = ref(Themes.detect());
	
	static detect() {
		const saved = localStorage.getItem(this.#storageKey) || this.defaultTheme;
		if (saved && this.availableThemes.map(theme => theme.name).includes(saved)) {
			return saved;
		}
		return this.prefersDark() ? 'dark' : 'light';
	}
	
	static apply(newTheme) {
		if (newTheme === this.defaultTheme) {
			localStorage.setItem(this.#storageKey, 'system');
			document.documentElement.setAttribute('data-theme', this.prefersDark() ? 'dark' : 'light');
		} else {
			document.documentElement.setAttribute('data-theme', newTheme);
			localStorage.setItem(this.#storageKey, newTheme);
		}
		this.currentTheme.value = newTheme;
	}
	
	static prefersDark() {
		return window.matchMedia('(prefers-color-scheme: dark)').matches;
	}
}
