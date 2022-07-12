Vue.component("trainers-trainings", {
	data:function(){
		return{
			searchName:'',
			selectedType : "",
			curentUser:null,
			trainersTrainingsPersonal : null,
			allTrainersTrainingsPersonal :null,
			trainersTrainingsGroup: null,
			allTrainersTrainingsGroup :null,
			selected:false,
			selectedTraining : null,
			retValForFilterForPersonal : [],
			retValForFilterForGroup : []
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<br>
		<input type="text" v-model="searchName" placeholder="Sport object name"/>
		<select name="SOType" @change="onChange($event)" v-model="selectedType">
			<option value="">All objects</option>
			<option value="gym">Gyms</option>
			<option value="pool">Pools</option>
			<option value="dance">Dance studios</option>
		</select>
		<br>
		<button v-on:click="search">Search</button>
		<br>
		Trainers personal trainings:
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
			onChange(event) {
		//	console.log(event.target.value, this.selectedType);	
				
		},	
		search: function(){
			console.log("Usli smo u search.");
			
			if(this.selectedType === "gym"){
			
			axios
			.get('rest/getJustGyms')
			.then(response => {gyms = response.data;
							console.log("Ovo su teretane:");
							console.log(response.data);
							retValInside =[];
							for(let j = 0 ; j < this.allTrainersTrainingsPersonal.length ; j++){								
								for(let i = 0 ; i < gyms.length ; i++){
									if(this.allTrainersTrainingsPersonal[j].SportObject === gyms[i].name){
										retValInside.push(this.allTrainersTrainingsPersonal[j]);
									}
								}
							}
							this.retValForFilterForPersonal = retValInside;	
							
							retValInside1 = [];
							for(let j = 0 ; j < this.allTrainersTrainingsGroup.length ; j++){								
								for(let i = 0 ; i < gyms.length ; i++){
									console.log("Jel ova ista:");
									console.log(this.allTrainersTrainingsGroup[j].SportObject);
									if(this.allTrainersTrainingsGroup[j].SportObject === gyms[i].name){
										retValInside1.push(this.allTrainersTrainingsGroup[j]);
									}
								}
							}
							console.log("ovde :");
							console.log(retValInside1);
							this.retValForFilterForGroup = retValInside1;	
								
			})	
			}else if(this.selectedType === "pool"){
				axios
			.get('rest/getJustPools')
			.then(response => {pools = response.data;
							retValInside = [];
							for(let j = 0 ; j < this.allTrainersTrainingsPersonal.length ; j++){								
								for(let i = 0 ; i < pools.length ; i++){
									if(this.allTrainersTrainingsPersonal[j].SportObject === pools[i].name){
										retValInside.push(this.allTrainersTrainingsPersonal[j]);
									}
								}
							}	
							this.retValForFilterForPersonal = retValInside;	
							
							retValInside1 =[];
							for(let j = 0 ; j < this.allTrainersTrainingsGroup.length ; j++){								
								for(let i = 0 ; i < pools.length ; i++){
									if(this.allTrainersTrainingsGroup[j].SportObject === pools[i].name){
										retValInside1.push(this.allTrainersTrainingsGroup[j]);
									}
								}
							}
							console.log("ovde :");
							console.log(retValInside1);
							this.retValForFilterForGroup = retValInside1;
								
			})
			}else if(this.selectedType === "dance"){
					axios
			.get('rest/getDanceStudios')
			.then(response => {studios = response.data;
			
							retValInside = [];
							for(let j = 0 ; j < this.allTrainersTrainingsPersonal.length ; j++){								
								for(let i = 0 ; i < studios.length ; i++){
									if(this.allTrainersTrainingsPersonal[j].SportObject === studios[i].name){
										retValInside.push(this.allTrainersTrainingsPersonal[j]);
									}
								}
							}
							this.retValForFilterForPersonal = retValInside;
							
							retValInside1 =[];
							for(let j = 0 ; j < this.allTrainersTrainingsGroup.length ; j++){								
								for(let i = 0 ; i < studios.length ; i++){
									if(this.allTrainersTrainingsGroup[j].SportObject === studios[i].name){
										retValInside1.push(this.allTrainersTrainingsGroup[j]);
									}
								}
							}
							console.log("ovde :");
							console.log(retValInside1);
							this.retValForFilterForGroup = retValInside1;
							
				})
			}else if(this.selectedType === ""){
				this.retValForFilterForPersonal = this.allTrainersTrainingsPersonal;
				this.retValForFilterForGroup = this.allTrainersTrainingsGroup;
			}
			
			retVal=[];
			for(let i = 0; i < this.retValForFilterForPersonal.length; i++){
				if(this.retValForFilterForPersonal[i].SportObject.toLowerCase().
				includes(this.searchName.toLowerCase()))
				{
					retVal.push(this.retValForFilterForPersonal[i]);
				}
			}
			this.trainersTrainingsPersonal = retVal;
			
			retValGroup = [];
			for(let i = 0; i < this.retValForFilterForGroup.length; i++){
				if(this.retValForFilterForGroup[i].SportObject.toLowerCase().
				includes(this.searchName.toLowerCase()))
				{
					retValGroup.push(this.retValForFilterForGroup[i]);
				}
			}
			this.trainersTrainingsGroup = retValGroup;
		},
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
			this.allTrainersTrainingsPersonal = response.data;			
		});
		
		axios
		.get('rest/traingSessionsForTrainerGroup')
		.then(response => {
			console.log(response.data);
			if(response.data == '404'){
				console.log('Dont have Group training!');
			}else{				
			this.trainersTrainingsGroup = response.data;
			this.allTrainersTrainingsGroup = response.data;
			}
		});
		
	},		
});