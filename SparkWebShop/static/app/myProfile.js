Vue.component("my-profile", {
	data: function () {
		    return {
				currentUser: null
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		My profile:
		<br>
		<div v-if="currentUser != null">
			<div>
				<p>Username: {{this.currentUser.username}}</p>
				<p>Password: {{this.currentUser.password}}</p>
				<p>First name: {{this.currentUser.firstName}}</p>
				<p>Last name: {{this.currentUser.lastName}}</p>
				<p>Birthdate: {{this.currentUser.birthDate}}</p>
				<p>Gender: {{this.currentUser.gender}}</p>
				<button type="button" v-on:click="startEdit()">Edit</button>
				<br>
				<button type="button" v-on:click="logOut()">Log out</button>
				<br>
			</div>
			
			<div v-if="currentUser.role.includes('ADMIN')">
				<button type="button" v-on:click="createSportObject()">Create sport object</button>
				<button type="button" v-on:click="createMenager()">Create menager</button>
				<button type="button" v-on:click="createTrainer()">Create trainer</button>
				<br>
				<button type="button" v-on:click="listUsers()">List users</button>
				<br>
				<button type="button" v-on:click="approveComments()">Approve comments</button>
				
			</div>
			
			<div v-if="currentUser.role.includes('MENAGER')">			
				<button type="button" v-if="currentUser.role.includes('MENAGER')" v-on:click="showSportObject()">Show my sport object</button>
				<br>
				<button type="button" v-on:click="showTreningsMenagersObject()">Show trainings in my sport object</button>
			</div>
			
			<div v-if="currentUser.role.includes('TRAINER')">
			<button v-on:click="showMyTrainingsTrainer">View my trainings</button>
			</div>
			
			<div v-if="currentUser.role.includes('BUYER')">
				<button type="button" v-on:click="showTrainingHistory()">Show training history</button>
				<br>
				<button type="button" v-on:click="showMembershipOptions()">Show membership options</button>
				<br>
				<button type="button" v-on:click="startSession()">Start training session</button>
				<br>
				<button type="button" v-on:click="writeReview()">Write review</button>
				<br>
			</div>
			
		</div>
	</div>	
	
	
</div>
`
	,
	methods : {
		showTreningsMenagersObject:function(){
			console.log("Menadzer gleda treninge u svom objektu!");
			router.push('/menagers-trainings');
		},
		showMyTrainingsTrainer: function(){
			router.push('/trainers-trainings');
		},
		startEdit(){
			console.log("Pushing router to edit profile!");
			router.push('/edit-profile')
		},
		logOut(){
			console.log("Log out clicked");
			axios
		    .post('rest/log-out')
		    .then(response => {
				console.log(response.data);
			})
	    	.catch((error) => console.log(error));
			router.push('/');
		},
		createSportObject(){
			console.log("Pushing router to create sport object!");
			router.push('/create-sport-object')
		},
		createMenager(){
			console.log("Pushing router to create menager!");
			router.push('/create-menager')
		},
		createTrainer(){
			console.log("Pushing router to create trainer!");
			router.push('/create-trainer')
		},
		listUsers(){
			console.log("Pushing router to list users!");
			router.push('/list-users')
		},
		approveComments(){
			console.log("Pushing router to approve comments!");
			router.push('/approve-comments')
		},
		showSportObject(){
			console.log("Show sport object selected!");
			console.log(this.currentUser);
	        axios
				.get('/rest/MenagersSportObject')
				.then(response => {
					if(response.data == '404')
					{
					 console.log("Menager dont have sport object!");
					}else
					{					
						router.push('/sport-object');
					}
				})
		},
		showTrainingHistory(){
			console.log("Show training history selected!");
			router.push('/training-history')
		},
		showMembershipOptions(){
			console.log("Show membership options selected!");
			router.push('/show-membership-options')
		},
		startSession(){
			console.log("Start session selected!");
			console.log(this.currentUser);
	        axios
				.get('/rest/check-membership')
				.then(response => {
					if(response.data == false)
					{
					 toast("Membership not valid!");
					}else
					{					
						router.push('/start-session');
					}
				})
		},
		writeReview(){
			console.log('Write review selected.');
			router.push('/write-review');
		}
	},
	mounted () {
        console.log("Mounted MyProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data;
				}
			})
    }
});