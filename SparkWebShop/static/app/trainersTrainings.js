Vue.component("trainers-trainings", {
	data:function(){
		return{
			curentUser:null,
			trainersTrainingsPersonal : null,
			trainersTrainingsGroup: null,
			selected:false,
			selectedTraining : null
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Trainers personal trainings:
		<br>
		<table v-if="trainersTrainingsPersonal != null" >
		<tr bgcolor="lightgrey">
					<th scope="col">Date time   </th>
					<th scope="col">Buyer</th>
					<th scope="col">Workout name   </th>
					<th scope="col">Sport object   </th>
		</tr>
		<tr v-for="tr in trainersTrainingsPersonal" v-on:click="select(tr)">
					<td>{{tr.dateTimeOfSignUp}}</td>
					<td>{{tr.buyer}}</td>
					<td>{{tr.workoutName}}</td>
					<td>{{tr.SportObject}}</td>
			</tr>
		</table>
		<br>
			<button v-on:disable="selected" v-on:click="deleteSelected">Delete</button>
		<br>
		<label v-if="trainersTrainingsGroup != null">Trainers group trainings:</label>
		<br>
		<table v-if="trainersTrainingsGroup != null" >
		<tr bgcolor="lightgrey">
					<th scope="col">Date time   </th>
					<th scope="col">Workout name   </th>
					<th scope="col">Sport object  </th>
		</tr>
		<tr v-for="tr in trainersTrainingsGroup">
					<td>{{tr.dateTimeOfSignUp}}</td>
					<td>{{tr.workoutName}}</td>
					<td>{{tr.SportObject}}</td>
			</tr>
		</table>
	</div>
</div>
`
,

	methods:{
		deleteSelected: function(){
			axios
			.post('rest/deleteTraining', this.selectedTraining)
			.then(response =>(console.log(response.data)))
			.catch((error) => console.log(error));
			
			axios
		.get('rest/traingSessionsForTrainerPersonal')
		.then(response => {
			console.log(response.data);
			this.trainersTrainingsPersonal = response.data;				
		});
		},
		select: function(training){
			console.log("selektovan je!");
			this.selected = true;
			this.selectedTraining = training;
		},
	},
	
	mounted () {
		console.log("Mounted Trainers trainings!");
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
		.get('rest/traingSessionsForTrainerPersonal')
		.then(response => {
			console.log(response.data);
			this.trainersTrainingsPersonal = response.data;				
		});
		
		axios
		.get('rest/traingSessionsForTrainerGroup')
		.then(response => {
			console.log(response.data);
			if(response.data == '404'){
				console.log('Dont have Group training!');
			}else{				
			this.trainersTrainingsGroup = response.data;
			}
		});
		
	},		
});