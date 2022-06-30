const Home = { template: '<home></home>' }
const SportObjects = { template: '<sport-objects></sport-objects>' }
const Register = { template: '<register></register>' }
const MyProfile = { template: '<my-profile></my-profile>'}
const EditProfile = { template: '<edit-profile></edit-profile>'}
const CreateSportObject = { template: '<create-sport-object></create-sport-object>'}
const CreateMenager = { template: '<create-menager></create-menager>'}
const CreateTrainer = { template: '<create-trainer></create-trainer>'}
const ListUsers = { template: '<list-users></list-users>'}
const ListSportObjects= { template: '<list-sport-objects></list-sport-objects>'}
const TrainingHistory= { template: '<training-history></training-history>'}

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
	    { path: '/create-trainer', component: CreateTrainer},
	    { path: '/list-users', component: ListUsers},
	    { path: '/list-sport-objects', component: ListSportObjects},
	    { path: '/training-history', component: TrainingHistory}
	  ]
});

var app = new Vue({
	router,
	el: '#fitnessCener',
});

