Vue.component("training-history", {
	data: function () {
		    return {
				currentUser: null,
				trainingSessions: null
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Training history:
		<br>
		<table class="table table-bordered table--lg team-roster-table table-hover"">
			<tr bgcolor="lightgrey">
				<th scope="col">Date</th>
				<th scope="col">Workout type</th>
				<th scope="col">Buyer</th>
				<th scope="col">Trainer</th>
			</tr>
				
			<tr v-for="ts in trainingSessions">
				<td scope="row">{{ts.dateTimeOfSignUp }}</td>
				<td>{{ts.workoutName }}</td>
				<td>{{ts.buyer }}</td>
				<td>{{ts.trainer }}</td>
			</tr>
		</table>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		startEdit(){
			console.log("Pushing router to edit profile!");
			router.push('/edit-profile')
		},
		
		startEdit(){
			console.log("Pushing router to create sport object!");
			router.push('/edit-profile')
		}
	},
	mounted () {
        console.log("Mounted list sport objects");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				this.currentUser = response.data;
				console.log('loged user:');
		      	console.log(this.currentUser);
		      	
		      	axios
					.post('rest/proizvodi/getTrainingHistory', this.currentUser)
		      		.then(response => {
						if (response.data == 'EMPTY'){
							console.log('No loged training sessions!');
						}
						else {
							this.trainingSessions = response.data;
							console.log(this.trainingSessions);
						}
					});
				axios
					.post('rest/proizvodi/getTrainingHistory', this.currentUser)
		      		.then(response => {
						if (response.data == 'EMPTY'){
							console.log('No loged training sessions!');
						}
						else {
							this.trainingSessions = response.data;
							console.log(this.trainingSessions);
						}
					});
				});
    }
});