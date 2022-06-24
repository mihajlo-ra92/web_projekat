const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const Register = { template: '<register></register>' }
const MyProfile = { template: '<my-profile></my-profile>'}
const EditProfile = { template: '<edit-profile></edit-profile>'}
const CreateSportObject = { template: '<create-sport-object></create-sport-object>'}
const CreateMenager = { template: '<create-menager></create-menager>'}
const CreateTrainer = { template: '<create-trainer></create-trainer>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: Home},
	    { path: '/sportObjects', component: SportObjects},
	    { path: '/register', component: Register},
	    { path: '/edit-profile', component: EditProfile},
	    { path: '/my-profile', component: MyProfile},
	    { path: '/create-sport-object', component: CreateSportObject},
	    { path: '/create-menager', component: CreateMenager},
	    { path: '/create-trainer', component: CreateTrainer}
	  ]
});

var app = new Vue({
	router,
	el: '#fitnessCener',
});

