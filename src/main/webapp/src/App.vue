<template>
	<div id="app">
		<router-view />
	</div>
</template>

<script>
	export default {
		name: 'App',
		beforeMount() {
			if (localStorage.getItem('theme') === 'dark') {
				document.documentElement.setAttribute('data-theme', 'dark');
			} else {
				document.documentElement.setAttribute('data-theme', 'light');
			}
		}
	}
</script>

<style lang="scss">
html {
	--bg: #e9e9eb;
	--container-bg: #f8f9fa;
	--header-bg: #ef4b4c;
	--header-text: #ffffff;
	--card-bg: #ffffff;
	--primary-text: #000000;
	--secondary-text: #6c757d;
	--primary-text2: #007bff;
	--button-bg: #007bff;
	--button-text: #ffffff;
	--border: #dee2e6;
	--new-chat-button: #8fd19e;
	--new-chat-button-hover: #28a745;
	--mate-msg-bg: #ffffff;
	--my-msg-bg: #fff7f7;
}

html[data-theme='dark'] {
	--bg: #222222;
	--container-bg: #121212;
	--header-bg: #902f30;
	--header-text: #e3e3e3;
	--card-bg: #1e1e1e;
	--primary-text: #e3e3e3;
	--secondary-text: #797979;
	--primary-text2: #f8ad16;
	--button-bg: #3ca978;
	--button-text: #f5dd6f;
	--border: #383838;
	--new-chat-button: #8fd19e;
	--new-chat-button-hover: #28a745;
	--mate-msg-bg: #383838;
	--my-msg-bg: #323232;
}

body {
	background-color: var(--bg) !important;
}

.secondary-text {
	color: var(--secondary-text);
}

.primary-text {
	color: var(--primary-text);
}

.primary-text2 {
	color: var(--primary-text2);
}

.color1 {
	/* Зеленый */
	background-color: #88e69c;
	color: #ffffff;
}



.color2 {
	/* Салатовый */
	background-color: #90f948;
	color: #000000;
}

.color3 {
	/* Синий */
	background-color: #628cff;
	color: #ffffff;
}

.color4 {
	/* Голубой */
	background-color: #86eeff;
	color: #000000;
}

.color5 {
	/* Красный */
	background-color: #ff9494;
	color: #ffffff;
}

.color6 {
	/* Оранжевый */
	background-color: #ffde67;
	color: #000000;
}

.color7 {
	/* Фиолетовый */
	background-color: #e65dff;
	color: #ffffff;
}

.color8 {
	/* Розовый */
	background-color: #ffa8fd;
	color: #000000;
}
</style>