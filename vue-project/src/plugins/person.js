export default {
	install(app, options) {
		const person = {
			name: '짐코딩',
			say() {
				alert(this.name);
			},
			...options,
		};
		app.provide('person', person);
		app.config.globalProperties.$person = person;
	},
};
