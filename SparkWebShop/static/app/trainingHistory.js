Vue.component("training-history", {
	data: function () {
		    return {
				selectedType : "",
				searchName : '',
				workouts : [],
				selectedWorkout : '',
				currentUser: null,
				trainingSessions: null,
				allTrainingSessions :null,
				retValForFilter : []
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Training history:
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
		<table class="table table-bordered table--lg team-roster-table table-hover"">
			<tr bgcolor="lightgrey">
				<th scope="col">Date </th>
				<th scope="col">Workout type </th>
				<th scope="col">Sport object  </th>
				<th scope="col">Trainer  </th>
			</tr>
				
			<tr v-for="ts in trainingSessions">
				<td scope="row">{{ts.dateTimeOfSignUp }}</td>
				<td>{{ts.workoutName }}</td>
				<td>{{ts.SportObject }}</td>
				<td>{{ts.trainer }}</td>
			</tr>
		</table>
	</div>	
	
	
</div>	  
`
	,
	methods : {
			onChange(event) {
		//	console.log(event.target.value, this.selectedType);	
				
		},	
		search:function(){
			console.log("Usli smo u search.");
			
		
			if(this.selectedType === "gym"){
			
			axios
			.get('rest/getJustGyms')
			.then(response => {gyms = response.data;
							retValInside =[];
							
							for(let j = 0 ; j < this.allTrainingSessions.length ; j++){								
								for(let i = 0 ; i < gyms.length ; i++){
									if(this.allTrainingSessions[j].SportObject === gyms[i].name){
										retValInside.push(this.allTrainingSessions[j]);
									}
								}
							}
							this.retValForFilter = retValInside;	
								
			})	
			}else if(this.selectedType === "pool"){
				
			axios
			.get('rest/getJustPools')
			.then(response => {pools = response.data;
			
							retValInside = [];
							for(let j = 0 ; j < this.allTrainingSessions.length ; j++){								
								for(let i = 0 ; i < pools.length ; i++){
									if(this.allTrainingSessions[j].SportObject === pools[i].name){
										retValInside.push(this.allTrainingSessions[j]);
									}
								}
							}	
							this.retValForFilter = retValInside;	
								
			})
			}else if(this.selectedType === "dance"){
				
			axios
			.get('rest/getDanceStudios')
			.then(response => {studios = response.data;
			
							retValInside = [];
							for(let j = 0 ; j < this.allTrainingSessions.length ; j++){								
								for(let i = 0 ; i < studios.length ; i++){
									if(this.allTrainingSessions[j].SportObject === studios[i].name){
										retValInside.push(this.allTrainingSessions[j]);
									}
								}
							}
							this.retValForFilter = retValInside;
							
				})
			}else if(this.selectedType === ""){
				
				this.retValForFilter = this.allTrainingSessions;
				
			}
			retVal=[];
			for(let i = 0; i < this.retValForFilter.length; i++){
			
				if(this.retValForFilter[i].SportObject.toLowerCase().
				includes(this.searchName.toLowerCase()))
				{
					retVal.push(this.retValForFilter[i]);
				}
			}
			this.trainingSessions = retVal;
		}
	},
	mounted () {
        console.log("Mounted list sport objects");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				this.currentUser = response.data;
				
		      	
		      	axios
					.post('rest/proizvodi/getTrainingHistory', this.currentUser)
		      		.then(response => {
						if (response.data == 'EMPTY'){
							console.log('No loged training sessions!');
						}
						else {
							this.trainingSessions = response.data;
							
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
							this.allTrainingSessions = response.data;
							
						}
					});
				});
    }
});