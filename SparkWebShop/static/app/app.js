const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const ShoppingCart = { template: '<shopping-cart></shopping-cart>' }
const WebShop = { template: '<web-shop></web-shop>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Home},
	    { path: '/sc', component: ShoppingCart },
	    { path: '/sportObjects', component: SportObjects}
	  ]
});

var app = new Vue({
	router,
	el: '#webShop'
});

