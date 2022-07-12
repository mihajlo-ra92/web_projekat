Vue.component("menagers-trainings", {
	data: function() {
		return {
			curentUser: null,
			trainings: null
		}
	},
	
	template: ` 
	<div>
		Trainings in your sport object:
		<br>
		<table class="table table-bordered table--lg team-roster-table table-hover"">
			<tr bgcolor="lightgrey">
			<th scope="col">Trainer   </th>
			<th scope="col">Buyer    </th>
			<th scope="col">Date time   </th>
			<th scope="col">Workout   </th>
			</tr>
			<tr v-for="tr in trainings">
			<td>{{tr.trainer}}</td>
			<td>{{tr.buyer}}</td>
			<td>{{tr.dateTimeOfSignUp}}</td>
			<td>{{tr.workoutName}}</td>
			</tr>
		</table>
	</div>
	` 
	,
	methods:{
		
	},
	mounted(){
		 console.log("Mounted menagers trainings!");
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
		.get('rest/TrainingsInSportObject')
		.then(response => {
		console.log(response.data)
		if (response.data == '403'){
					console.log('Nema treninga u sportskom objektu!');
				}
				else {
					this.trainings = response.data;
					console.log("IMA");
				}
		});
		
	}

});