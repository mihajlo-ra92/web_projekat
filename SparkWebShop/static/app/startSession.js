Vue.component("start-session", {
	data: function () {
		    return {
				currentUser: null,
				sportObjects: null,
				workouts: null,
				selectedObject: null,
				selectedWorkout: null,
				selected: false
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		My profile:
		<br>
		<div v-if="currentUser != null">
			<div v-if="currentUser.role.includes('BUYER')">
				<h3>Pick sport object</h3>
				<table class="table table-bordered table--lg team-roster-table table-hover"">
					<tr bgcolor="lightgrey">
						<th scope="col">Name</th>
						<th scope="col">Object type</th>
						<th scope="col">Status</th>
						<th scope="col">Address</th>
						<th scope="col">Grade</th>
					</tr>
						
					<tr v-for="so in sportObjects" v-if="so.isOpen" v-on:click="selectObject(so)">
						
						<td scope="row">{{so.name }}</td>
						<td>{{so.objectType }}</td>
						<td v-if="so.isOpen">Open</td>
						<td v-else>Closed</td>
						<td>{{so.location.address }}</td>
						<td>{{so.avegareGrade }}</td>
					</tr>
				</table>
				
				<div v-if="selected != false">
					<table>
						<tr bgcolor="lightgrey">
							<th scope="col">Content name  </th>
							<th scope="col">Content type  </th>
							<th scope="col">Duration  </th>
							<th scope="col">Description  </th>
							<th scope="col">Trainer  </th>
						</tr>
						
						<tr v-for="wo in workouts" v-if="wo.sportObject === selectedObject.name" v-on:click="selectWorkout(wo)">
							<td scope="row">{{wo.name}}</td>
							<td scope="row">{{wo.workoutType}}</td>
							<td scope="row">{{wo.workoutDuration}}</td>
							<td scope="row">{{wo.description}}</td>
							<td scope="row">{{wo.trainer}}</td>
						</tr>
					</table>
						<br>
					<button type="button" v-on:click="unselect()">Back</button>
				</div>
			</div>
			
		</div>
	</div>	
	
	
</div>
`
	,
	methods : {
		selectObject : function(sportObject){
			console.log("Usli smo u select sportObject.");
			this.selectedObject = sportObject;
			this.selected = true;			
		},
		selectWorkout: function(workout){
			console.log("Usli smo u select workout.");
			this.selectedWorkout = workout;
			axios
		    .post('rest/startTraining', this.selectedWorkout)
		    .then(response => {
				toast(response.data);
			})
	    	.catch((error) => console.log(error));
			router.push('/my-profile');
		},
		unselect : function() {
			console.log("vrati na tabelu!");
			this.selected = false;
			
		}
	},
	mounted () {
        console.log("Mounted start session");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data;
				}
			});
		axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
         
        axios
        .get('rest/proizvodi/getAllcontents')
        .then(response => this.workouts = response.data);
		
		
    }
});